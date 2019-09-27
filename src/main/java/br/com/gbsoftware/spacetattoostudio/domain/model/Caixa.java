package br.com.gbsoftware.spacetattoostudio.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Caixa {

	private LocalDateTime dataHoraAbertura;
	
	private LocalDateTime dataHoraFechamento;
	
	private BigDecimal totalAvista;
	
	private BigDecimal totalCredito;
	
	private BigDecimal totalDebito;
	
	private BigDecimal total;
	
	private List<EntradaSaida> entradaSaida;
	
}
