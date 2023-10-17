package com.api.jukeboxd.application.controller;

import com.api.jukeboxd.application.dto.request.UserRequestDTO;
import com.api.jukeboxd.application.dto.response.ErrorResponse;
import com.api.jukeboxd.application.dto.response.Response;
import com.api.jukeboxd.application.dto.response.UserResponseDTO;
import com.api.jukeboxd.application.mapper.UserDTOMapper;
import com.api.jukeboxd.core.exception.PasswordEncryptionException;
import com.api.jukeboxd.core.exception.ValidationException;
import com.api.jukeboxd.core.model.User;
import com.api.jukeboxd.core.port.service.UserSerivceAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/u")
@Tag(name = "User Configuration")
//@PreAuthorize("hasAuthority('SCOPE_user')")
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
    public ResponseEntity<User> getUser(@RequestHeader String username) {
        return ResponseEntity.ok(service.fetchByUsername(username));
    }
    @Operation(summary = "Create new User", description = "Adds new User to Database")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "401", description = "Error while creating username")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<UserResponseDTO>> getArtist(@RequestBody UserRequestDTO user) throws PasswordEncryptionException {
        var response = service.addNewUser(mapper.toModel(user));
        return new ResponseEntity<>(new Response<UserResponseDTO>(mapper.toResponseDTO(response)), HttpStatus.CREATED);
    }
}
