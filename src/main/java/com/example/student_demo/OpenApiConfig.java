package com.example.student_demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI studentApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student Demo API")
                        .description("CRUD API with validation, service layer & global exceptions")
                        .version("v1.0"));
    }
}