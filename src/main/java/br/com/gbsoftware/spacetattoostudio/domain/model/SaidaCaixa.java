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
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.gbsoftware.spacetattoostudio.domain.EntidadeBase;
import br.com.gbsoftware.spacetattoostudio.domain.enums.FormaPagamentoEnum;

@Entity
@SuppressWarnings("serial")
@Table(name = "SAIDA_CAIXA")
public class SaidaCaixa extends EntidadeBase<Long> {

	@Column(name = "horario_operacao")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime horarioOperacao;

	@Column(length = 65)
	private String descricao;

	@Column(precision = 12, scale = 2)
	private BigDecimal desconto;

	@Column(precision = 12, scale = 2)
	private BigDecimal valor;

	@Column(name = "forma_pagamento")
	@Enumerated(EnumType.STRING)
	private FormaPagamentoEnum formaPagamento;

	public SaidaCaixa() {

	}

	public SaidaCaixa(LocalDateTime horarioOperacao, String descricao, BigDecimal desconto, BigDecimal valor,
			FormaPagamentoEnum formaPagamento) {
		super();
		this.horarioOperacao = horarioOperacao;
		this.descricao = descricao;
		this.desconto = desconto;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
	}

	public LocalDateTime getHorarioOperacao() {
		return horarioOperacao;
	}

	public void setHorarioOperacao(LocalDateTime horarioOperacao) {
		this.horarioOperacao = horarioOperacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
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

	@Override
	public String toString() {
		return "SaidaCaixa [horarioOperacao=" + horarioOperacao + ", descricao=" + descricao + ", desconto=" + desconto
				+ ", valor=" + valor + ", formaPagamento=" + formaPagamento + "]";
	}

}
