package br.com.gbsoftware.spacetattoostudio.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
@SuppressWarnings("serial")
@Entity
@Table(name = "CLIENTE")
public class Cliente extends EntidadeBase<Long>{

	@NotBlank
	@Column(nullable = true, length = 65)
	private String nome;
	
	private String telefone;
	
	@Enumerated(EnumType.STRING)
	@NotBlank
	@Column(name = "status", nullable = true, length = 65)
	private StatusClienteEnum statusCliente;
	
	@Column(name = "numero_servicos")
	private Long numeroServicos;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_cadastro")
	private LocalDateTime dataCadastro;
	
	private Double saldo;

	@OneToMany(mappedBy = "cliente")
	private List<Servico> servicos;
	
	public Cliente() {
		
	}

	public Cliente(
			@Size(min = 3, max = 45, message = "Nome deve conter no mínimo 3 caracteres e no máximo 45") String nome,
			String telefone, StatusClienteEnum statusCliente, Long numeroServicos, LocalDateTime dataCadastro,
			Double saldo, List<Servico> servicos) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.statusCliente = statusCliente;
		this.numeroServicos = numeroServicos;
		this.dataCadastro = dataCadastro;
		this.saldo = saldo;
		this.servicos = servicos;
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

	public void setStatusCliente(StatusClienteEnum statusCliente) {
		this.statusCliente = statusCliente;
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

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", telefone=" + telefone + ", statusCliente=" + statusCliente
				+ ", numeroServicos=" + numeroServicos + ", dataCadastro=" + dataCadastro + ", saldo=" + saldo
				+ ", servicos=" + servicos + "]";
	}
	
}
