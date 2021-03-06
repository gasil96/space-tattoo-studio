package br.com.gbsoftware.spacetattoostudio.domain.enums;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
public enum TipoRoleEnum {
	ROLE_ADMIN("ROLE_ADMIN"),
	ROLE_GERENTE("ROLE_GERENTE"),
	ROLE_USUARIO("ROLE_USUARIO");
	
	private String role;

	private TipoRoleEnum(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

}

