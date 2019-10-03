package br.com.gbsoftware.spacetattoostudio.domain.enums;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
public enum FormaPagamentoEnum {

	A_VISTA("À Vista"),
	CREDITO("Crédito"),
	DEBITO("Débito");
	
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
