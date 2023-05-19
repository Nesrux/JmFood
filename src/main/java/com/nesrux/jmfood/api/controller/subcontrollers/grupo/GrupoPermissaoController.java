package com.nesrux.jmfood.api.controller.subcontrollers.grupo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/{grupoId}/permissoes")
public class GrupoPermissaoController {
	
	private CadastroGrupoService grupoService;
}
