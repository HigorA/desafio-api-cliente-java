package br.com.desafio.config;

import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("RESTFUL API Desafio")
                .version("v1")
                .description("API Manipula dados de Clientes")
                .termsOfService("")
                .license(new License().name("Apache 2.0").url(""))
        );
    }
}
