package br.com.gbsoftware.spacetattoostudio.domain.vw;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import br.com.gbsoftware.spacetattoostudio.domain.EntidadeBase;

@Immutable
@Entity
@Table(name = "VW_ARRECADAO_ANUAL_TIPO")
public class VwArrecadacaoAnualTipo extends EntidadeBase<Long> {

	private static final long serialVersionUID = 7291256242033325398L;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "ARRECADACAO_TOTAL_JANEIRO")
	private BigDecimal jan;

	@Column(name = "ARRECADACAO_TOTAL_FEVEREIRO")
	private BigDecimal fev;

	@Column(name = "ARRECADACAO_TOTAL_MARCO")
	private BigDecimal mar;

	@Column(name = "ARRECADACAO_TOTAL_ABRIL")
	private BigDecimal abr;

	@Column(name = "ARRECADACAO_TOTAL_MAIO")
	private BigDecimal mai;

	@Column(name = "ARRECADACAO_TOTAL_JUNHO")
	private BigDecimal jun;

	@Column(name = "ARRECADACAO_TOTAL_JULHO")
	private BigDecimal jul;

	@Column(name = "ARRECADACAO_TOTAL_AGOSTO")
	private BigDecimal ago;

	@Column(name = "ARRECADACAO_TOTAL_SETEMBRO")
	private BigDecimal set;

	@Column(name = "ARRECADACAO_TOTAL_OUTUBRO")
	private BigDecimal out;

	@Column(name = "ARRECADACAO_TOTAL_NOVEMBRO")
	private BigDecimal nov;

	@Column(name = "ARRECADACAO_TOTAL_DEZEMBRO")
	private BigDecimal dez;

	public VwArrecadacaoAnualTipo() {

	}

	public VwArrecadacaoAnualTipo(String tipo, BigDecimal jan, BigDecimal fev, BigDecimal mar, BigDecimal abr,
			BigDecimal mai, BigDecimal jun, BigDecimal jul, BigDecimal ago, BigDecimal set, BigDecimal out,
			BigDecimal nov, BigDecimal dez) {
		this.tipo = tipo;
		this.jan = jan;
		this.fev = fev;
		this.mar = mar;
		this.abr = abr;
		this.mai = mai;
		this.jun = jun;
		this.jul = jul;
		this.ago = ago;
		this.set = set;
		this.out = out;
		this.nov = nov;
		this.dez = dez;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getJan() {
		return jan;
	}

	public void setJan(BigDecimal jan) {
		this.jan = jan;
	}

	public BigDecimal getFev() {
		return fev;
	}

	public void setFev(BigDecimal fev) {
		this.fev = fev;
	}

	public BigDecimal getMar() {
		return mar;
	}

	public void setMar(BigDecimal mar) {
		this.mar = mar;
	}

	public BigDecimal getAbr() {
		return abr;
	}

	public void setAbr(BigDecimal abr) {
		this.abr = abr;
	}

	public BigDecimal getMai() {
		return mai;
	}

	public void setMai(BigDecimal mai) {
		this.mai = mai;
	}

	public BigDecimal getJun() {
		return jun;
	}

	public void setJun(BigDecimal jun) {
		this.jun = jun;
	}

	public BigDecimal getJul() {
		return jul;
	}

	public void setJul(BigDecimal jul) {
		this.jul = jul;
	}

	public BigDecimal getAgo() {
		return ago;
	}

	public void setAgo(BigDecimal ago) {
		this.ago = ago;
	}

	public BigDecimal getSet() {
		return set;
	}

	public void setSet(BigDecimal set) {
		this.set = set;
	}

	public BigDecimal getOut() {
		return out;
	}

	public void setOut(BigDecimal out) {
		this.out = out;
	}

	public BigDecimal getNov() {
		return nov;
	}

	public void setNov(BigDecimal nov) {
		this.nov = nov;
	}

	public BigDecimal getDez() {
		return dez;
	}

	public void setDez(BigDecimal dez) {
		this.dez = dez;
	}

	@Override
	public String toString() {
		return "VwArrecadacaoAnualTipoService [tipo=" + tipo + ", jan=" + jan + ", fev=" + fev + ", mar=" + mar
				+ ", abr=" + abr + ", mai=" + mai + ", jun=" + jun + ", jul=" + jul + ", ago=" + ago + ", set=" + set
				+ ", out=" + out + ", nov=" + nov + ", dez=" + dez + "]";
	}

}
