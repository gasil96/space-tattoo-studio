package br.com.gbsoftware.spacetattoostudio.service;

/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
		return servicoRepository.findAll().stream()
				.sorted(Comparator.comparing(Servico::getHorarioAgendamento).reversed()).collect(Collectors.toList());
	}

	@Override
	public List<Servico> getAgendamentosProximosTresMeses() {
		return buscarTodos().stream()
				.filter(x -> x.getHorarioAgendamento().isAfter(LocalDateTime.now())
						&& x.getHorarioAgendamento().isBefore(LocalDateTime.now().plusMonths(3)))
				.collect(Collectors.toList());
	}

	@Override
	public List<Servico> getAgendamentosUltimosTresMeses() {
		return buscarTodos().stream().filter(x -> x.getHorarioAgendamento().isAfter(LocalDateTime.now().plusMonths(-3))
				&& x.getHorarioAgendamento().isBefore(LocalDateTime.now())).collect(Collectors.toList());
	}

	@Override
	public List<Servico> getAgendamentosMesAtual() {
		return this.buscarTodos().stream()
				.filter(x -> x.getHorarioAgendamento().getMonthValue() == LocalDateTime.now().getMonthValue())
				.collect(Collectors.toList());
	}

	@Override
	public List<Servico> encerramentoMesAtual() {
		return this.buscarTodos().stream()
				.filter(x -> x.getHorarioAgendamento().getMonthValue() == LocalDateTime.now().getMonthValue()
						&& x.getStatusAgendamento().equals(StatusServicoEnum.ENCERRADO))
				.collect(Collectors.toList());
	}

	@Override
	public List<Servico> getCalendarIO() {
		return servicoRepository.getCaledarIO();
	}

}
