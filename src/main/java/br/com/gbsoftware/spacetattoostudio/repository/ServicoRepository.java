package br.com.gbsoftware.spacetattoostudio.repository;

/**
 * <b>GB Software</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

	List<Servico> findByHorarioAgendamento(LocalDateTime horarioAgendamento);

	List<Servico> findByTipoServico(TipoServicoEnum tipoServico);

	List<Servico> findByStatusAgendamento(StatusServicoEnum statusAgendamento);

	List<Servico> findByHorarioConclusaoAgendamento(LocalDateTime horarioConclusaoAgendamento);

	@Query(value = "SELECT * FROM servico WHERE date_format(horario_agendamento, '%Y-%m-%d') = CURDATE()", nativeQuery = true)
	List<Servico> agendamentosDoDia();

	@Query(value = "SELECT * FROM servico WHERE DATE_FORMAT(horario_agendamento, '%Y-%m-%d') "
					+ "BETWEEN CURDATE() AND DATE_ADD(NOW(), INTERVAL 7 DAY)", nativeQuery = true)
	List<Servico> agendamentoDaSemana();

	@Query(value = "SELECT * FROM servico WHERE horario_agendamento BETWEEN CURRENT_TIMESTAMP()"
					+ " AND DATE_ADD(NOW(), INTERVAL 1 DAY) LIMIT 6", nativeQuery = true)
	List<Servico> proximosTresAgendamentos();
}
