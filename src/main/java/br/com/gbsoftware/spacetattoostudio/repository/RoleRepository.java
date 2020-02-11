package br.com.gbsoftware.spacetattoostudio.repository;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface RoleRepository extends JpaRepository<Role, String>{
}
