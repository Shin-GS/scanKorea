package com.scankorea.server.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "ScanKorea Project API", version = "v1")
)
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi
                .builder()
                .group("API")
                .displayName("ScanKorea API")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi hxApi() {
        return GroupedOpenApi
                .builder()
                .group("HX API")
                .displayName("ScanKorea HX API")
                .pathsToMatch("/hx/**")
                .build();
    }
}
