package com.kunal.aws_integration.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomConfig() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Employee Management")
                                .version("1.0.0")
                                .description("This API documentation for Employee Management app.")
                )
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Server"),
                        new Server().url("http://localhost:8081").description("Live Server")

                    )
                )
                .tags(List.of(
                        new Tag().name("Employee API")
                ));
    }


}
