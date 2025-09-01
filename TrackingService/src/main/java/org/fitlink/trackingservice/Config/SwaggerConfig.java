package org.fitlink.trackingservice.Config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    @Bean
     public OpenAPI CustomeAPI () {
        return new OpenAPI().info(
                new Info().title("This is the api documentation for tracking service ")
                        .version("version v1 ")
                        .description(" this api can track meal plans and workout  plans of each client"
                        )
        );

    }
}
