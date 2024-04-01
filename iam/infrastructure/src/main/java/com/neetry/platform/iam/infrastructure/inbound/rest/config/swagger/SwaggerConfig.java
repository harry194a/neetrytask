package com.neetry.platform.iam.infrastructure.inbound.rest.config.swagger;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "Bearer", scheme = "bearer", in = SecuritySchemeIn.HEADER, bearerFormat = "Bearer ")
public class SwaggerConfig {

    private static final String VERSION = "v3";
    private static final String DISPLAY_NAME = "OAUTH API Service";
    private static final String API_DESCRIPTION = "OAUTH API";

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("neetry-api-public")
                .addOpenApiCustomizer(openApi -> openApi
                        .addSecurityItem(new SecurityRequirement().addList("Bearer"))
                        .setInfo(new Info()
                                .version(VERSION)
                                .title(DISPLAY_NAME)
                                .description(API_DESCRIPTION)))
                .pathsToMatch("/**")
                .packagesToScan("com.neetry.platform.iam")
                .build();
    }

}
