package br.com.gbsoftware.spacetattoostudio.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.gbsoftware.spacetattoostudio.domain.model.Usuario;


public interface UsuarioRepository extends CrudRepository<Usuario, String>{

	Usuario findByLogin(String login);
	
}
