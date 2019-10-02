package br.com.gbsoftware.spacetattoostudio.domain.model;

/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.gbsoftware.spacetattoostudio.domain.EntidadeBase;

@Entity
@Table(name = "CAIXA")
@SuppressWarnings("serial")
public class Caixa extends EntidadeBase<Long> {

	@DateTimeFormat(iso = ISO.DATE_TIME, pattern = "dd-MM-yyyy HH:mm")
	@Column(name = "data_hora_abertura", updatable = false)
	private LocalDateTime dataHoraAbertura;

	@DateTimeFormat(iso = ISO.DATE_TIME, pattern = "dd-MM-yyyy HH:mm")
	@Column(name = "data_hora_fechamento")
	private LocalDateTime dataHoraFechamento;

	@Column(length = 65, precision = 12, scale = 2)
	private BigDecimal total;
 
	@NotNull
	@Column(name = "operador_abertura", updatable = false, length = 30)
	private String operadorAbertura;

	@Column(name = "operador_fechamento", length = 30)
	private String operadorFechamento;

	@Type(type="true_false") 
	private Boolean aberto;

	@OneToMany(mappedBy = "caixa")
	private List<EntradaSaida> entradaSaida;

	public Caixa() {

	}

	public Caixa(LocalDateTime dataHoraAbertura, LocalDateTime dataHoraFechamento, BigDecimal total,
			@NotNull String operadorAbertura, @NotNull String operadorFechamento, Boolean aberto,
			List<EntradaSaida> entradaSaida) {
		super();
		this.dataHoraAbertura = dataHoraAbertura;
		this.dataHoraFechamento = dataHoraFechamento;
		this.total = total;
		this.operadorAbertura = operadorAbertura;
		this.operadorFechamento = operadorFechamento;
		this.aberto = aberto;
		this.entradaSaida = entradaSaida;
	}

	public Boolean getAberto() {
		return aberto;
	}

	public void setAberto(Boolean aberto) {
		this.aberto = aberto;
	}

	public LocalDateTime getDataHoraAbertura() {
		return dataHoraAbertura;
	}

	public void setDataHoraAbertura(LocalDateTime dataHoraAbertura) {
		this.dataHoraAbertura = dataHoraAbertura;
	}

	public LocalDateTime getDataHoraFechamento() {
		return dataHoraFechamento;
	}

	public void setDataHoraFechamento(LocalDateTime dataHoraFechamento) {
		this.dataHoraFechamento = dataHoraFechamento;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getOperadorAbertura() {
		return operadorAbertura;
	}

	public void setOperadorAbertura(String operadorAbertura) {
		this.operadorAbertura = operadorAbertura;
	}

	public String getOperadorFechamento() {
		return operadorFechamento;
	}

	public void setOperadorFechamento(String operadorFechamento) {
		this.operadorFechamento = operadorFechamento;
	}

	public List<EntradaSaida> getEntradaSaida() {
		return entradaSaida;
	}

	public void setEntradaSaida(List<EntradaSaida> entradaSaida) {
		this.entradaSaida = entradaSaida;
	}

	@Override
	public String toString() {
		return "Caixa [dataHoraAbertura=" + dataHoraAbertura + ", dataHoraFechamento=" + dataHoraFechamento + ", total="
				+ total + ", operadorAbertura=" + operadorAbertura + ", operadorFechamento=" + operadorFechamento
				+ ", aberto=" + aberto + ", entradaSaida=" + entradaSaida + "]";
	}

}
