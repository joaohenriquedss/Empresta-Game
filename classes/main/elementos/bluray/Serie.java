package main.elementos.bluray;

import java.util.ArrayList;
import java.util.List;

import main.exception.DadoInvalido;

/**
 * Classe que representa um bluray de serie
 * 
 * @author Giovana Brito Oliveira - 116110904
 *
 */
public class Serie extends BluRay {

	/**
	 * 
	 */
	private static final long serialVersionUID = 13784028056361153L;
	private String nome;
	private List<Integer> episodios;
	private String genero;
	private int numeroTemporada;
	private String descricao;

	/**
	 * Construtor de serie
	 * 
	 * @param nome
	 *            nome da serie
	 * @param valor
	 *            valor da serie
	 * @param descricao
	 *            descricao da serie
	 * @param duracao
	 *            duracao da serie
	 * @param classificacao
	 *            classificacao da serie
	 * @param genero
	 *            genero da serie
	 * @param temporada
	 *            numero da temporada
	 * @throws Exception
	 */
	public Serie(String nome, double valor, String descricao, int duracao, String classificacao, String genero,
			int temporada) throws DadoInvalido {
		super(nome, valor, duracao, classificacao);

		if (descricao == null) {
			throw new NullPointerException("Descricao nao pode ser nula");
		}
		if (descricao.equals("")) {
			throw new DadoInvalido("Descricao invalida");
		}
		if (genero == null) {
			throw new NullPointerException("Genero nao pode ser nulo");
		}
		if (genero.equals("")) {
			throw new DadoInvalido("Genero invalido");
		}
		if (temporada <= 0) {
			throw new DadoInvalido("Temporada invalida");
		}

		this.episodios = new ArrayList<Integer>();
		this.setDescricao(descricao);
		this.genero = genero;
		this.numeroTemporada = temporada;
	}

	/**
	 * Metodo que adiciona um novo episodio na temporada
	 * 
	 * @param duracao
	 *            duracao do episodio
	 * @throws DadoInvalido
	 */
	@Override
	public void addBluray(int duracao) throws DadoInvalido {
		if (duracao <= 0) {
			throw new DadoInvalido("Duracao invalida");
		}
		this.episodios.add(duracao);
	}

	/**
	 * Metodo que retorna uma representacao inteira da serie
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + numeroTemporada;
		return result;
	}

	/**
	 * Metodo quer indica se uma serie � ou n�o igual a outra
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Serie other = (Serie) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numeroTemporada != other.numeroTemporada)
			return false;
		return true;
	}

	/**
	 * Metodo que retorna o total de duracao da temporada
	 * 
	 * @return retorna o a duracao total da temporada
	 */
	public int getDuracaoTotal() {
		int duracaoTotal = 0;
		for (int duracao : episodios) {
			duracaoTotal += duracao;
		}
		return duracaoTotal;
	}

	/**
	 * Representacao em string da serie
	 */
	@Override
	public String toString() {
		return "SERIE: " + this.getNome() + ", R$ " + this.valor + ", " + this.estado() + ", " + this.getDuracaoTotal()
				+ " min, " + this.getClassificacao() + ", " + this.genero + ", Temporada " + this.numeroTemporada;
	}

	/**
	 * Mettodo que envia a descricao da serie
	 * 
	 * @return retorna a descricao da serie
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Metodo que recebe e atualiza a descricao da serie
	 * 
	 * @param descricao
	 *            nova descricao da serie
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
