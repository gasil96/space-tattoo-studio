package br.com.gbsoftware.spacetattoostudio.service;
import java.util.List;

/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import br.com.gbsoftware.spacetattoostudio.domain.model.Role;

public interface RoleService {

	void salvar(Role role);
	
	List<Role> buscarTodos();
}
