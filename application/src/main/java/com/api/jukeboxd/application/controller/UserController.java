package com.api.jukeboxd.application.controller;

import com.api.jukeboxd.application.dto.response.Response;
import com.api.jukeboxd.application.dto.response.UserResponseDTO;
import com.api.jukeboxd.application.mapper.UserDTOMapper;
import com.api.jukeboxd.core.model.User;
import com.api.jukeboxd.core.port.service.AuthServiceAdapter;
import com.api.jukeboxd.core.port.service.UserSerivceAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/u")
@Tag(name = "User Config")
public class UserController {
    private final UserSerivceAdapter service;
    private final UserDTOMapper mapper;
    @Operation(summary = "Get User by Username", description = "Returns User information based on username")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK Status"),
            @ApiResponse(responseCode = "404", description = "Not Found Status")
    })
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Response<UserResponseDTO>> getUser(@RequestHeader String username) {
        var user = service.fetchByUsername(username);
        return ResponseEntity.ok(new Response<>(mapper.toResponseDTO(user)));
    }
}
