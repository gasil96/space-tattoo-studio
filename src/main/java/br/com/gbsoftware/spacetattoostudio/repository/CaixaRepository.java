package br.com.gbsoftware.spacetattoostudio.repository;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gbsoftware.spacetattoostudio.domain.model.Caixa;

public interface CaixaRepository extends JpaRepository<Caixa, Long>{

	
	@Query(value = "select * from caixa where DATE_FORMAT(data_hora_abertura, \"%y/%m/%d\") = date_format(curdate(), \"%y/%m/%d\") limit 1", nativeQuery = true)
	Caixa getCaixaDiaAtual();
}
