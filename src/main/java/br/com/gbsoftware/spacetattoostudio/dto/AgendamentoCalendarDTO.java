package br.com.gbsoftware.spacetattoostudio.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;

public class AgendamentoCalendarDTO {

	@JsonProperty("title")
	private TipoServicoEnum tipoServico;

	@JsonProperty("categoria")
	private String categoria;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", locale = "pt-BR", timezone = "America/Belem")
	@JsonProperty("start")
	private LocalDateTime horarioAgendamento;

	public AgendamentoCalendarDTO() {

	}

	public AgendamentoCalendarDTO(TipoServicoEnum tipoServico, String categoria, LocalDateTime horarioAgendamento) {
		this.tipoServico = tipoServico;
		this.categoria = categoria;
		this.horarioAgendamento = horarioAgendamento;
	}

	public TipoServicoEnum getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServicoEnum tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public LocalDateTime getHorarioAgendamento() {
		return horarioAgendamento;
	}

	public void setHorarioAgendamento(LocalDateTime horarioAgendamento) {
		this.horarioAgendamento = horarioAgendamento;
	}

	@Override
	public String toString() {
		return "AgendamentoCalendarDTO [tipoServico=" + tipoServico + ", categoria=" + categoria
				+ ", horarioAgendamento=" + horarioAgendamento + "]";
	}

}
