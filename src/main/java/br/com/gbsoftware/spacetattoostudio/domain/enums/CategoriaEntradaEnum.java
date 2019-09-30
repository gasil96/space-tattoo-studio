package br.com.gbsoftware.spacetattoostudio.domain.enums;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
public enum CategoriaEntradaEnum {
	TATTOO("TATTOO"),
	PIERCING("PIERCING"),
	BARBEARIA("BARBEARIA"),
	PRODUTO("PRODUTO"),
	DIVERSOS("DIVERSOS");

	private String categoriaEntrada;

	private CategoriaEntradaEnum(String categoriaEntrada) {
		this.categoriaEntrada = categoriaEntrada;
	}

	public String getCategoriaEntrada() {
		return categoriaEntrada;
	}

	public void setCategoriaEntrada(String categoriaEntrada) {
		this.categoriaEntrada = categoriaEntrada;
	}
	

}

