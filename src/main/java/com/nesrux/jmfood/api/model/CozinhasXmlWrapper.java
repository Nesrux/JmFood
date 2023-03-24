package com.nesrux.jmfood.api.model;

import java.util.List;

import com.nesrux.jmfood.domain.model.Cozinha;

import lombok.Data;
import lombok.NonNull;

@Data
public class CozinhasXmlWrapper {

	@NonNull
	private List<Cozinha> cozinhas;

}
