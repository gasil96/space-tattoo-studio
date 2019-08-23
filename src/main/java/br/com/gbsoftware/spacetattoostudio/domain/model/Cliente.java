package br.com.gbsoftware.spacetattoostudio.domain.model;

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

import br.com.gbsoftware.spacetattoostudio.domain.EntidadeBase;
import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusClienteEnum;

/**
 * 
 * <b>GB Software</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 *
 */
@Entity
@SuppressWarnings("serial")
@Table(name = "CLIENTE")
public class Cliente extends EntidadeBase<Long> {
	/**
	 * TODO Faltando @Notblank's Faltando passar os nullable, lenght's e uniques
	 * 
	 */
	// @NotBlank //impede pessistencia de elementos vazios TODO - FALTA COLOCAR OS
	// NOTNULL @NOTBLACK LENGHT ETC...
	@NotNull
	@Column(length = 65)
	private String nome;

	@Column(length = 30)
	private String telefone;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 65)
	private StatusClienteEnum statusCliente;

	@Column(name = "numero_servicos")
	private Long numeroServicos;

	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_cadastro", nullable = false, updatable = false)
	private LocalDateTime dataCadastro;

	@Column(length = 30)
	private String instagram;

	private Double saldo;

	@OneToMany(mappedBy = "cliente")
	private List<Servico> servicos;

	public Cliente() {

	}

	public Cliente(String nome, String telefone, StatusClienteEnum statusCliente, Long numeroServicos,
			LocalDateTime dataCadastro, String instagram, Double saldo, List<Servico> servicos) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.statusCliente = statusCliente;
		this.numeroServicos = numeroServicos;
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
			this.instagram =  instagram;
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

	public Long getNumeroServicos() {
		return numeroServicos;
	}

	public void setNumeroServicos(Long numeroServicos) {
		this.numeroServicos = numeroServicos;
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
				+ ", numeroServicos=" + numeroServicos + ", dataCadastro=" + dataCadastro + ", saldo=" + saldo
				+ ", servicos=" + servicos + "]";
	}

}
