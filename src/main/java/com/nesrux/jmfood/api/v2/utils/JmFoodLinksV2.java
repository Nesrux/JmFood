package com.nesrux.jmfood.api.v2.utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.api.v1.controller.main.CidadeController;
import com.nesrux.jmfood.api.v2.controller.CozinhaControllerV2;

@Service
public class JmFoodLinksV2 {
	public Link linkToCidades(String rel) {
		return linkTo(CidadeController.class).withRel(rel);
	}

	public Link linkToCidades() {
		return linkToCidades(IanaLinkRelations.SELF.value());
	}

	public Link linkToCozinhas(String rel) {
		return linkTo(CozinhaControllerV2.class).withRel(rel);
	}

	public Link linkToCozinhas() {
		return linkToCozinhas(IanaLinkRelations.SELF.value());
	}

}
