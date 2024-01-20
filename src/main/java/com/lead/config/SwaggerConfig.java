package com.lead.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Lead API",
                description = "This Lead API will add, delete, create and update lead data",
                termsOfService = "T&C",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "Pramij",
                        email = "pramij@gmail.com"
                ),
                license = @io.swagger.v3.oas.annotations.info.License(
                        name = "Code With Robin"

                ),
                version = "v1"
        ),
        servers = {@Server(
                description = "Dev",
                url = "http://localhost:8082"
        ),
                @Server(
                        description = "QA",
                        url = "http://localhost:8082"
                ),
                @Server(
                        description = "Test",
                        url = "http://localhost:8082"
                )}
)
@Configuration
public class SwaggerConfig {

    // @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                        .title("Lead Application")
                        .description("This is application for Lead APIs")
                        .version("1.0")
                        .contact(new Contact().name("Code with Robin").email("robin@gmail.com").url("robin.com")
                        ).license(new License().name("Apache")))
                .externalDocs(new ExternalDocumentation().description("External").url("coding.com"));
    }

    //@Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("Lead Application APIs")
                .pathsToMatch("/v1/leads/**")
                .displayName("Lead Application")
                .build();
    }
}
