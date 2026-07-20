package com.atv.PetCare.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI petCareOpenAPI() {

        return new OpenAPI()
                .info(
                    new Info()
                        .title("PetCare API")
                        .description(
                            "API para gerenciamento de tutores, pets e consultas")
                        .version("1.0.0"));
    }
}