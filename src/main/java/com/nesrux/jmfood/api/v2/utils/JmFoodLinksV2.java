package com.nesrux.jmfood.api.v2.utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;

import com.nesrux.jmfood.api.v1.controller.CidadeController;
import com.nesrux.jmfood.api.v1.controller.CozinhaController;

public class JmFoodLinksV2 {
	public Link linkToCidades(String rel) {
		return linkTo(CidadeController.class).withRel(rel);
	}

	public Link linkToCidades() {
		return linkToCidades(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToCozinhas(String rel) {
		return linkTo(CozinhaController.class).withRel(rel);
	}

	public Link linkToCozinhas() {
		return linkToCozinhas(IanaLinkRelations.SELF.value());
	}

}
