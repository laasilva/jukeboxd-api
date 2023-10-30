package com.api.jukeboxd.core.business;

import com.api.jukeboxd.core.model.AuthRequest;
import com.api.jukeboxd.core.port.persistence.UserPersistenceAdapter;
import com.api.jukeboxd.core.port.service.AuthServiceAdapter;
import com.api.jukeboxd.core.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RequiredArgsConstructor
public class AuthService implements AuthServiceAdapter {
    private final UserPersistenceAdapter persistence;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public String authenticate(AuthRequest auth) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        auth.getUsername(),
                        auth.getPassword()));
        var user = persistence.authByUsername(auth.getUsername());
        return jwtService.generateToken(user);
    }

    @Override
    public Boolean validate(String username) {
        return persistence.usernameExists(username);
    }
}
