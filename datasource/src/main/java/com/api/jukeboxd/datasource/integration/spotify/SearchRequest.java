package com.api.jukeboxd.datasource.integration.spotify;

import com.api.jukeboxd.core.dto.SearchQueryDto;
import com.api.jukeboxd.core.dto.SearchResponseDto;
import com.api.jukeboxd.core.exception.CoreException;
import com.api.jukeboxd.core.util.SearchType;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Log4j2
public class SearchRequest<T> {
    private final String spotifyApiUrl;
    private final WebClient.Builder webClientBuilder;
    private final TokenRequest tokenRequest;
    private T t;

    public SearchRequest(@Value("${spotify.url.api}") String spotifyApiUrl,
                         WebClient.Builder webClientBuilder, TokenRequest tokenRequest) {
        this.spotifyApiUrl = spotifyApiUrl;
        this.webClientBuilder = webClientBuilder;
        this.tokenRequest = tokenRequest;
    }

    public T sync(SearchQueryDto query) throws Exception {
        var searchQuery = String.format("q=%s&type=%s&market=US&limit=1&offset=0",
                query.getQ(), query.getType());

        var uri = spotifyApiUrl + "search?" + searchQuery;
        var response = webClientBuilder
                .build()
                .get()
                .uri(String.format(uri))
                .header(HttpHeaders.AUTHORIZATION, tokenRequest.getToken())
                .exchangeToMono(resp -> {
                    if (resp.statusCode() != HttpStatus.OK) {
                        log.error("Error while requesting from " + uri);
                        log.error("Status code: " + resp.statusCode());

                        throw new HttpClientErrorException(resp.statusCode());
                    }
                    return resp.bodyToMono(String.class);
                })
                .toFuture();

        var resp = jsonToModel(response.join());

        if (query.getType().equals(SearchType.ARTIST.getValue())) {
            return (T) resp.getArtists().getItems().get(0);
        }

        if (query.getType().equals(SearchType.ALBUM.getValue())) {
            return (T) resp.getAlbums().getItems().get(0);
        }

        throw new CoreException(HttpStatus.INTERNAL_SERVER_ERROR, "Error");
    }

    public List<?> search(SearchQueryDto query, int offset) throws Exception {
        var limit = 5 - offset;

        var searchQuery = String.format("q=%s&type=%s&market=US&limit=%s&offset=%s",
                query.getQ(), query.getType(), limit, offset);

        var uri = spotifyApiUrl + "search?" + searchQuery;
        var response = webClientBuilder
                .build()
                .get()
                .uri(String.format(uri))
                .header(HttpHeaders.AUTHORIZATION, tokenRequest.getToken())
                .exchangeToMono(resp -> {
                    if (resp.statusCode() != HttpStatus.OK) {
                        log.error("Error while requesting from " + uri);
                        log.error("Status code: " + resp.statusCode());

                        throw new HttpClientErrorException(resp.statusCode());
                    }
                    return resp.bodyToMono(String.class);
                })
                .toFuture();

        var resp = jsonToModel(response.join());

        if (query.getType().equals(SearchType.ARTIST.getValue())) {
            return resp.getArtists().getItems();
        }

        if (query.getType().equals(SearchType.ALBUM.getValue())) {
            return resp.getAlbums().getItems();
        }

        throw new CoreException(HttpStatus.INTERNAL_SERVER_ERROR, "Error");
    }

    private SearchResponseDto jsonToModel(String response) {
        var dto = new Gson().fromJson(response, SearchResponseDto.class);

        if (dto != null) {
            return dto;
        } else {
            throw new JsonParseException("Error while converting Artist Response JSON.");
        }
    }

    public void set(T t) { this.t = t; }
    public T get() { return t; }
}
