package com.nesrux.jmfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nesrux.jmfood.domain.model.user.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{
	
	public String findSenhaById(Long id);
	public String findEmailById(Long id);
}
