package br.com.gbsoftware.spacetattoostudio.service;

/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;

public interface ServicoService {

	void salvar(Servico servico);

	void editar(Servico servico);

	Optional<Servico> buscarPorId(Long id);

	List<Servico> buscarTodos();

	List<Servico> buscarPorHorarioAgendamento(LocalDateTime horarioAgendamento);

	List<Servico> buscarPorTipoServico(TipoServicoEnum tipoServico);

	List<Servico> buscarPorHorarioConclusaoAgendamento(LocalDateTime horarioConclusaoAgendamento);

	List<Servico> buscarPorStatusAgendamento(StatusServicoEnum statusAgendamento);

	List<Servico> getAgendamentoPorDia();

	List<Servico> getAgendamentoPorSemana();

	List<Servico> getAgendamentosProximosTresMeses();

	List<Servico> getAgendamentosUltimosTresMeses();

	List<Servico> getAgendamentosMesAtual();

	List<Servico> getProximosSeisAgendamentos();

	List<Servico> encerramentoMesAtual();

}
