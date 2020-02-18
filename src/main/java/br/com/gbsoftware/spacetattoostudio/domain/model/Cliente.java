package br.com.gbsoftware.spacetattoostudio.domain.model;

import java.math.BigDecimal;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.gbsoftware.spacetattoostudio.domain.EntidadeBase;
import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusClienteEnum;

@Entity
@SuppressWarnings("serial")
@Table(name = "CLIENTE")
public class Cliente extends EntidadeBase<Long> {

	@Column(length = 50, nullable = false)
	@JsonProperty(value = "nome")
	private String nome;

	@Column(length = 20)
	@JsonProperty(value = "telefone")
	private String telefone;

	@Column(name = "credito_cliente", precision = 12, scale = 2)
	private BigDecimal creditoCliente;

	@JsonIgnore
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 30, nullable = false)
	private StatusClienteEnum statusCliente;

	@DateTimeFormat(iso = ISO.DATE_TIME, pattern = "dd-MM-yyyy HH:mm")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", locale = "pt-BR", timezone = "America/Belem")
	@Column(name = "data_cadastro", nullable = false, updatable = false)
	private LocalDateTime dataCadastro;

	@Column(length = 30)
	@JsonProperty(value = "instagram")
	private String instagram;

	@Column(name = "total_gasto_anual", precision = 12, scale = 2)
	private BigDecimal totalGastoAnual;

	@JsonIgnore
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<Servico> servicos;

	public Cliente() {
	}

	public Cliente(String nome, String telefone, BigDecimal creditoCliente, StatusClienteEnum statusCliente,
			LocalDateTime dataCadastro, String instagram, BigDecimal totalGastoAnual, List<Servico> servicos) {
		this.nome = nome;
		this.telefone = telefone;
		this.creditoCliente = creditoCliente;
		this.statusCliente = statusCliente;
		this.dataCadastro = dataCadastro;
		this.instagram = instagram;
		this.totalGastoAnual = totalGastoAnual;
		this.servicos = servicos;
	}

	public BigDecimal getTotalGastoAnual() {
		return totalGastoAnual;
	}

	public void setTotalGastoAnual(BigDecimal totalGastoAnual) {
		this.totalGastoAnual = totalGastoAnual;
	}

	public BigDecimal getCreditoCliente() {
		return creditoCliente;
	}

	public void setCreditoCliente(BigDecimal creditoCliente) {
		this.creditoCliente = creditoCliente;
	}

	public void setStatusCliente(StatusClienteEnum statusCliente) {
		this.statusCliente = statusCliente;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public StatusClienteEnum getStatusCliente() {
		return statusCliente;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	@PrePersist
	public void preSalvar() {
		this.setDataCadastro(LocalDateTime.now());
	}

}
