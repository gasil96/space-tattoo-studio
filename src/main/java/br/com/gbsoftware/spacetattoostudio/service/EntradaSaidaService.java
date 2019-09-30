package br.com.gbsoftware.spacetattoostudio.service;

import java.util.List;
import java.util.Optional;

import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaSaida;

public interface EntradaSaidaService {

	void salvar(EntradaSaida entradaSaida);

	void editar(EntradaSaida entradaSaida);

	void deletar(Long id);

	Optional<EntradaSaida> buscarPorId(Long id);

	List<EntradaSaida> buscarTodos();

}
