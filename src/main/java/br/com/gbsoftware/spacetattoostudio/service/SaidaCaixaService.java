package br.com.gbsoftware.spacetattoostudio.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.util.List;
import java.util.Optional;

import br.com.gbsoftware.spacetattoostudio.domain.model.SaidaCaixa;

public interface SaidaCaixaService {

	void salvar(SaidaCaixa saidaCaixa);

	void alterar(SaidaCaixa saidaCaixa);

	void deletar(Long id);

	Optional<SaidaCaixa> buscarPorId(Long id);

	List<SaidaCaixa> buscarTodos();

	List<SaidaCaixa> buscarTodosDoDia();

	Optional<BigDecimal> sumTotalSaida();

	List<SaidaCaixa> buscarTodosIntervalo(LocalDateTime inicio, LocalDateTime fim);
}
