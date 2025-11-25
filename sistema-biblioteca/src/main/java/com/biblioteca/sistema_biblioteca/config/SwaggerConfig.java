package com.biblioteca.sistema_biblioteca.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Biblioteca API")
                        .version("1.0")
                        .description("Projeto final de Backend - API para gerenciamento de biblioteca")
                        .contact(new Contact()
                                .name("Suporte")
                                .email("suporte@biblioteca.com")));
    }
}