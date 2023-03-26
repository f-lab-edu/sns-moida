package com.moida.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	private static final String SECURITY_SCHEME_NAME = "BearerAuth";

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.info(apiInfo())
			.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
			.components(new Components()
				.addSecuritySchemes(SECURITY_SCHEME_NAME, createAPIKeyScheme()));
	}

	private Info apiInfo() {
		return new Info()
			.title("SNS Moida API")
			.description("SNS 서비스 API 명세서 - JWT 인증 포함")
			.version("V1.0");
	}

	private SecurityScheme createAPIKeyScheme() {
		return new SecurityScheme()
			.type(SecurityScheme.Type.HTTP)
			.scheme("bearer")
			.bearerFormat("JWT")
			.description("JWT 토큰을 입력하세요. 'Bearer ' 접두사는 자동으로 추가됩니다.");
	}
}
