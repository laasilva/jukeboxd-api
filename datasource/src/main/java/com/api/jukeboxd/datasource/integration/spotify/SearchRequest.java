package com.api.jukeboxd.datasource.integration.spotify;

import com.api.jukeboxd.core.dto.artist.ArtistDto;
import com.api.jukeboxd.core.dto.SearchQueryDto;
import com.api.jukeboxd.core.dto.artist.SearchArtistResponseDto;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Log4j2
public class SearchRequest {
    private final String spotifyApiUrl;
    private final WebClient.Builder webClientBuilder;
    private final TokenRequest tokenRequest;

    public SearchRequest(@Value("${spotify.url.api}") String spotifyApiUrl,
                         WebClient.Builder webClientBuilder, TokenRequest tokenRequest) {
        this.spotifyApiUrl = spotifyApiUrl;
        this.webClientBuilder = webClientBuilder;
        this.tokenRequest = tokenRequest;
    }

    public ArtistDto syncArtist(SearchQueryDto query) throws Exception {
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
        return jsonToModel(response.join());
    }

    private ArtistDto jsonToModel(String response) {
        var dto = new Gson().fromJson(response, SearchArtistResponseDto.class);

        if (dto != null) {
            return dto.getArtists().getItems().get(0);
        } else {
            throw new JsonParseException("Error while converting Artist Response JSON.");
        }
    }
}
