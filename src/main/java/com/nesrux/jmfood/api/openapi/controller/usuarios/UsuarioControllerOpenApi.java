package com.nesrux.jmfood.api.openapi.controller.usuarios;

import java.util.List;

import com.nesrux.jmfood.api.model.dto.input.usuario.TrocarSenhaInput;
import com.nesrux.jmfood.api.model.dto.input.usuario.UsuarioInput;
import com.nesrux.jmfood.api.model.dto.input.usuario.UsuarioInputAtualizar;
import com.nesrux.jmfood.api.model.dto.output.usuario.UsuarioModel;

public interface UsuarioControllerOpenApi {

	public List<UsuarioModel> listar();

	public UsuarioModel buscar(Long usuarioId);

	public UsuarioModel salvar(UsuarioInput userInput);

	public UsuarioModel atualizar(Long usuarioId, UsuarioInputAtualizar usuarioInput);

	public void atualizarSenha(Long usuarioId, TrocarSenhaInput senhainput);

}
