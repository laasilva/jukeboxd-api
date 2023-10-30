package com.api.jukeboxd.application.controller;

import com.api.jukeboxd.core.model.Artist;
import com.api.jukeboxd.core.model.Paginated;
import com.api.jukeboxd.core.port.service.ArtistServiceAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/artists")
@Tag(name = "Artist Interactions")
public class ArtistController {
    private final ArtistServiceAdapter service;

    @Operation(summary = "Search for artists", description = "Searches for artist on database and Spotify")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK Status"),
            @ApiResponse(responseCode = "404", description = "Not Found Status")
    })
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Artist>> searchArtists(@Parameter(description = "Insert Artist Name")
                                                      @RequestParam String name) throws Exception {
        return ResponseEntity.ok(service.searchArtists(name));
    }
    @Operation(summary = "Get Artist", description = "Returns Artist information based on Name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK Status"),
            @ApiResponse(responseCode = "404", description = "Not Found Status")
    })
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Artist> getArtist(@Parameter(description = "Insert Artist Name") String artistName) {
        return ResponseEntity.ok(service.syncArtistFromSpotify(artistName));
    }

    @Operation(summary = "Get Artists By IDs", description = "Returns a list of Artists information based on IDs")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK Status"),
            @ApiResponse(responseCode = "404", description = "Not Found Status")
    })
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<Artist>> getArtistsByIds(@Parameter(description = "Insert Artist Ids")
                                                            @RequestParam String[] ids) {
        return ResponseEntity.ok(service.getArtistsById(ids));
    }
    @Operation(summary = "Get Artists By IDs", description = "Returns a list of Artists information based on IDs")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK Status"),
            @ApiResponse(responseCode = "404", description = "Not Found Status")
    })
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/list/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Paginated<List<Artist>>> getAllArtistsPaginated(@Parameter(description = "Page")
                                                                              @RequestHeader int page,
                                                                         @Parameter(description = "Size")
                                                                            @RequestHeader int size) {
        return ResponseEntity.ok(service.getAllArtists(page, size));
    }
}
