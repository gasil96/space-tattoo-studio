package br.com.gbsoftware.spacetattoostudio.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaCaixa;

@Repository
public interface EntradaCaixaRepository extends JpaRepository<EntradaCaixa, Long> {

	@Query("select ec from EntradaCaixa ec where day(horarioOperacao) = day(curdate())")
	List<EntradaCaixa> findByDoDia();

	@Query("SELECT ec FROM EntradaCaixa ec WHERE DATE_FORMAT(horarioOperacao, '%Y/%m/%d')"
			+ "BETWEEN DATE_FORMAT(?1, '%Y/%m/%d') and DATE_FORMAT(?2, '%Y/%m/%d') ")
	List<EntradaCaixa> findByIntervalo(LocalDateTime inicio, LocalDateTime fim);
}
