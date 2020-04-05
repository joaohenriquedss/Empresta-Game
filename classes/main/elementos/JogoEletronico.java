package main.elementos;

import main.exception.DadoInvalido;

/**
 * @author Redson
 *
 *         Classe que representa um Jogo Eletronico
 */
public class JogoEletronico extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4935711967042775308L;
	private String plataforma;

	/**
	 * Construtor do Jogo Eletronico
	 * 
	 * @throws Exception
	 * 
	 */
	public JogoEletronico(String nome, double valor, String plataforma) throws DadoInvalido {
		super(nome, valor);
		if (valor <= 0)
			throw new DadoInvalido("Preco invalido");
		if (nome.trim().equals("") || nome == null)
			throw new DadoInvalido("Nome invalido");
		if (plataforma.trim().equals("") || plataforma == null)
			throw new DadoInvalido("plataforma invalida");
		this.plataforma = plataforma;
	}

	/**
	 * Metodo que retorna a Plataforma do Jogo Eletronico
	 * 
	 */
	public String getPlataforma() {
		return plataforma;
	}

	/**
	 * Metodo que altera a Plataforma do Jogo Eletronico
	 * 
	 */
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	/**
	 * Metodo que retorna uma representacao inteira do jogo eletronico
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((plataforma == null) ? 0 : plataforma.hashCode());
		return result;
	}

	/**
	 * Metodo que indica se um jogo eletronico eh ou nao igua ao outro
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		JogoEletronico other = (JogoEletronico) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (plataforma != other.plataforma)
			return false;
		return true;
	}

	/**
	 * Metodo que retorna uma String para representar o Jogo Eletroniico
	 * 
	 */
	@Override
	public String toString() {
		return "JOGO ELETRONICO: " + nome + ", R$ " + valor + ", " + this.estado() + ", " + plataforma;
	}

}