package br.com.gbsoftware.spacetattoostudio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gbsoftware.spacetattoostudio.domain.model.SaidaCaixa;

@Repository
public interface SaidaCaixaRepository extends JpaRepository<SaidaCaixa, Long> {

	@Query("select sc from SaidaCaixa sc where day(horarioOperacao) = day(curdate())")
	List<SaidaCaixa> findByDiaAtual();

}
