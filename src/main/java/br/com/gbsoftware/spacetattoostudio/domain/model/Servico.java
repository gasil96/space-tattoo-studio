package br.com.gbsoftware.spacetattoostudio.domain.model;

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
import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;

@SuppressWarnings("serial")
@Entity
@Table(name = "SERVICO")
public class Servico extends EntidadeBase<Long>{


	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = true, length = 65)
	private TipoServicoEnum tipoServico;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "horario_agendamento")
	private LocalDateTime horarioAgendametno;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "horario_conclusao_agendamento")
	private LocalDateTime horarioConclusaoAgendamento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_agendamento", length = 65)
	private StatusServicoEnum statusAgendamento;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_cliente_fk")
	private Cliente cliente;
	
	public Servico() {
		
	}

	public Servico(TipoServicoEnum tipoServico, LocalDateTime horarioAgendametno,
			LocalDateTime horarioConclusaoAgendamento, StatusServicoEnum statusAgendamento, @NotNull Cliente cliente) {
		super();
		this.tipoServico = tipoServico;
		this.horarioAgendametno = horarioAgendametno;
		this.horarioConclusaoAgendamento = horarioConclusaoAgendamento;
		this.statusAgendamento = statusAgendamento;
		this.cliente = cliente;
	}


	public TipoServicoEnum getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServicoEnum tipoServico) {
		this.tipoServico = tipoServico;
	}

	public LocalDateTime getHorarioAgendametno() {
		return horarioAgendametno;
	}

	public void setHorarioAgendametno(LocalDateTime horarioAgendametno) {
		this.horarioAgendametno = horarioAgendametno;
	}

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
		return "Servico [tipoServico=" + tipoServico + ", horarioAgendametno="
				+ horarioAgendametno + ", horarioConclusaoAgendamento=" + horarioConclusaoAgendamento
				+ ", statusAgendamento=" + statusAgendamento + ", cliente=" + cliente + "]";
	}
	
}
