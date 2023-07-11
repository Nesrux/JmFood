package com.nesrux.jmfood.core.springFox;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;
import com.nesrux.jmfood.api.exceptionHandler.ErroApi;
import com.nesrux.jmfood.api.model.dto.output.cidade.CidadeModel;
import com.nesrux.jmfood.api.model.dto.output.cozinha.CozinhaModel;
import com.nesrux.jmfood.api.model.dto.output.estado.EstadoModel;
import com.nesrux.jmfood.api.model.dto.output.grupo.GrupoModel;
import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoResumoModel;
import com.nesrux.jmfood.api.model.dto.output.permissao.PermissaoModel;
import com.nesrux.jmfood.api.model.dto.output.produto.ProdutoModel;
import com.nesrux.jmfood.api.model.dto.output.restaurante.RestauranteResumoModel;
import com.nesrux.jmfood.api.model.dto.output.usuario.UsuarioModel;
import com.nesrux.jmfood.api.openapi.model.LinksModelOpenApi;
import com.nesrux.jmfood.api.openapi.model.PedidosModelOpenApi;
import com.nesrux.jmfood.api.openapi.model.PropriedadesPaginacaoModel;
import com.nesrux.jmfood.api.openapi.model.collectionModel.CidadesCollectionOpenApi;
import com.nesrux.jmfood.api.openapi.model.collectionModel.CozinhasModelOpenApi;
import com.nesrux.jmfood.api.openapi.model.collectionModel.EstadosCollectionOpenApi;
import com.nesrux.jmfood.api.openapi.model.collectionModel.GrupoCollectionOpenApi;
import com.nesrux.jmfood.api.openapi.model.collectionModel.PedidosPageCollectionOpenApi.PedidosEmbeddedModelOpenApi;
import com.nesrux.jmfood.api.openapi.model.collectionModel.PermissoesCollectionOpenApi;
import com.nesrux.jmfood.api.openapi.model.collectionModel.ProdutosCollectionOpenApi;
import com.nesrux.jmfood.api.openapi.model.collectionModel.RestauranteCollectionOpenApi;
import com.nesrux.jmfood.api.openapi.model.collectionModel.UsuarioCollectionOpenApi;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
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
				//.globalOperationParameters() PARAMETROS GLOBAIS DA API 
				.additionalModels(typeResolver.resolve(ErroApi.class))
				.ignoredParameterTypes(ServletWebRequest.class)
				.directModelSubstitute(Pageable.class, PropriedadesPaginacaoModel.class)
				.directModelSubstitute(Links.class, LinksModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(PagedModel.class, CozinhaModel.class),
						CozinhasModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Page.class, PedidoResumoModel.class),
						PedidosModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, CidadeModel.class),
						CidadesCollectionOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, EstadoModel.class),
						EstadosCollectionOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, GrupoModel.class),
						GrupoCollectionOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, PermissaoModel.class),
						PermissoesCollectionOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, PedidoResumoModel.class),
						PedidosEmbeddedModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, ProdutoModel.class),
						ProdutosCollectionOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, RestauranteResumoModel.class),
						RestauranteCollectionOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, UsuarioModel.class),
						UsuarioCollectionOpenApi.class))
				.apiInfo(apiInfo())
				.tags(
					new Tag("Cidades", "Gerencia as cidades"),
					new Tag ("Grupos" , "Gerencia os grupos de usuarios"),
					new Tag("Cozinhas", "Gerencia as cozinhas"),
					new Tag("Formas de pagamento", "Gerencia as formas de pagamento"),
					new Tag("Pedidos", "Gerencia os pedidos"),
					new Tag("Restaurantes", "Gerencia os Restaurantes"),
					new Tag("Estados", "Gerencia os Estados"),
					new Tag("Produtos", "Gerencia os produtos"), 
					new Tag("Usuarios", "Gerencia os Usuarios"),
					new Tag("Estatisticas","Gerencia as Estatísticas"),
					new Tag("RootEntryPoint", "Ponto de entrada da Api")
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
