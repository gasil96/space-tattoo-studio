package br.com.gbsoftware.spacetattoostudio.domain.enums;

/** 
 * 
 * <b>GB Software</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019-08-11 - Criação
 *
 */
public enum StatusClienteEnum {
	ATIVO("ATIVO"),
	INATIVO("INATIVO"),
	CANCELADO("CANCELADO"),
	INADIMPLENTE("INADIMPLENTE");

	private String statusCliente;

	private StatusClienteEnum(String statusCliente) {
		this.statusCliente = statusCliente;
	}

	public String getStatusCliente() {
		return statusCliente;
	}

}
