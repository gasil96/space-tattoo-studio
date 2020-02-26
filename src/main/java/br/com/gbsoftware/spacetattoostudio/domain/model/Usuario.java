package br.com.gbsoftware.spacetattoostudio.domain.model;

/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.gbsoftware.spacetattoostudio.domain.enums.CargoUsuarioEnum;

@SuppressWarnings("serial")
@Entity
public class Usuario implements UserDetails, Serializable {

	@Id
	@Column(length = 20, unique = true)
	@NotNull
	private String login;

	@NotNull
	private String nomeCompleto;

	@NotNull
	@Column(unique = true)
	private String email;

	@NotNull
	@NotEmpty
	@JsonIgnore
	private String senha;

	@Column(name = "cargo")
	@Enumerated(EnumType.STRING)
	private CargoUsuarioEnum cargo;

	@ManyToMany
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "login"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "nomeRole"))
	@JsonIgnore
	private List<Role> roles;

	public Usuario() {
		super();
	}

	public Usuario(@NotNull String login, @NotNull String nomeCompleto, @NotNull String email,
			@NotNull @NotEmpty String senha, CargoUsuarioEnum cargo, List<Role> roles) {
		super();
		this.login = login;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.senha = senha;
		this.cargo = cargo;
		this.roles = roles;
	}

	public CargoUsuarioEnum getCargo() {
		return cargo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCargo(CargoUsuarioEnum cargo) {
		this.cargo = cargo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) this.roles;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
