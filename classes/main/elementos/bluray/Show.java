package main.elementos.bluray;

import main.exception.DadoInvalido;

/**
 * Classe que representa o bluray de Show
 * 
 * @author Giovana Brito Oliveira - 116110904
 *
 */
public class Show extends BluRay {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5775263303007503598L;
	private String nomeDoArtista;
	private int numeroFaixas;

	/**
	 * Contrutor do show
	 * 
	 * @param nome
	 *            nome do show
	 * @param valor
	 *            preco do bluray de show
	 * @param duracao
	 *            duracao do show
	 * @param numeroFaixas
	 *            numero de faixas do show
	 * @param artista
	 *            nome do artista
	 * @param classificacao
	 *            classificacao do show
	 * @throws Exception
	 */
	public Show(String nome, double valor, int duracao, int numeroFaixas, String artista, String classificacao)
			throws DadoInvalido {
		super(nome, valor, duracao, classificacao);

		if (artista == null) {
			throw new NullPointerException("Nome do artista nao pode ser nulo");
		}
		if (artista.equals("")) {
			throw new DadoInvalido("Nome do artista invalido");
		}
		if (numeroFaixas <= 0) {
			throw new DadoInvalido("Numero de faixas invalido");
		}

		this.nomeDoArtista = artista;
		this.numeroFaixas = numeroFaixas;
	}

	/**
	 * Metodo que retorna a representacao em String do show
	 */
	@Override
	public String toString() {
		return "SHOW: " + this.getNome() + ", R$ " + this.valor + ", " + this.estado() + ", " + this.getDuracao()
				+ " min, " + this.getClassificacao() + ", " + this.nomeDoArtista + ", " + this.numeroFaixas + " faixas";
	}

}
