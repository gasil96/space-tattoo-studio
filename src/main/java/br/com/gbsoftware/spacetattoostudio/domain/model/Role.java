//package br.com.gbsoftware.spacetattoostudio.domain.model;
//
//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//
//import org.springframework.security.core.GrantedAuthority;
//
//@Entity
//public class Role implements GrantedAuthority {
//
//	@Id
//	private String nomeRole;
//	
//	@ManyToMany(mappedBy = "roles")
//    private List<Usuario> usuarios;
//
//	public Role(String nomeRole) {
//		super();
//		this.nomeRole = nomeRole;
//	}
//
//	@Override
//	public String getAuthority() {
//		return this.nomeRole;
//
//	}
//	
//}
