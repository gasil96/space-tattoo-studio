package br.com.gbsoftware.spacetattoostudio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
/**
 * 
 * <b>GB Software</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 *
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome);

	List<Cliente> findByInstagram(String instagram);

	
}


