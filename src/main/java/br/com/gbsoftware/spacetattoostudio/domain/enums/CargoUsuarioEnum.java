package br.com.gbsoftware.spacetattoostudio.domain.enums;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
public enum CargoUsuarioEnum {
	GERENTE("Gerente"),
	DESENVOLVEDOR("Desenvolvedor"),
	FUNCIONARIO("Funcionario");
	
	
	private String cargo;

	private CargoUsuarioEnum(String cargo) {
		this.cargo = cargo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
}

