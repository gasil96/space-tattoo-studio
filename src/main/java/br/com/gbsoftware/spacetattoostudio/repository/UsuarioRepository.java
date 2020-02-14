package br.com.gbsoftware.spacetattoostudio.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.gbsoftware.spacetattoostudio.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String>{

	Usuario findByLogin(String login);
	
	Usuario findByEmail(String email);
	
	@Query("select u from Usuario u")
	List<Usuario> buscarTodosUsuarios();
}
