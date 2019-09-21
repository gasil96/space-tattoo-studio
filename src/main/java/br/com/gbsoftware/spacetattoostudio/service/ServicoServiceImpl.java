package br.com.gbsoftware.spacetattoostudio.service;
/**
 * <b>GB Software</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;
import br.com.gbsoftware.spacetattoostudio.repository.ServicoRepository;
@Service
public class ServicoServiceImpl implements ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;

	@Override
	public void editar(Servico servico) {
		servicoRepository.save(servico);
	}

	public void salvar(Servico servico) {
		servicoRepository.save(servico);
	}

	@Override
	public List<Servico> buscarPorHorarioAgendamento(LocalDateTime horarioAgendamento) {
		return servicoRepository.findByHorarioAgendamento(horarioAgendamento);
	}

	@Override
	public List<Servico> buscarPorTipoServico(TipoServicoEnum tipoServico) {
		return servicoRepository.findByTipoServico(tipoServico);
	}

	@Override
	public List<Servico> buscarPorHorarioConclusaoAgendamento(LocalDateTime horarioConclusaoAgendamento) {
		return servicoRepository.findByHorarioConclusaoAgendamento(horarioConclusaoAgendamento);
	}

	@Override
	public List<Servico> buscarPorStatusAgendamento(StatusServicoEnum statusAgendamento) {
		return servicoRepository.findByStatusAgendamento(statusAgendamento);
	}

	@Override
	public Optional<Servico> buscarPorId(Long id) {
		return servicoRepository.findById(id);
	}

	@Override
	public List<Servico> buscarTodos() {
		return servicoRepository.findAll();
	}

	@Override
	public List<Servico> getAgendamentoPorDia() {
		return servicoRepository.agendamentosDoDia();
	}

	@Override
	public List<Servico> getAgendamentoPorSemana() {
		return servicoRepository.agendamentoDaSemana();
	}

	@Override
	public List<Servico> getAgendamentosProximosTresMeses() {
		return servicoRepository.agendamentosProximosTresMeses();
	}

	@Override
	public List<Servico> getAgendamentosUltimosTresMeses() {
		return servicoRepository.agendametnoUltimosTresMeses();
	}

	@Override
	public List<Servico> getAgendamentosMesAtual() {
		return servicoRepository.agendamentosMesAtual();
	}

	@Override
	public List<Servico> getProximosSeisAgendamentos() {
		return servicoRepository.proximosSeisAgendamentos();
	}

}