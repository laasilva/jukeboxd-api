package com.api.jukeboxd.datasource.integration.spotify;

import com.api.jukeboxd.core.dto.TokenDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Log4j2
public class TokenRequest {
    private final String spotifyTokenUrl;
    private final String clientSecret;
    private final String clientId;
    private final WebClient.Builder webClientBuilder;

    public TokenRequest(@Value("${spotify.url.token}") String spotifyTokenUrl,
                        @Value("${spotify.secret}") String clientSecret,
                        @Value("${spotify.id}") String clientId,
                        WebClient.Builder webClientBuilder) {
        this.spotifyTokenUrl = spotifyTokenUrl;
        this.clientSecret = clientSecret;
        this.clientId = clientId;
        this.webClientBuilder = webClientBuilder;
    }

    public String getToken() throws Exception {
        var token = generateToken();

        if(token != null) {
            return String.format("Bearer " + token.getAccess_token());
        }

        throw new Exception();
    }

    private TokenDto generateToken() {
        MultiValueMap<String, String> payload = new LinkedMultiValueMap<>();
        payload.add("grant_type", "client_credentials");
        payload.add("client_id", clientId);
        payload.add("client_secret", clientSecret);

        var response = webClientBuilder
                .build()
                .post()
                .uri(spotifyTokenUrl)
                .body(BodyInserters.fromFormData(payload))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .exchangeToMono(resp -> {
                    if(resp.statusCode() != HttpStatus.OK) {
                        log.error("Error while generating Token from " + spotifyTokenUrl);
                        log.error("Status code: " + resp.statusCode());
                    }

                    return resp.bodyToMono(new ParameterizedTypeReference<TokenDto>() {});
                })
                .toFuture();

        return response.join();
    }
}