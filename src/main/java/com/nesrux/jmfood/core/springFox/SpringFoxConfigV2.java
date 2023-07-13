package com.nesrux.jmfood.core.springFox;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;
import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.v1.openapi.model.LinksModelOpenApi;
import com.nesrux.jmfood.api.v1.openapi.model.PropriedadesPaginacaoModel;
import com.nesrux.jmfood.api.v1.openapi.model.collectionModel.CozinhasModelOpenApi;
import com.nesrux.jmfood.api.v2.model.output.cozinha.CozinhaModelV2;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
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
public class SpringFoxConfigV2 implements WebMvcConfigurer {

	@Bean
	public Docket apiDocketV2() {
		var typeResolver = new TypeResolver();

		return new Docket(DocumentationType.SWAGGER_2)
			.groupName("v2")
			.select()
				.apis(RequestHandlerSelectors.basePackage("com.nesrux.jmfood.api"))
				.paths(PathSelectors.ant("/v2/**"))
				.build()
			.useDefaultResponseMessages(false)
			.globalResponseMessage(RequestMethod.GET, globalGetResponsemessage())
			.globalResponseMessage(RequestMethod.DELETE, globalDeleteResponsemessage())
			.globalResponseMessage(RequestMethod.POST, globalPostResponsemessage())
			.globalResponseMessage(RequestMethod.PUT, globalPutResponsemessage())
			// .globalOperationParameters() PARAMETROS GLOBAIS DA API
			.additionalModels(typeResolver.resolve(ErroApi.class))
			.ignoredParameterTypes(ServletWebRequest.class)
			.directModelSubstitute(Pageable.class, PropriedadesPaginacaoModel.class)
			.directModelSubstitute(Links.class, LinksModelOpenApi.class)
			.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(PagedModel.class, CozinhaModelV2.class),
					CozinhasModelOpenApi.class))
			
			.tags(
					new Tag("Cozinhas", "Gerencia os endpoints de cozinhas"),
					new Tag("Cidades", "Gerencia os endpoints de cidades")
				)
			
			.apiInfo(apiInfoV2());
	}

	private ApiInfo apiInfoV2() {
		return new ApiInfoBuilder()
				.title("Jmfood API")
				.version("2")
				.description("Uma Rest Api publica de um delivery de comida")
				.contact(new Contact("Jmfood", "https://github.com/Nesrux/JmFood", "joaomarcosdevs@gmai.com"))
				.build();

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
