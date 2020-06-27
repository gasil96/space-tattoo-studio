package br.com.gbsoftware.spacetattoostudio.domain.model;

public class CorpoEmail {

	private String assunto;

	private String menssagem;

	private String email;

	private String desconto;

	public CorpoEmail() {

	}

	public CorpoEmail(String assunto, String menssagem, String email) {
		super();
		this.assunto = assunto;
		this.menssagem = menssagem;
		this.email = email;
	}

	public String getDesconto() {
		return desconto;
	}

	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}

	public String getAssunto() {
		return assunto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMenssagem() {
		return menssagem;
	}

	public void setMenssagem(String menssagem) {
		this.menssagem = menssagem;
	}

	@Override
	public String toString() {
		return "CorpoEmail [assunto=" + assunto + ", menssagem=" + menssagem + ", email=" + email + "]";
	}

}
