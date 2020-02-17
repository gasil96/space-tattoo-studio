package br.com.gbsoftware.spacetattoostudio.service;
import java.math.BigDecimal;
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

	void alterar(EntradaCaixa entradaSaida);

	void deletar(Long id);

	Optional<EntradaCaixa> buscarPorId(Long id);

	List<EntradaCaixa> buscarTodos();

	List<EntradaCaixa> buscarTodosDoDia();
	
	Optional<BigDecimal> sumTotalEntrada();
}
