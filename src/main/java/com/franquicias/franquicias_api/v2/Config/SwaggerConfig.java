package com.franquicias.franquicias_api.v2.Config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI franquiciaApiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Franquicias API")
                        .description("API que permite gestionar franquicias, sucursales y productos.")
                        .version("v2.0.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/JuanmaMnz/Franchises-API-v2"));
    }
}
