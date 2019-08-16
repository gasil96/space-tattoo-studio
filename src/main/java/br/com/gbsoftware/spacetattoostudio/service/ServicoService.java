package br.com.gbsoftware.spacetattoostudio.service;

import java.util.List;

import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;


public interface ServicoService {

	void salvar(Servico servico);

	void editar(Servico servico);

	void excluir(Long id);

	Servico buscarPorId(Long id);
	
	List<Servico> buscarTodos();
	
}
