package br.com.gbsoftware.spacetattoostudio.service;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.util.List;
import java.util.Optional;

import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaSaida;

public interface EntradaSaidaService {

	void salvar(EntradaSaida entradaSaida);

	void editar(EntradaSaida entradaSaida);

	void deletar(Long id);

	Optional<EntradaSaida> buscarPorId(Long id);

	List<EntradaSaida> buscarTodos();
	
	List<EntradaSaida> busarTodosDoDia(Long iDCaixaFK);

	void addGastoTotalCliente(EntradaSaida entradaSaida);

	void removeGastoTotalCliente(EntradaSaida entradaSaida);

}
