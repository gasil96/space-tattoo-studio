package br.com.gbsoftware.spacetattoostudio.service;

/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.util.List;
import java.util.Optional;

import br.com.gbsoftware.spacetattoostudio.domain.model.Caixa;

public interface CaixaService {

	void salvar(Caixa caixa);

	void editar(Caixa caixa);

	void detelar(Long id);

	Optional<Caixa> buscarPorId(Long id);

	List<Caixa> buscarTodos();

}
