package br.com.gbsoftware.spacetattoostudio.domain.enums;

public enum TipoOperacaoEnum {

	ENTRADA("ENTRADA"),
	SAIDA("SAIDA");
	
	private String operacao;
	
	private TipoOperacaoEnum(String operacao) {
		this.operacao = operacao;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	
}
