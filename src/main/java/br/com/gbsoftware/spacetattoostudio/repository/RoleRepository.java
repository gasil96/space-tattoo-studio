package br.com.gbsoftware.spacetattoostudio.repository;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.gbsoftware.spacetattoostudio.domain.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String>{
}
