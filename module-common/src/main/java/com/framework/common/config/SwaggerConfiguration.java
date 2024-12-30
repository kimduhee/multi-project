package com.framework.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.framework.common.config
 * fileName       : SwaggerConfiguration
 * author         : NAMANOK
 * date           : 2024-12-30
 * description    :
 *  swagger 설정
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        NAMANOK       최초 생성
 */
@OpenAPIDefinition(
        info = @Info(
                title = "API Test",
                description = "Swagger UI",
                version = "1.0.0")
)
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        String jwt = "JWT";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt);
        Components components = new Components().addSecuritySchemes(jwt, new SecurityScheme()
                .name(jwt)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
        );
        return new OpenAPI()
                .components(new Components())
                //.info(apiInfo())
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
