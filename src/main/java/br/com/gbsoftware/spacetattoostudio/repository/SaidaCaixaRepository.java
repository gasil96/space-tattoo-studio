package br.com.gbsoftware.spacetattoostudio.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gbsoftware.spacetattoostudio.domain.model.SaidaCaixa;

@Repository
public interface SaidaCaixaRepository extends JpaRepository<SaidaCaixa, Long> {

	@Query("select sc from SaidaCaixa sc where day(horarioOperacao) = day(curdate())")
	List<SaidaCaixa> findByDiaAtual();

	@Query("SELECT ec FROM SaidaCaixa ec WHERE DATE_FORMAT(horarioOperacao, '%Y/%m/%d')"
			+ "BETWEEN DATE_FORMAT(?1, '%Y/%m/%d') and DATE_FORMAT(?2, '%Y/%m/%d') ")
	List<SaidaCaixa> findByIntervalo(LocalDateTime inicio, LocalDateTime fim);
	
}
