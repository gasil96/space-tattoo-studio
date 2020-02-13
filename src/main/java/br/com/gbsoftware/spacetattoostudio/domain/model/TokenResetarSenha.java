package br.com.gbsoftware.spacetattoostudio.domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.catalina.User;

import br.com.gbsoftware.spacetattoostudio.domain.EntidadeBase;

@SuppressWarnings("serial")
@Entity
@Table(name = "TOKEN_SENHA")
public class TokenResetarSenha extends EntidadeBase<Long> {

	private static final int EXPIRATION = 60 * 2;

	private String token;

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "id_usuario_fk")
	private Usuario usuario;

	private Date dataExpiracao;

	public TokenResetarSenha() {

	}

	public TokenResetarSenha(String token, Usuario usuario) {
		super();
		this.token = token;
		this.usuario = usuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

}
