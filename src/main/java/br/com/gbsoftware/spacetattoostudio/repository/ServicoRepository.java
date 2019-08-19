package br.com.gbsoftware.spacetattoostudio.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;

/**
 * 
 * <b>GB Software</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 *
 */
@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

	List<Servico> findByHorarioAgendamento(LocalDateTime horarioAgendamento);

	List<Servico> findByTipoServico(TipoServicoEnum tipoServico);

	List<Servico> findByStatusAgendamento(StatusServicoEnum statusAgendamento);

	List<Servico> findByHorarioConclusaoAgendamento(LocalDateTime horarioConclusaoAgendamento);

	
	@Override
	default boolean existsById(Long id) {
		return false;
	}
}
