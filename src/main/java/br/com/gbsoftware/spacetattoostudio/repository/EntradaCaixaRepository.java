package br.com.gbsoftware.spacetattoostudio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaCaixa;

@Repository
public interface EntradaCaixaRepository extends JpaRepository<EntradaCaixa, Long> {

	@Query("select ec from EntradaCaixa ec where day(horarioOperacao) = day(curdate())")
	List<EntradaCaixa> findByDoDia();

}
