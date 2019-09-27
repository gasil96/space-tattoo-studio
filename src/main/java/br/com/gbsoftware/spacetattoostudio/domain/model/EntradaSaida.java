package br.com.gbsoftware.spacetattoostudio.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.gbsoftware.spacetattoostudio.domain.enums.FormaPagamentoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoOperacaoEnum;

public class EntradaSaida {
	
	private LocalDateTime dataHoraEntrada;
	
	private LocalDateTime dataHoraSaida;
	
	private TipoOperacaoEnum tipoOperacao;
	
	private String nomeEntrada;
	
	private String nomeSaida;
	
	private BigDecimal valorEntrada;
	
	private BigDecimal valorSaida;

	private BigDecimal desconto;
	
	private FormaPagamentoEnum formaPagamento;
	
	private Cliente cliente;
	
	private Caixa caixa;
}
