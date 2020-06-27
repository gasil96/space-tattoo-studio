package br.com.gbsoftware.spacetattoostudio.repository;

import java.math.BigDecimal;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

	@Query("select s.horarioAgendamento, s.categoria, s.tipoServico from Servico s")
	List<Servico> getCaledarIO();

	@Query("select c.id, c.nome, sum(s.orcamento) as totalGasto from Servico s, Cliente c where c.id = ?2")
	Optional<Servico> getCalculoOrcamentoCliente(BigDecimal totalGasto, Long id);
}
