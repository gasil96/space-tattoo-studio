package br.com.gbsoftware.spacetattoostudio.domain.model;

/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2020 - Criação
 */

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.gbsoftware.spacetattoostudio.domain.EntidadeBase;
import br.com.gbsoftware.spacetattoostudio.domain.enums.FormaPagamentoEnum;

@Entity
@SuppressWarnings("serial")
@Table(name = "SAIDA_CAIXA")
public class SaidaCaixa extends EntidadeBase<Long> {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", locale = "pt-BR", timezone = "America/Belem")
	@DateTimeFormat(iso = ISO.DATE_TIME, pattern = "dd-MM-yyyy HH:mm")
	@Column(name = "HORARIO_OPERACAO", updatable = false)
	private LocalDateTime horarioOperacao;

	@Column(name = "DESCRICAO", length = 65)
	private String descricao;

	@NotNull
	@Column(name = "VALOR", precision = 22, scale = 2)
	private BigDecimal valor;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "FORMA_PAGAMENTO")
	private FormaPagamentoEnum formaPagamento;

	public SaidaCaixa() {

	}

	public SaidaCaixa(LocalDateTime horarioOperacao, String descricao, BigDecimal valor,
			FormaPagamentoEnum formaPagamento) {
		super();
		this.horarioOperacao = horarioOperacao;
		this.descricao = descricao;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
	}

	public LocalDateTime getHorarioOperacao() {
		return horarioOperacao;
	}

	public void setHorarioOperacao(LocalDateTime horarioOperacao) {
		this.horarioOperacao = LocalDateTime.now();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public FormaPagamentoEnum getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamentoEnum formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	@PrePersist
	private void preSalvar() {
		this.setHorarioOperacao(LocalDateTime.now());
	}
	
	@Override
	public String toString() {
		return "SaidaCaixa [horarioOperacao=" + horarioOperacao + ", descricao=" + descricao + ", valor=" + valor
				+ ", formaPagamento=" + formaPagamento + "]";
	}

}
