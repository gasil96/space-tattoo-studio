package br.com.gbsoftware.spacetattoostudio.domain.model;

/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.gbsoftware.spacetattoostudio.domain.EntidadeBase;
import br.com.gbsoftware.spacetattoostudio.domain.enums.FormaPagamentoEnum;

@Entity
@SuppressWarnings("serial")
@Table(name = "ENTRADA_SAIDA")
public class EntradaCaixa extends EntidadeBase<Long> {

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

	@ManyToOne
	@JoinColumn(name = "id_cliente_fk")
	private Cliente cliente;

	public EntradaCaixa() {

	}

	public EntradaCaixa(LocalDateTime horarioOperacao, String descricao, BigDecimal desconto, BigDecimal valor,
			FormaPagamentoEnum formaPagamento, Cliente cliente) {
		super();
		this.horarioOperacao = horarioOperacao;
		this.descricao = descricao;
		this.desconto = desconto;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
		this.cliente = cliente;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "EntradaCaixa [horarioOperacao=" + horarioOperacao + ", descricao=" + descricao + ", desconto="
				+ desconto + ", valor=" + valor + ", formaPagamento=" + formaPagamento + ", cliente=" + cliente + "]";
	}
}
