package com.nesrux.jmfood.core.openapi;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;
import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.openapi.controller.PageableModelApi;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDocket() {
	 var typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.nesrux.jmfood.api"))
					.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, globalGetResponsemessage())
				.globalResponseMessage(RequestMethod.DELETE, globalDeleteResponsemessage())
				.globalResponseMessage(RequestMethod.POST, globalPostResponsemessage())
				.globalResponseMessage(RequestMethod.PUT, globalPutResponsemessage())
				.additionalModels(typeResolver.resolve(ErroApi.class))
				.directModelSubstitute(Pageable.class, PageableModelApi.class)
				.apiInfo(apiInfo())
				.tags(
					new Tag("Cidades", "Gerencia as cidades"),
					new Tag ("Grupos" , "Gerencia os grupos de usuarios")
						);
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Jmfood API")
				.version("1.0.0")
				.description("Uma Rest Api publica de um delivery de comida")
				.contact(new Contact("Jmfood", "https://github.com/Nesrux/JmFood", "joaomarcosdevs@gmai.com"))
				.build();
		
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");

	}

	
	private List<ResponseMessage> globalPutResponsemessage() {
		return Arrays.asList(
			new ResponseMessageBuilder()
				.code(HttpStatus.BAD_REQUEST.value())
				.message("Requisição inválida (erro do cliente)")
				.responseModel(new ModelRef("Problema"))
				.build(),
			new ResponseMessageBuilder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.responseModel(new ModelRef("Problema"))
				.message("Erro interno no servidor")
				.build(),
			new ResponseMessageBuilder()
				.code(HttpStatus.NOT_FOUND.value())
				.responseModel(new ModelRef("Problema"))
				.message("A entidade que você tentou atualizar não existe")
				.build(),
			new ResponseMessageBuilder()
				.code(HttpStatus.NOT_ACCEPTABLE.value())
				.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
				.build(),
			new ResponseMessageBuilder()
				.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
				.message("Requisição recusada porque o corpo está em um formato não suportado")
				.build()
				
				);
	}

	private List<ResponseMessage> globalPostResponsemessage() {
		return Arrays.asList(
			new ResponseMessageBuilder()
				.code(HttpStatus.BAD_REQUEST.value())
				.message("Requisição inválida (erro do cliente)")
				.responseModel(new ModelRef("Problema"))
				.build(),
			new ResponseMessageBuilder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.responseModel(new ModelRef("Problema"))
				.message("Erro interno no servidor")
				.build(),
			new ResponseMessageBuilder()
				.code(HttpStatus.NOT_ACCEPTABLE.value())
				.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
				.build(),
			new ResponseMessageBuilder()
				.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
				.message("Requisição recusada porque o corpo está em um formato não suportado")
				.build());
	}

	private List<ResponseMessage> globalDeleteResponsemessage() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.CONFLICT.value())
					.responseModel(new ModelRef("Problema"))
					.message("O recurso que você tentou apagar está sendo usado por outra entidade")
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_FOUND.value())
					.responseModel(new ModelRef("Problema"))
					.message("O recuso que você tentou apagar não existe")
					.build(),				
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.responseModel(new ModelRef("Problema"))
					.message("Erro interno no servidor")
					.build()
				);
	}

	private List<ResponseMessage> globalGetResponsemessage(){
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.responseModel(new ModelRef("Problema"))
					.message("Erro interno do servidor")
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso não possui representação que poderia ser aceito pelo consumidor")
					.build()
				);
	}


}
