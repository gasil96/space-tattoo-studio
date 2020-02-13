package br.com.gbsoftware.spacetattoostudio.domain.model;

public class GenericReponse {

	private String message;

	private String error;

	public GenericReponse() {

	}

	public GenericReponse(String message) {
		super();
		this.message = message;
	}

	public GenericReponse(String message, String error) {
		super();
		this.message = message;
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
