package com.canevi.fraud.detection.config;

import com.canevi.fraud.detection.docs.SwaggerExampleLoader;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.MediaType;
import org.springdoc.core.customizers.OpenApiCustomizer;
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

//    @Bean
//    public OpenApiCustomizer transactionRequestExampleCustomizer() {
//        return openApi -> {
//            var pathItem = openApi.getPaths().get("/api/fraud/check");
//            if (pathItem == null || pathItem.getPost() == null) return;
//
//            var content = new MediaType();
//            content.setExample(SwaggerExampleLoader.loadExample("swagger-examples/transaction-request-example.json"));
//
//            var requestBody = pathItem.getPost().getRequestBody();
//            if (requestBody != null) {
//                requestBody.getContent().put("application/json", content);
//            }
//        };
//    }
}