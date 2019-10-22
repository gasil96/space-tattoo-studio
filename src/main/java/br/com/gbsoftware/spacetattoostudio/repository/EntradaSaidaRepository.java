package br.com.gbsoftware.spacetattoostudio.repository;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaSaida;

@Repository
public interface EntradaSaidaRepository extends JpaRepository<EntradaSaida, Long> {

	@Query(value = "select  "+ 
			"es.id, es.tipo_operacao, es.valor, es.desconto, es.forma_pagamento, " + 
			"es.descricao, es.categoria_entrada , c.nome, es.id_caixa_fk, es.id_cliente_fk, "+
			"es.horario_operacao " + 
			"from entrada_saida es " + 
			"inner join cliente c on es.id_cliente_fk = c.id " + 
			"where es.id_caixa_fk = :iDCaixaFK", nativeQuery = true)
	List<EntradaSaida> findByEntradaSaida(@Param("iDCaixaFK") Long iDCaixaFK);
	
	@Query(value = "select *  \r\n" + 
			"from entrada_saida where  date_format(horario_operacao, '%d-%m-%Y') between :dataInicial and :dataFinal", nativeQuery =true )
	List<EntradaSaida> findByInterval(@Param("dataInicial")String dataInicial, @Param("dataFinal") String dataFinal);
	
}
