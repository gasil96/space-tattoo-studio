package br.com.gbsoftware.spacetattoostudio.service;

import java.util.List;

import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;

public interface ClienteService {

	void salvar(Cliente cliente);

	void editar(Cliente cliente);

	void excluir(Long id);

	Cliente buscarPorId(Long id);
	
	List<Cliente> buscarTodos();
	
}
