//package com.testProject.demo.Configuration;
//
//
//import io.swagger.v3.oas.annotations.info.Contact;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.annotations.info.License;
//import io.swagger.v3.oas.models.OpenAPI;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//
//
//public class OpenApiConfig {
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        Contact contact = new Contact().name("Ehsan Atif").email("ehsanAtif@example.com");
//        return new OpenAPI()
//                .info(new Info().title("E-commerce API")
//                        .description("API for managing products, orders, and order items")
//                        .version("1.0")
//                        .contact(new Contact().name("Ehsan Atif").email("ehsanAtif@example.com"))
//                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html")));
//    }
//
//}