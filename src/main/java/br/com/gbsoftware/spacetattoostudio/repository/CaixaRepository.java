package br.com.gbsoftware.spacetattoostudio.repository;

import java.util.List;

/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gbsoftware.spacetattoostudio.domain.model.Caixa;

public interface CaixaRepository extends JpaRepository<Caixa, Long>{

	
	@Query(value = "select * from caixa where DATE_FORMAT(data_hora_abertura, \"%y/%m/%d\") = date_format(curdate(), \"%y/%m/%d\") limit 1", nativeQuery = true)
	Caixa getCaixaDiaAtual();

	@Query(value = "select *  \r\n" + 
			"from caixa where  date_format(data_hora_fechamento, '%d-%m-%Y') between :dataInicial and :dataFinal", nativeQuery =true )
	List<Caixa> findByInterval(@Param("dataInicial")String dataInicial, @Param("dataFinal") String dataFinal);
	
	@Query( value = "SELECT * FROM caixa WHERE DATE_FORMAT(data_hora_fechamento, '%Y-%m') = :relGeralMensal", nativeQuery = true )
	List<Caixa> findByMes(@Param("relGeralMensal")String relGeralMensal);
}