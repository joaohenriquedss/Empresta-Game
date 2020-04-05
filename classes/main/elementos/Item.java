package main.elementos;

import java.io.Serializable;

import main.exception.DadoInvalido;

/**
 * @author Redson
 *
 *         Classe que representa um Item
 */
public abstract class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6727407156960527901L;
	protected String nome;
	protected double valor;
	protected boolean status;
	protected int popularidade;

	/**
	 * Construtor do Item
	 * 
	 */
	public Item(String nome, double valor) throws DadoInvalido {
		this.nome = nome;
		this.valor = valor;
		this.status = false;
		this.popularidade = 0;
	}

	/**
	 * Metodo que retorna uma String de acordo com o valor do status do Item
	 * 
	 */
	public String estado() {
		if (status == true)
			return "Emprestado";
		else
			return "Nao emprestado";
	}

	/**
	 * Metodo que retorna o nome do Item
	 * 
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo que altera o nome do Item
	 * 
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo que retorna o valor do Item
	 * 
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * Metodo que altera o valor do Item
	 *
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}

	/**
	 * Metodo que retorna status do Item
	 * 
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * Metodo que altera o status do Item
	 * 
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Metodo que retorna a popularidade do item
	 * 
	 * @return retorna a popularidade do item
	 */
	public int getPopularidade() {
		return popularidade;
	}

	/**
	 * Metodo que adiciona 1 a popularidade do item
	 */
	public void setPopularidade() {
		this.popularidade += 1;
	}

	/**
	 * Metodo que retorna uma representacao inteira do item
	 * 
	 * @author matheus
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * Metodo que indica se um objeto do tipo item eh ou nao igual ao outro
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/**
	 * Metodo que adiciona um episodio numa serie
	 * 
	 * @param duracao
	 *            duracao do episodio
	 * @throws DadoInvalido
	 */
	public void addBluray(int duracao) throws DadoInvalido {
		// funciona apenas na serie
	}
	
}