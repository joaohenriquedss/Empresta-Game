package main.elementos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import main.elementos.bluray.Filme;
import main.elementos.bluray.Serie;
import main.elementos.bluray.Show;
import main.exception.DadoInvalido;
import main.reputacao.BomAmigo;
import main.reputacao.Caloteiro;
import main.reputacao.FreeRyder;
import main.reputacao.Noob;
import main.reputacao.Reputacao;

/**
 * Classe que representa um usuario do sistema
 * 
 * @author Matheus
 *
 */
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1402844845936290606L;
	private String nome;
	private String email;
	private String numeroDoCelular;
	private double reputacaoValor;
	private Reputacao reputacao;
	private static final double porcentagemReputacao = 0.05;

	// uso do hashset pois apenas adicionar elementos com nomes diferentes.
	private Set<Item> listaItens;
	private Set<Item> listaItensPegos;

	/**
	 * Construtor do usuario
	 * 
	 * @param nome
	 *            nome do usuario
	 * @param email
	 *            email do usuario
	 * @param numeroDoCelular
	 *            numero do celular do usuario
	 */
	public Usuario(String nome, String email, String numeroDoCelular) throws Exception {

		if (nome.trim().equals("") || email.trim().equals("") || numeroDoCelular.trim().equals("")) {
			throw new DadoInvalido("Dado invalido");
		}
		this.nome = nome;
		this.email = email;
		this.numeroDoCelular = numeroDoCelular;
		this.listaItens = new HashSet<>();
		this.listaItensPegos = new HashSet<>();

		// Calculando valor inicial da reputacao do usuario
		if (listaItens.size() == 0) {
			this.reputacaoValor = 0.0;
			this.atualizarReputacao();
		} else {
			this.reputacaoValor = this.totalInicialReputacao();
		}
	}

	/**
	 * Metodo que calcula qual sera a reputacao do usuario a partir dos seus
	 * itens
	 * 
	 * @return retorna o total inicial da reputacao do usuario
	 */
	private double totalInicialReputacao() {
		double soma = 0;
		for (Item item : listaItens) {
			soma += item.getValor();
		}
		return soma * 0.05;
	}

	/**
	 * Metodo que retona a reputaccao do usuario
	 * 
	 * @return retorna a reputacao atual do usuario
	 */
	public double getReputacaoValor() {
		return reputacaoValor;
	}

	public String listarItensEmprestados() {
		String lista = "";
		for (Item item : listaItens) {
			if (item.getStatus() == true) {
				lista += item.toString();
			}
		}
		return lista;
	}

	/**
	 * Metodo que retorna o nome do usuario
	 * 
	 * @return retorna o nome do usuario
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo que atualiza o nome do usuario
	 * 
	 * @param nome
	 *            novo nome do usuario
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo que reotorna o email do usuario
	 * 
	 * @return retorna o email do usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo que atualiza o email do usuario
	 * 
	 * @param email
	 *            novo email do usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Metodo que retorna o nomero do celular do usuario
	 * 
	 * @return retorna o numero do celular do usuario
	 */
	public String getNumeroDoCelular() {
		return numeroDoCelular;
	}

	/**
	 * Metodo que atualiza o numero do celular do usuario
	 * 
	 * @param numeroDoCelular
	 *            novo numero de celular do usuario
	 */
	public void setNumeroDoCelular(String numeroDoCelular) {
		this.numeroDoCelular = numeroDoCelular;
	}

	/**
	 * Metodo que retorn uma representacao em String do usuario
	 */
	@Override
	public String toString() {
		return nome + ", " + email + ", " + numeroDoCelular + "|";
	}

	/**
	 * Metodo que retorna uma representacao em String com a reputacao do usuario
	 * 
	 * @return retorna uma representacao em String da reputacao do usuario
	 */
	public String toStringComReputacao() {
		return String.format("%s - Reputacao: %.2f|", nome, reputacaoValor);
	}

	/**
	 * Metodo que retorna uma representacao inteira do usuario
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numeroDoCelular == null) ? 0 : numeroDoCelular.hashCode());
		return result;
	}

	/**
	 * Metodoque determina se um usuario eh ou nao igual a outro
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numeroDoCelular == null) {
			if (other.numeroDoCelular != null)
				return false;
		} else if (!numeroDoCelular.equals(other.numeroDoCelular))
			return false;
		return true;
	}

	/**
	 * Metodo que adiciona um novo jodo de tabuleiro
	 * 
	 * @author Matheus Thiago
	 * @throws Exception
	 */
	public void adicionarItemJogoTabuleiro(String nome, double valor) throws Exception {
		this.listaItens.add(new JogoDeTabuleiro(nome, valor));
		this.reputacaoValor += valor * porcentagemReputacao;
	}

	/**
	 * Metodo que adiciona um novo jogo eletronico
	 * 
	 * @param nome
	 *            nome do jogo
	 * @param valor
	 *            preco o jogo
	 * @param plataforma
	 *            plataforma do jogo
	 * @throws Exception
	 */
	public void adiconarItemJogoEletronico(String nome, double valor, String plataforma) throws Exception {
		this.listaItens.add(new JogoEletronico(nome, valor, plataforma));
		this.reputacaoValor += valor * porcentagemReputacao;
	}

	/**
	 * Metodo que adiciona um novo filme
	 * 
	 * @param nome
	 *            nome do filme
	 * @param valor
	 *            preco do filme
	 * @param duracao
	 *            duracao do filme
	 * @param genero
	 *            genero do filme
	 * @param classificacao
	 *            classificacao do filme
	 * @param anoDeLancamento
	 *            ano de lancamento do filme
	 * @throws Exception
	 */
	public void adicionarItemFilme(String nome, double valor, int duracao, String genero, String classificacao,
			String anoDeLancamento) throws Exception {
		this.listaItens.add(new Filme(nome, valor, duracao, genero, classificacao, anoDeLancamento));
		this.reputacaoValor += valor * porcentagemReputacao;
	}

	/**
	 * Metodo que adiciona uma nova serie
	 * 
	 * @param nome
	 *            nome da serie
	 * @param valor
	 *            preco da serie
	 * @param descricao
	 *            descricao da serie
	 * @param duracao
	 *            duracao da serie
	 * @param classificacao
	 *            classisficacao da serie
	 * @param genero
	 *            genero da serie
	 * @param temporada
	 *            temporada da serie
	 * @throws Exception
	 */
	public void adicionarItemSerie(String nome, double valor, String descricao, int duracao, String classificacao,
			String genero, int temporada) throws Exception {
		this.listaItens.add(new Serie(nome, valor, descricao, duracao, classificacao, genero, temporada));
		this.reputacaoValor += valor * porcentagemReputacao;
	}

	/**
	 * Metodo que adiciona um novo show
	 * 
	 * @param nome
	 *            nome dos show
	 * @param valor
	 *            preco do bluray de show
	 * @param duracao
	 *            duracao do show
	 * @param numeroFaixas
	 *            numero de faixas do show
	 * @param artista
	 *            nome do artista do show
	 * @param classificacao
	 *            classificacao do show
	 * @throws Exception
	 */
	public void adicionarItemShow(String nome, double valor, int duracao, int numeroFaixas, String artista,
			String classificacao) throws Exception {
		this.listaItens.add(new Show(nome, valor, duracao, numeroFaixas, artista, classificacao));
		this.reputacaoValor += valor * porcentagemReputacao;
	}

	/**
	 * Metodo que remove um item
	 * 
	 * @param nomeItem
	 *            nome do idem
	 * @return retorna um booleano indicando se o item foi ou nao removido
	 */
	public boolean removerItem(String nomeItem) {
		for (Item item : listaItens) {
			if (item.getNome().equalsIgnoreCase(nomeItem)) {
				this.listaItens.remove(item);
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo que atualiza o nome do item
	 * 
	 * @param nomeItem
	 *            nome atual do item
	 * @param nomeMudar
	 *            novo nome do item
	 */
	public void atualizarItem(String nomeItem, String atributo, String valor) {
		if (atributo.equalsIgnoreCase("nome")) {
			recuperItem(nomeItem).setNome(valor);
		} else if (atributo.equalsIgnoreCase("preco")) {
			double valorDouble = Double.parseDouble(valor);
			recuperItem(nomeItem).setValor(valorDouble);
		}
	}

	/**
	 * Metodo que pega o valor de um item
	 * 
	 * @param nomeItem
	 *            nome do item desejado
	 * @return retorna o valor do item desejado
	 */
	public double getValorItem(String nomeItem) {
		if (recuperItem(nomeItem) != null)
			return recuperItem(nomeItem).getValor();
		return 0;
	}

	/**
	 * Metodo que retorna o nome do item desejado
	 * 
	 * @param nomeItem
	 *            nome do item desejado
	 * @return retorna o próprios nome do item
	 */
	public String getNomeItem(String nomeItem) {
		if (recuperItem(nomeItem) != null)
			return recuperItem(nomeItem).getNome();
		return "";
	}

	/**
	 * Metodo que recupera um item
	 * 
	 * @param nomeItem
	 *            nome do item
	 * @return retorna o item solicitado
	 */
	public Item recuperItem(String nomeItem) {
		for (Item item : listaItens) {
			if (item.getNome().equals(nomeItem))
				return item;
		}
		return null;
	}

	/**
	 * Metodo que adiciona um bluray numa serie
	 * 
	 * @param nomeItem
	 *            nome do item
	 * @param duracao
	 *            duracao do item
	 * @throws DadoInvalido
	 */
	public void adicionarBluRay(String nomeItem, int duracao) throws DadoInvalido {
		recuperItem(nomeItem).addBluray(duracao);
	}

	/**
	 * Metodo que envia a lista de itens
	 * 
	 * @return lista de itens
	 */
	public Set<Item> getListaItens() {
		return listaItens;
	}

	/**
	 * Metodo que adiciona uma peca perdida
	 * 
	 * @param nomeItem
	 *            nome do item
	 * @param nomePeca
	 *            nome da peca perdida
	 */
	public void adicionarPecaperdida(String nomeItem, String nomePeca) {
		JogoDeTabuleiro itenTabuleiro = (JogoDeTabuleiro) this.recuperItem(nomeItem);
		itenTabuleiro.adicionarPecaPerdida(nomePeca);
	}

	/**
	 * Metodo que retorna o toString de um item
	 * 
	 * @param nomeItem
	 *            nome do item
	 * @return retorna o toString do item solicitado
	 */
	public String detalheTotalItem(String nomeItem) {
		return this.recuperItem(nomeItem).toString();
	}

	/**
	 * aloca o item na lista de itens pegos
	 */
	public void aloca(Item item) {
		listaItensPegos.add(item);
	}

	/**
	 * Metodo que confere se um item ja esta presente na lista de itens pegos
	 * 
	 * @param nomeItem
	 *            nome do item solicitadoF
	 * @return retorna um booleano indicando se o item ja esta ou nao alocado
	 */
	public boolean recuperaAlocados(String nomeItem) {
		for (Item item : listaItensPegos) {
			if (nomeItem.equalsIgnoreCase(item.getNome()))
				return true;
		}
		return false;
	}

	/**
	 * Metodo que remove um item emprestado
	 * 
	 * @param nomeItem
	 *            nome do item solicitado
	 * @return retorna um booleano indicando se o item foi ou nao removido
	 */
	public boolean removerItemEmprestado(String nomeItem) {
		for (Item item : listaItensPegos) {
			if (item.getNome().equalsIgnoreCase(nomeItem)) {
				listaItensPegos.remove(item);
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo que ira atualizar a reputacao do usuario toda vez que ele
	 * emprestar um item seu
	 */
	public void atualizarReputacaoValor(double atualizacao) {
		this.reputacaoValor += atualizacao;
	}

	/**
	 * Metodo que atualiza a reputacao do usuario toda vez que e chamado.
	 */
	public void atualizarReputacao() {
		if (this.reputacaoValor > 100)
			this.reputacao = new BomAmigo();
		else if (this.reputacaoValor < 0)
			this.reputacao = new Caloteiro();
		else if (this.reputacaoValor >= 0 && this.numeroItensParaEmprestar() != 0)
			this.reputacao = new Noob();
		else if (this.reputacaoValor >= 0 && this.numeroItensParaEmprestar() == 0)
			this.reputacao = new FreeRyder();

	}

	/**
	 * metodo get que retorna o objeto reputacao.
	 * 
	 * @return o objeto Reputacao
	 */
	public Reputacao getReputacao() {
		return reputacao;
	}

	/**
	 * Metodo para listar o numeros de itens do usuario para serem emprestados.
	 * 
	 * @return inteiro do numeros de itens para serem emprestados
	 */
	public int numeroItensParaEmprestar() {
		int totalItens = 0;
		for (Item item : listaItens) {
			if (item.getStatus() == false)
				totalItens++;
		}
		return totalItens;
	}

}
