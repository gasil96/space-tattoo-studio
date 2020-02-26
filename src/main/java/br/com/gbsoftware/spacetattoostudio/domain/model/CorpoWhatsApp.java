package br.com.gbsoftware.spacetattoostudio.domain.model;

public class CorpoWhatsApp {

	private String msg;
	
	private String numero;
	
	public CorpoWhatsApp() {
		
	}

	public CorpoWhatsApp(String msg, String numero) {
		this.msg = msg;
		this.numero = numero;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "CorpoWhatsApp [msg=" + msg + ", numero=" + numero + "]";
	}
	
}
	