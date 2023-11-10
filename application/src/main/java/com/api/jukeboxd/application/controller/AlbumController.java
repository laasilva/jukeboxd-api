package com.api.jukeboxd.application.controller;

import com.api.jukeboxd.core.model.Album;
import com.api.jukeboxd.core.port.service.AlbumServiceAdapter;
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
@RequestMapping("/albums")
@Tag(name = "Album Interactions")
public class AlbumController {
    private final AlbumServiceAdapter service;
    @Operation(summary = "Search for albums", description = "Searches for albums on database and Spotify")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK Status"),
            @ApiResponse(responseCode = "404", description = "Not Found Status")
    })
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Album>> searchAlbums(@Parameter(description = "Insert Album Name")
                                                      @RequestParam String name) throws Exception {
        return ResponseEntity.ok(service.searchAlbums(name));
    }
    @Operation(summary = "Get Album", description = "Returns Album information based on Name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK Status"),
            @ApiResponse(responseCode = "404", description = "Not Found Status")
    })
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Album> getAlbum(@Parameter(description = "Insert Album Name") String albumName)
            throws Exception {
        return ResponseEntity.ok(service.syncAlbumFromSpotify(albumName));
    }
}
