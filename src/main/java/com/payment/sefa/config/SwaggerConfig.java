package com.payment.sefa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    @Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes("Bearer",
								new SecurityScheme().type(SecurityScheme.Type.HTTP)
										.scheme("bearer").bearerFormat("JWT")))
				.info(new Info()
						.title("Intranet DPE API")
						.description(
								"API de pagamento - Teste Técnico.")
						.version("v0.0.1")
						.license(new License()
								.name("Viviane Castro do Rosário")
								.url("")));
	}

}
