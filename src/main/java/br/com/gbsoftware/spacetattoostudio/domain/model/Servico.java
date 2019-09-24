package br.com.gbsoftware.spacetattoostudio.domain.model;

import java.math.BigDecimal;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import br.com.gbsoftware.spacetattoostudio.domain.EntidadeBase;
import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;

@SuppressWarnings("serial")
@Entity
@Table(name = "SERVICO")
public class Servico extends EntidadeBase<Long> {

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", length = 65)
	@JsonProperty(value = "title")
	private TipoServicoEnum tipoServico;

	@Column(length = 40)
	private String categoria;

	@JsonProperty(value = "start")
	@DateTimeFormat(iso = ISO.DATE_TIME, pattern = "dd-MM-yyyy HH:mm")
	@Column(name = "horario_agendamento", nullable = false)
	private LocalDateTime horarioAgendamento;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "horario_conclusao_agendamento")
	@JsonIgnore
	private LocalDateTime horarioConclusaoAgendamento;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status_agendamento", length = 65)
	@JsonIgnore
	private StatusServicoEnum statusAgendamento;

	@Column(name = "valor_orcamento", length = 65, precision = 12, scale = 2)
	private BigDecimal orcamento;

	@Column(name = "numero_sessoes", length = 3)
	private Integer numeroSessoes;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_cliente_fk")
	@JsonIgnore
	private Cliente cliente;

	public Servico() {

	}

	public Servico(@NotNull TipoServicoEnum tipoServico, String categoria, LocalDateTime horarioAgendamento,
			LocalDateTime horarioConclusaoAgendamento, @NotNull StatusServicoEnum statusAgendamento,
			BigDecimal orcamento, Integer numeroSessoes, @NotNull Cliente cliente) {
		super();
		this.tipoServico = tipoServico;
		this.categoria = categoria;
		this.horarioAgendamento = horarioAgendamento;
		this.horarioConclusaoAgendamento = horarioConclusaoAgendamento;
		this.statusAgendamento = statusAgendamento;
		this.orcamento = orcamento;
		this.numeroSessoes = numeroSessoes;
		this.cliente = cliente;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(BigDecimal orcamento) {
		this.orcamento = orcamento;
	}

	public Integer getNumeroSessoes() {
		return numeroSessoes;
	}

	public void setNumeroSessoes(Integer numeroSessoes) {
		this.numeroSessoes = numeroSessoes;
	}

	public Servico(@NotNull TipoServicoEnum tipoServico) {
		super();
		this.tipoServico = tipoServico;
	}

	public Servico(@NotNull TipoServicoEnum tipoServico, LocalDateTime horarioAgendamento) {
		super();
		this.tipoServico = tipoServico;
		this.horarioAgendamento = horarioAgendamento;
	}

	public LocalDateTime getHorarioAgendamento() {
		return horarioAgendamento;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	public void setHorarioAgendamento(LocalDateTime horarioAgendamento) {
		this.horarioAgendamento = horarioAgendamento;
	}

	public TipoServicoEnum getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServicoEnum tipoServico) {
		this.tipoServico = tipoServico;
	}

	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDateTime getHorarioConclusaoAgendamento() {
		return horarioConclusaoAgendamento;
	}

	public void setHorarioConclusaoAgendamento(LocalDateTime horarioConclusaoAgendamento) {
		this.horarioConclusaoAgendamento = horarioConclusaoAgendamento;
	}

	public StatusServicoEnum getStatusAgendamento() {
		return statusAgendamento;
	}

	public void setStatusAgendamento(StatusServicoEnum statusAgendamento) {
		this.statusAgendamento = statusAgendamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Servico [tipoServico=" + tipoServico + ", categoria=" + categoria + ", horarioAgendamento="
				+ horarioAgendamento + ", horarioConclusaoAgendamento=" + horarioConclusaoAgendamento
				+ ", statusAgendamento=" + statusAgendamento + ", orcamento=" + orcamento + ", numeroSessoes="
				+ numeroSessoes + ", cliente=" + cliente + "]";
	}

}
