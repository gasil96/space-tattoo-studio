package br.com.gbsoftware.spacetattoostudio.service;

import java.util.List;

import br.com.gbsoftware.spacetattoostudio.domain.model.Usuario;

public interface UsuarioService {

	List<Usuario> buscarTodos();
	
	void salvar(Usuario usuario);
}
