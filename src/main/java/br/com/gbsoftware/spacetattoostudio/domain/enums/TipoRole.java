package br.com.gbsoftware.spacetattoostudio.domain.enums;
/**
 * <b>GB Software</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
public enum TipoRole {
	ROLE_ADMIN("ROLE_ADMIN"),
	ROLE_GERENTE("ROLE_GERENTE"),
	ROLE_USUARIO("ROLE_USUARIO");
	
	private String role;

	private TipoRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

}

