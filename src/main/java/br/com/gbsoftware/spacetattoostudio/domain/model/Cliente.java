package br.com.gbsoftware.spacetattoostudio.domain.model;

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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.gbsoftware.spacetattoostudio.domain.EntidadeBase;
import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusClienteEnum;

@Entity
@SuppressWarnings("serial")
@Table(name = "CLIENTE")
public class Cliente extends EntidadeBase<Long> {

	@NotNull
	@Column(length = 50)
	@JsonProperty(value="nome")
	private String nome;

	@Column(length = 20)
	@JsonProperty(value="telefone")
	private String telefone;

	@JsonIgnore
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 30)
	private StatusClienteEnum statusCliente;

	@JsonIgnore
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_cadastro", nullable = false, updatable = false)
	private LocalDateTime dataCadastro;

	@Column(length = 30)
	@JsonProperty(value="instagram")
	private String instagram;

	@JsonIgnore
	private Double saldo;

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Servico> servicos;

	public Cliente() {
	}

	public Cliente(String nome, String telefone, StatusClienteEnum statusCliente, LocalDateTime dataCadastro,
			String instagram, Double saldo, List<Servico> servicos) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.statusCliente = statusCliente;
		this.dataCadastro = dataCadastro;
		this.instagram = instagram;
		this.saldo = saldo;
		this.servicos = servicos;
	}

	public Cliente(String nome, String telefone, StatusClienteEnum statusCliente, String instagram) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.statusCliente = statusCliente;
		this.instagram = instagram;
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

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
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

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", telefone=" + telefone + ", statusCliente=" + statusCliente
				+ ", dataCadastro=" + dataCadastro + ", saldo=" + saldo + "]";
	}
}
