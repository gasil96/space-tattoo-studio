package br.com.gbsoftware.spacetattoostudio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;


public interface ServicoService {

	void salvar(Servico servico);

	void editar(Servico servico);

	void excluir(Long id);

	Optional<Servico> buscarPorId(Long id);
	
	List<Servico> buscarTodos();
	
	List<Servico> buscarPorHorarioAgendamento(LocalDateTime horarioAgendamento);

	List<Servico> buscarPorTipoServico(TipoServicoEnum tipoServico);
	
	List<Servico> buscarPorHorarioConclusaoAgendamento(LocalDateTime horarioConclusaoAgendamento);
	
	List<Servico> buscarPorStatusAgendamento(StatusServicoEnum statusAgendamento);
}
