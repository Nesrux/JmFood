package com.nesrux.jmfood.api.classconversion.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.domain.service.CadastroGrupoService;

@Component
public class GrupoOutputAssembler {
	@Autowired
	private CadastroGrupoService service;

}
