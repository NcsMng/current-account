package com.ncsgab.currentaccount.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Value("${application-description}")
    private String description;
    @Value("${application-version}")
    private String version;
    @Value("${application-title}")
    private String title;
    @Value("${application-licenseName}")
    private String licenseName;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .description(description)
                        .version(version)
                        .license(new License().name(licenseName)));
    }
}