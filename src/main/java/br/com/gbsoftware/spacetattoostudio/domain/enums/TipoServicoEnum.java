package br.com.gbsoftware.spacetattoostudio.domain.enums;

/** 
 * 
 * <b>GB Software</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019-08-11 - Criação
 *
 */
public enum TipoServicoEnum {

	BARBEARIA("BARBEARIA"),
	LOJA("LOJA"),
	TATTOO("TATTOO"),
	PIERNCING("PIERCING");
	
	private String tipoServico;
	
	private TipoServicoEnum(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getTipoServico() {
		return tipoServico;
	}

}

