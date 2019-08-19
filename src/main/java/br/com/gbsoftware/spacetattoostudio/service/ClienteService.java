package br.com.gbsoftware.spacetattoostudio.service;

import java.util.List;
import java.util.Optional;

import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;

/**
 * 
 * <b>GB Software</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 *
 */

public interface ClienteService {

	void salvar(Cliente cliente);

	void excluir(Long id);

	Optional<Cliente> buscarPorId(Long id);

	List<Cliente> buscarPorNome(String nome);
	
	List<Cliente> buscarPorInstagram(String instagram);
	
	List<Cliente> buscarTodos();

	void editar(Cliente cliente);
}
