package com.api.jukeboxd.application.config;

import com.api.jukeboxd.core.business.ArtistService;
import com.api.jukeboxd.core.business.AuthService;
import com.api.jukeboxd.core.business.UserService;
import com.api.jukeboxd.core.port.persistence.ArtistPersistenceAdapter;
import com.api.jukeboxd.core.port.persistence.SyncArtistPersistenceAdapter;
import com.api.jukeboxd.core.port.persistence.UserPersistenceAdapter;
import com.api.jukeboxd.core.security.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan(basePackages = {"com.api.jukeboxd"})
public class BeanConfig {
    @Bean
    ArtistService artistService(SyncArtistPersistenceAdapter syncArtistPersistenceAdapter,
                                ArtistPersistenceAdapter persistence) {
        return new ArtistService(syncArtistPersistenceAdapter, persistence);
    }

    @Bean
    UserService userService(UserPersistenceAdapter userPersistenceAdapter, PasswordEncoder passwordEncoder) {
        return new UserService(userPersistenceAdapter, passwordEncoder);
    }

    @Bean
    AuthService authService(UserPersistenceAdapter persistence, JwtService jwtService, AuthenticationManager authenticationManager) {
        return new AuthService(persistence, jwtService, authenticationManager);
    }
}
