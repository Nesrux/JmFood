package com.nesrux.jmfood.domain.repository;

import java.util.Optional;

import com.nesrux.jmfood.domain.model.user.Usuario;

public interface UsuarioRepository  extends CustomJpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByEmail(String email);
}
