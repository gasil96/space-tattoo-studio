package br.com.gbsoftware.spacetattoostudio.repository;

/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome);

	List<Cliente> findByInstagram(String instagram);

	@Query(value = "SELECT * FROM cliente WHERE month(data_cadastro) = month(now())", nativeQuery = true)
	List<Cliente> getPorCadastroMesAtual();

	@Query(value = "SELECT * FROM cliente WHERE MONTH(data_cadastro) = (MONTH(NOW())-1)", nativeQuery = true)
	List<Cliente> getPorCadastroMesPassado();
	
	@Query(value = "SELECT * FROM cliente ORDER BY data_cadastro DESC", nativeQuery = true)
	List<Cliente> findAll();
	
	@Query(value = "select clt.id, clt.instagram, clt.nome, clt.telefone from cliente clt", nativeQuery = true)
	List<Cliente> getClienteIdInstaNome();
}