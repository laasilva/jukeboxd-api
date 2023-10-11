package com.api.jukeboxd.application.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

public class OpenApiConfig {
    @Bean
    @Profile("!prod")
    public GroupedOpenApi actuatorApi(OpenApiCustomizer actuatorOpenApiCustomiser,
                                      OperationCustomizer actuatorCustomizer,
                                      @Value("${springdoc.version}") String appVersion) {
        return GroupedOpenApi.builder()
                .group("Actuator")
                .pathsToMatch("*")
                .addOpenApiCustomizer(actuatorOpenApiCustomiser)
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("Actuator API").version(appVersion)))
                .addOperationCustomizer(actuatorCustomizer)
                .pathsToExclude("/health/*")
                .build();
    }

    @Bean
    public GroupedOpenApi usersGroup(@Value("${springdoc.version}") String appVersion) {
        return GroupedOpenApi.builder().group("users")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    operation.addSecurityItem(new SecurityRequirement().addList("basicScheme"));
                    return operation;
                })
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("Users API").version(appVersion)))
                .packagesToScan("org.springdoc.demo.app2")
                .build();
    }
}
