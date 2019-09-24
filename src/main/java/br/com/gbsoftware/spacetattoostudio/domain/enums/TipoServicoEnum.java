package br.com.gbsoftware.spacetattoostudio.domain.enums;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
public enum TipoServicoEnum {
	TATTOO("TATTOO"),
	BARBEARIA("BARBEARIA"),
	PIERCING("PIERCING");

	private String tipoServico;
	
	private TipoServicoEnum(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getTipoServico() {
		return tipoServico;
	}

}

