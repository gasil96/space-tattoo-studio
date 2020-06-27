package br.com.gbsoftware.spacetattoostudio.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusClienteEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;

public class ClienteDTO {

	private Long id;

	private String nome;

	private String telefone;

	private BigDecimal creditoCliente;

	private StatusClienteEnum statusCliente;

	private LocalDateTime dataCadastro;

	private String instagram;

	private BigDecimal saldo;

	private BigDecimal gastoAnual;

	@JsonIgnore
	private List<Servico> servicos;

	public ClienteDTO() {

	}

	public ClienteDTO(Long id, String nome, String telefone, BigDecimal creditoCliente, StatusClienteEnum statusCliente,
			LocalDateTime dataCadastro, String instagram, BigDecimal saldo, BigDecimal gastoAnual,
			List<Servico> servicos) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.creditoCliente = creditoCliente;
		this.statusCliente = statusCliente;
		this.dataCadastro = dataCadastro;
		this.instagram = instagram;
		this.saldo = saldo;
		this.gastoAnual = gastoAnual;
		this.servicos = servicos;
	}

	public BigDecimal getGastoAnual() {
		return gastoAnual;
	}

	public void setGastoAnual(BigDecimal gastoAnual) {
		this.gastoAnual = gastoAnual;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public BigDecimal getCreditoCliente() {
		return creditoCliente;
	}

	public void setCreditoCliente(BigDecimal creditoCliente) {
		this.creditoCliente = creditoCliente;
	}

	public StatusClienteEnum getStatusCliente() {
		return statusCliente;
	}

	public void setStatusCliente(StatusClienteEnum statusCliente) {
		this.statusCliente = statusCliente;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
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
		return "ClienteDTO [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", creditoCliente="
				+ creditoCliente + ", statusCliente=" + statusCliente + ", dataCadastro=" + dataCadastro
				+ ", instagram=" + instagram + ", saldo=" + saldo + ", gastoAnual=" + gastoAnual + ", servicos="
				+ servicos + "]";
	}

}
