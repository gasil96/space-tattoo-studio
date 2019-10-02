package br.com.gbsoftware.spacetattoostudio.service;

import java.util.List;
import java.util.Optional;

import br.com.gbsoftware.spacetattoostudio.domain.model.Caixa;

public interface CaixaService {

	void salvar(Caixa caixa);

	void editar(Caixa caixa);

	void detelar(Long id);
	
	Optional<Caixa> buscarPorId(Long id);

	List<Caixa> buscarTodos();
	
	Caixa getDiaAtual();
	
}
