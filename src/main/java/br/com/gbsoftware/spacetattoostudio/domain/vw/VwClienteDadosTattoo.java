package br.com.gbsoftware.spacetattoostudio.domain.vw;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.gbsoftware.spacetattoostudio.domain.EntidadeBase;

@Immutable
@Entity
@Table(name = "VW_CLIENTE_DADOS_TATTOO")
public class VwClienteDadosTattoo extends EntidadeBase<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4821792458055991136L;

	@Column(length = 50, nullable = false)
	@JsonProperty(value = "nome")
	private String nome;
	
	@Column(name = "credito_cliente", precision = 12, scale = 2)
	private BigDecimal creditoCliente;

	@DateTimeFormat(iso = ISO.DATE_TIME, pattern = "dd-MM-yyyy HH:mm")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", locale = "pt-BR", timezone = "America/Belem")
	@Column(name = "data_cadastro", nullable = false, updatable = false)
	private LocalDateTime dataCadastro;
	
	@Column(length = 20)
	@JsonProperty(value = "telefone")
	private String telefone;
	
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "TOTAL_GASTO_ANUAL_TATTOO", precision = 12, scale = 2)
	private BigDecimal totalGastoAnual;
	
	@Column(name = "NUMERO_AGENDAMENTOS_TATTOO", precision = 12, scale = 2)
	private Integer numeroAgendamentos;

	public VwClienteDadosTattoo() {
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getCreditoCliente() {
		return creditoCliente;
	}

	public void setCreditoCliente(BigDecimal creditoCliente) {
		this.creditoCliente = creditoCliente;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getTotalGastoAnual() {
		return totalGastoAnual;
	}

	public void setTotalGastoAnual(BigDecimal totalGastoAnual) {
		this.totalGastoAnual = totalGastoAnual;
	}

	public Integer getNumeroAgendamentos() {
		return numeroAgendamentos;
	}

	public void setNumeroAgendamentos(Integer numeroAgendamentos) {
		this.numeroAgendamentos = numeroAgendamentos;
	}

	
	
}
