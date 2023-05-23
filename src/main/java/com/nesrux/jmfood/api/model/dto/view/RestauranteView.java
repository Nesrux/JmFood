package com.nesrux.jmfood.api.model.dto.view;

public interface RestauranteView {

	public interface resumo {
	}

	public interface apenasNome {
	}

	/*
	 * Essa é uma classe utilizada apenas para configurar o o @jsonview, funciona
	 * exatamente igual ao grups do java validation
	 */

	/*
	 * ela é utilizada com a anotação @jsonview e recebe em cima nas propriedades
	 * que você quer projetar na representação do recurso, e no método do controller
	 * onde vc quer que esses dados sejam projetados
	 */

	/*
	 * para utilizar ela é preciso usar uma pathVariable, e atribuir o valor dela de
	 * requirid como false, e criar uma logica que venha fazer isso
	 * programtaticmanete de forma dinamica, mas para usar isso é necessário duas
	 * coisas
	 */

	/*
	 * primeiro é o método retornar um MappingJacksonValue, e instanciar esse mesmo
	 * método para usar ele de retorno na hora da representação EX: com listagem de
	 * Restaurantes
	 */

	/*
	 * @GetMapping("/test") public MappingJacksonValue
	 * listarDinamicamente(@PathVariable(required = false) String resumo) {
	 * List<Restaurante> restaurantes = service.acharTodos(); List<RestauranteModel>
	 * listaRestauranteModel = restauranteAssembler.toCollectionDto(restaurantes);
	 * 
	 * MappingJacksonValue jacksonValue = new
	 * MappingJacksonValue(listaRestauranteModel);
	 * 
	 * if("resumo".equals(resumo)) {
	 * jacksonValue.setSerializationView(RestauranteView.resumo.class); } else
	 * if("apenas-nome".equals(resumo)) {
	 * jacksonValue.setSerializationView(RestauranteView.apenasNome.class); }
	 * 
	 * return jacksonValue;
	 * }
	 */
}
