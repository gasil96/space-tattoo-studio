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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.gbsoftware.spacetattoostudio.domain.EntidadeBase;
import br.com.gbsoftware.spacetattoostudio.domain.enums.CategoriaEntradaEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.FormaPagamentoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoOperacaoEnum;

@Entity
@SuppressWarnings("serial")
@Table(name = "ENTRADA_SAIDA")
public class EntradaCaixa extends EntidadeBase<Long> {

	@Column(name = "horario_operacao")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime horarioOperacao;

	@Column(name = "tipo_operacao")
	@Enumerated(EnumType.STRING)
	private TipoOperacaoEnum tipoOperacao;

	@Column(length = 65)
	private String descricao;

	@Column(precision = 12, scale = 2)
	private BigDecimal valor;

	@Column(precision = 12, scale = 2)
	private BigDecimal desconto;

	@Column(name = "forma_pagamento")
	@Enumerated(EnumType.STRING)
	private FormaPagamentoEnum formaPagamento;

	@Enumerated(EnumType.STRING)
	@Column(name = "categoria_entrada")
	private CategoriaEntradaEnum categoriaEntrada;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_caixa_fk" )
	private Caixa caixa;

	@ManyToOne
	@JoinColumn(name = "id_cliente_fk")
	private Cliente cliente;

	public EntradaCaixa() {

	}

	public EntradaCaixa(LocalDateTime horarioOperacao, TipoOperacaoEnum tipoOperacao, String descricao,
			BigDecimal valor, BigDecimal desconto, FormaPagamentoEnum formaPagamento,
			CategoriaEntradaEnum categoriaEntrada, @NotNull Caixa caixa, Cliente cliente) {
		super();
		this.horarioOperacao = horarioOperacao;
		this.tipoOperacao = tipoOperacao;
		this.descricao = descricao;
		this.valor = valor;
		this.desconto = desconto;
		this.formaPagamento = formaPagamento;
		this.categoriaEntrada = categoriaEntrada;
		this.caixa = caixa;
		this.cliente = cliente;
	}

	public CategoriaEntradaEnum getCategoriaEntrada() {
		return categoriaEntrada;
	}

	public void setCategoriaEntrada(CategoriaEntradaEnum categoriaEntrada) {
		this.categoriaEntrada = categoriaEntrada;
	}

	public LocalDateTime getHorarioOperacao() {
		return horarioOperacao;
	}

	public void setHorarioOperacao(LocalDateTime horarioOperacao) {
		this.horarioOperacao = horarioOperacao;
	}

	public TipoOperacaoEnum getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacaoEnum tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
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

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
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

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	@Override
	public String toString() {
		return "EntradaSaida [horarioOperacao=" + horarioOperacao + ", tipoOperacao=" + tipoOperacao + ", descricao="
				+ descricao + ", valor=" + valor + ", desconto=" + desconto + ", formaPagamento=" + formaPagamento
				+ ", categoriaEntrada=" + categoriaEntrada + ", caixa=" + caixa + ", cliente=" + cliente + "]";
	}
}
