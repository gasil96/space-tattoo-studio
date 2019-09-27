package br.com.gbsoftware.spacetattoostudio.domain.enums;

public enum FormaPagamentoEnum {

	CREDITO("CREDITO"),
	DEBITO("DEBITO"),
	AVISTA("AVISTA");
	
	private String pagamento;

	private FormaPagamentoEnum(String pagamento) {
		this.pagamento = pagamento;
	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	
	
}
