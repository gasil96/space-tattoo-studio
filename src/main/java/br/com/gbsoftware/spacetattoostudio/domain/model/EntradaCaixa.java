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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.gbsoftware.spacetattoostudio.domain.EntidadeBase;
import br.com.gbsoftware.spacetattoostudio.domain.enums.FormaPagamentoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;

@Entity
@SuppressWarnings("serial")
@Table(name = "ENTRADA_CAIXA")
public class EntradaCaixa extends EntidadeBase<Long> {

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", locale = "pt-BR", timezone = "America/Belem")
	@DateTimeFormat(iso = ISO.DATE_TIME, pattern = "dd-MM-yyyy HH:mm")
	@Column(name = "HORARIO_OPERACAO")
	private LocalDateTime horarioOperacao;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "SERVICO")
	private TipoServicoEnum tipoServico;

	@Column(name = "descricaoCaixa", length = 65)
	private String descricaoCaixaCaixa;

	@Max(100)
	@Column(name = "PORCENTAGEM_DESCONTO")
	private Integer porcentagemDesconto;

	@Column(name = "VALOR", precision = 12, scale = 2)
	@NotNull
	private BigDecimal valor;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "FORMA_PAGAMENTO")
	private FormaPagamentoEnum formaPagamento;

	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE_FK")
	private Cliente cliente;

	public EntradaCaixa() {

	}

	public EntradaCaixa(LocalDateTime horarioOperacao, @NotNull TipoServicoEnum tipoServico, String descricaoCaixa,
			Integer porcentagemDesconto, @NotNull BigDecimal valor, @NotNull FormaPagamentoEnum formaPagamento,
			Cliente cliente) {
		super();
		this.horarioOperacao = horarioOperacao;
		this.tipoServico = tipoServico;
		this.descricaoCaixaCaixa = descricaoCaixa;
		this.porcentagemDesconto = porcentagemDesconto;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
		this.cliente = cliente;
	}

	public TipoServicoEnum getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServicoEnum tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Integer getPorcentagemDesconto() {
		return porcentagemDesconto;
	}

	public void setPorcentagemDesconto(Integer porcentagemDesconto) {
		this.porcentagemDesconto = porcentagemDesconto;
	}

	public LocalDateTime getHorarioOperacao() {
		return horarioOperacao;
	}

	public void setHorarioOperacao(LocalDateTime horarioOperacao) {
		this.horarioOperacao = horarioOperacao;
	}

	public String getdescricaoCaixa() {
		return descricaoCaixaCaixa;
	}

	public void setdescricaoCaixa(String descricaoCaixa) {
		this.descricaoCaixaCaixa = descricaoCaixa;
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

	@PrePersist
	private void preSalvar() {
		this.setHorarioOperacao(LocalDateTime.now());
		if(this.getPorcentagemDesconto() != null) {
			this.setValor(valorComDesconto(this.getValor(), this.getPorcentagemDesconto()));
		}
	}

	private BigDecimal valorComDesconto(BigDecimal valor, Integer porcentagemDesconto) {
		if(porcentagemDesconto != null) {
			BigDecimal desconto = new BigDecimal(porcentagemDesconto).divide(new BigDecimal(100));
			BigDecimal valorDesconto = valor.multiply(desconto);
			return valor.subtract(valorDesconto);
		}else {
			return valor;
		}
	}
	
	@Override
	public String toString() {
		return "EntradaCaixa [horarioOperacao=" + horarioOperacao + ", tipoServico=" + tipoServico + ", descricaoCaixaCaixa="
				+ descricaoCaixaCaixa + ", porcentagemDesconto=" + porcentagemDesconto + ", valor=" + valor + ", formaPagamento="
				+ formaPagamento + ", cliente=" + cliente + "]";
	}

}
