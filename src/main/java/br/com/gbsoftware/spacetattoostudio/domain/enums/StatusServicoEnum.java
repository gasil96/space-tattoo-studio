package br.com.gbsoftware.spacetattoostudio.domain.enums;
/**
 * <b>GB Software</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
public enum StatusServicoEnum {
	ATIVO("ATIVO"),
	ENCERRADO("ENCERRADO"),
	PENDENTE("PENDENTE");

	private String statusServico;

	private StatusServicoEnum(String statusServico) {
		this.statusServico = statusServico;
	}

	public String getStatusServico() {
		return statusServico;
	}
}
