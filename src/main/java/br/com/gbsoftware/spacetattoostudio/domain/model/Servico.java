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
/**
 * 
 * <b>GB Software</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SERVICO")
public class Servico extends EntidadeBase<Long>{
	/**
	 * TODO
	 * Faltando @Notblank's
	 * Faltando passar os nullable, lenght's e uniques 
	 * 
	 * */

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", length = 65)
	private TipoServicoEnum tipoServico;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "horario_agendamento", nullable=false)
	private LocalDateTime horarioAgendamento;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "horario_conclusao_agendamento")
	private LocalDateTime horarioConclusaoAgendamento;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status_agendamento", length = 65)
	private StatusServicoEnum statusAgendamento;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_cliente_fk")
	private Cliente cliente;
	
	public Servico() {
		
	}

	public Servico(TipoServicoEnum tipoServico, LocalDateTime horarioAgendamento,
			LocalDateTime horarioConclusaoAgendamento, StatusServicoEnum statusAgendamento, @NotNull Cliente cliente) {
		super();
		this.tipoServico = tipoServico;
		this.horarioAgendamento = horarioAgendamento;
		this.horarioConclusaoAgendamento = horarioConclusaoAgendamento;
		this.statusAgendamento = statusAgendamento;
		this.cliente = cliente;
	}



	public LocalDateTime getHorarioAgendamento() {
		return horarioAgendamento;
	}

	public void setHorarioAgendamento(LocalDateTime horarioAgendamento) {
		this.horarioAgendamento = horarioAgendamento;
	}

	public TipoServicoEnum getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServicoEnum tipoServico) {
		this.tipoServico = tipoServico;
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
		return "Servico [tipoServico=" + tipoServico + ", horarioAgendamento="
				+ horarioAgendamento + ", horarioConclusaoAgendamento=" + horarioConclusaoAgendamento
				+ ", statusAgendamento=" + statusAgendamento + ", cliente=" + cliente + "]";
	}

}
