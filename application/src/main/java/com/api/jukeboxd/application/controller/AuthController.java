package com.api.jukeboxd.application.controller;

import com.api.jukeboxd.application.dto.request.AuthRequestDTO;
import com.api.jukeboxd.application.dto.request.UserRequestDTO;
import com.api.jukeboxd.application.dto.response.AuthResponseDTO;
import com.api.jukeboxd.application.dto.response.Response;
import com.api.jukeboxd.application.dto.response.UserResponseDTO;
import com.api.jukeboxd.application.mapper.UserDTOMapper;
import com.api.jukeboxd.core.exception.PasswordEncryptionException;
import com.api.jukeboxd.core.model.AuthRequest;
import com.api.jukeboxd.core.port.service.AuthServiceAdapter;
import com.api.jukeboxd.core.port.service.UserSerivceAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthController {
    private final UserSerivceAdapter userService;
    private final AuthServiceAdapter authService;
    private final UserDTOMapper mapper;
    @Operation(summary = "Create new User", description = "Adds new User to Database")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "401", description = "Error while creating username")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<UserResponseDTO>> register(@RequestBody UserRequestDTO user) throws PasswordEncryptionException {
        var response = userService.addNewUser(mapper.toModel(user));
        return new ResponseEntity<>(new Response<UserResponseDTO>(mapper.toResponseDTO(response)), HttpStatus.CREATED);
    }
    @Operation(summary = "Authenticate User", description = "Authenticate User using existing credentials")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "401", description = "Error while creating username")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO auth) throws PasswordEncryptionException {
        return ResponseEntity.ok(AuthResponseDTO.builder().token(authService
                .authenticate(AuthRequest.builder()
                        .username(auth.getUsername())
                        .password(auth.getPassword())
                        .build())).build());
    }
    @Operation(summary = "Validate Username", description = "Validate if username exists while on signup.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Error while validating username")
    })
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value="/validate",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean validate(@RequestHeader String username) {
        return authService.validate(username);
    }
}
