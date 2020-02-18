package br.com.gbsoftware.spacetattoostudio.repository;

import java.math.BigDecimal;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome);

	List<Cliente> findByInstagram(String instagram);

	@Transactional
	@Modifying
	@Query("UPDATE Cliente SET credito_cliente = ?1 WHERE id = ?2")
	void updateCredito(BigDecimal valorCredito, Long idCliente);

	@Transactional
	@Modifying
	@Query("UPDATE Cliente SET status = ?1 WHERE id = ?2")
	void updateStatus(String statusCliente, Long idCliente);

}

