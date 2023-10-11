package com.api.jukeboxd.application.config;

import com.api.jukeboxd.core.business.ArtistService;
import com.api.jukeboxd.core.port.persistence.ArtistPersistenceAdapter;
import com.api.jukeboxd.core.port.persistence.SyncArtistPersistenceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.api.jukeboxd"})
public class BeanConfig {
    @Bean
    ArtistService artistService(SyncArtistPersistenceAdapter syncArtistPersistenceAdapter,
                                ArtistPersistenceAdapter persistence) {
        return new ArtistService(syncArtistPersistenceAdapter, persistence);
    }
}
