package br.com.gbsoftware.spacetattoostudio.service;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.util.List;
import java.util.Optional;

import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaCaixa;

public interface EntradaCaixaService {

	void salvar(EntradaCaixa entradaSaida);

	void editar(EntradaCaixa entradaSaida);

	void deletar(Long id);

	Optional<EntradaCaixa> buscarPorId(Long id);

	List<EntradaCaixa> buscarTodos();
	
	List<EntradaCaixa> busarTodosDoDia(Long iDCaixaFK);

	List<EntradaCaixa> buscarPorIntervalo(String dataInicial, String dataFinal);
}
