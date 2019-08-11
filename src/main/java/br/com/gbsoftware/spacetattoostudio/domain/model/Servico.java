package br.com.gbsoftware.spacetattoostudio.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;

@Entity
@Table(name = "SERVICO")
public class Servico implements Serializable{

	private static final long serialVersionUID = 8942989251660620714L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_servico")
	private Long idServico;
	
	@Column(name = "tipo", nullable = true)
	private TipoServicoEnum tipoServico;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "horario_agendamento")
	private LocalDateTime horarioAgendametno;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "horario_conclusao_agendamento")
	private LocalDateTime horarioConclusaoAgendamento;
	
	@Column(name = "status")
	private StatusServicoEnum statusAgendamento;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_cliente_fk")
	private Cliente cliente;
	
	public Servico() {
		
	}

	public Servico(Long idServico, TipoServicoEnum tipoServico, LocalDateTime horarioAgendametno,
			LocalDateTime horarioConclusaoAgendamento, StatusServicoEnum statusAgendamento, @NotNull Cliente cliente) {
		super();
		this.idServico = idServico;
		this.tipoServico = tipoServico;
		this.horarioAgendametno = horarioAgendametno;
		this.horarioConclusaoAgendamento = horarioConclusaoAgendamento;
		this.statusAgendamento = statusAgendamento;
		this.cliente = cliente;
	}

	public Long getIdServico() {
		return idServico;
	}

	public void setIdServico(Long idServico) {
		this.idServico = idServico;
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
		return "Servico [idServico=" + idServico + ", tipoServico=" + tipoServico + ", horarioAgendametno="
				+ horarioAgendametno + ", horarioConclusaoAgendamento=" + horarioConclusaoAgendamento
				+ ", statusAgendamento=" + statusAgendamento + ", cliente=" + cliente + "]";
	}
	
}
