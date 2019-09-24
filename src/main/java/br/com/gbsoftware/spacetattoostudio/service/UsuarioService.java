package br.com.gbsoftware.spacetattoostudio.service;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.util.List;

import br.com.gbsoftware.spacetattoostudio.domain.model.Usuario;

public interface UsuarioService {

	List<Usuario> buscarTodos();
	
	void salvar(Usuario usuario);
}
