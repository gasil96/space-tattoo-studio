package br.com.gbsoftware.spacetattoostudio.domain.enums;

public enum TipoRole {
	/** 
	 * @author Gabriel Silva
	 * 
	 * */
	
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

