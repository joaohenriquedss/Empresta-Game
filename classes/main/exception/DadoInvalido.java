package main.exception;

import java.io.Serializable;

/**
 * Classe que representa a exception de DadoInvalido
 * 
 * @author Joao
 *
 */
public class DadoInvalido extends Exception implements Serializable {

	private static final long serialVersionUID = -5742543064498035320L;

	/**
	 * Construtor do dadoInvalido
	 * 
	 * @param msg
	 *            mensagem da exception
	 */
	public DadoInvalido(String msg) {
		super(msg);
	}
}
