package com.neetry.platform.book.config.openapi;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by Harutyun Badeyan
 * Date: 24.03.24
 * Time: 22:28
 */
@Configuration
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "Bearer", scheme = "bearer", in = SecuritySchemeIn.HEADER, bearerFormat = "Bearer ")
public class OpenApiConfig {

    private static final String VERSION = "v3";
    private static final String DISPLAY_NAME = "NEETRY API Service";
    private static final String DESCRIPTION = "NEETRY API";

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("neetry-api-public")
                .addOpenApiCustomizer(openApi -> openApi
                        .addSecurityItem(new SecurityRequirement().addList("Bearer"))
                        .setInfo(new Info()
                                .version(VERSION)
                                .title(DISPLAY_NAME)
                                .description(DESCRIPTION)))
                .pathsToMatch("/**")
                .packagesToScan("com.neetry.platform.book")
                .build();
    }
}
