package br.com.gbsoftware.spacetattoostudio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.gbsoftware.spacetattoostudio.domain.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String>{

}
