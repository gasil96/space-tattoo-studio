package br.com.gbsoftware.spacetattoostudio.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gbsoftware.spacetattoostudio.domain.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{
}
