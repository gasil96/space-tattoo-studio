package br.com.gbsoftware.spacetattoostudio.domain.enums;

public enum TipoRole {
	/** 
	 * @author Gabriel Silva
	 * 
	 * */
	
	ROLE_ADMIN("ROLE_ADMIN"),
	ROLE_USER_PRIVILEGIO("ROLE_USER_PRIVILEGIO"),
	ROLE_USER("ROLE_USER");
	
	private String role;

	private TipoRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

}

