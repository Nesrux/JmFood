package com.nesrux.jmfood.api.controller.subcontrollers.grupo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.domain.model.user.Permissao;
import com.nesrux.jmfood.domain.service.CadastroGrupoService;
import com.nesrux.jmfood.domain.service.CadastroPermissaoService;

@RestController
@RequestMapping("/{grupoId}/permissoes")
public class GrupoPermissaoController {
	@Autowired
	private CadastroGrupoService grupoService;

	@Autowired
	private CadastroPermissaoService permissaoService;

	@GetMapping
	public List<Permissao> ListarPermissoes(@PathVariable Long grupoId) {
		return grupoService.listarPermissoes(grupoId);
	}
}
