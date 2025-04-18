package com.canevi.fraud.detection.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Fraud Detection API")
                        .version("1.0")
                        .description("Detects fraudulent transactions based on a rule engine")
                        .contact(new Contact()
                                .name("Your Team")
                                .email("team@canevi.com")));
    }
}