package main.facade;

import java.io.IOException;
import java.io.Serializable;

import easyaccept.EasyAccept;
import main.controller.EmprestimoController;
import main.controller.ItemController;
import main.controller.UsuarioController;
import main.elementos.Usuario;

/**
 * Classe responsabel pela interacao do administrador com o programa
 */
public class TTFacade implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2950013313250796321L;
	private EmprestimoController emprestimoController;
	private UsuarioController usuarioController;
	private ItemController itemController;

	/**
	 * Contrutor da facade
	 */
	public TTFacade() {
		usuarioController = new UsuarioController();
		emprestimoController = new EmprestimoController(usuarioController.getRepository());
		itemController = new ItemController(usuarioController.getRepository());

	}

	/**
	 * Metodo por onde vai se iniciar o programa
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		args = new String[] { "main.facade.TTFacade", "us/us1_test.txt", "us/us2_test.txt", "us/us3_test.txt",
				"us/us4_test.txt", "us/us5_test.txt", "us/us6_test.txt", "us/us7_test.txt", "us/us8_test.txt" };
		EasyAccept.main(args);

	}

	/**
	 * Metodo que inicia o sistema
	 * @throws ClassNotFoundException 
	 */
	public void iniciarSistema() {
		try {
			this.usuarioController.iniciarSistema();

		} catch (IOException e) {

		} catch (ClassNotFoundException e) {
		} 
		try {
			this.emprestimoController.iniciaSistema();
		} catch (IOException e) {
			
		} catch (ClassNotFoundException e) {
		}
	}

	/**
	 * Metodo que fecha o sistema
	 */
	public void fecharSistema() {
		try {
			usuarioController.salvarSistema();
		} catch (IOException e) {
		}
		try {
			emprestimoController.salvarSistema();
		} catch (IOException e) {
			
		}
	}

	/**
	 * Adiciona {@link Usuario} para um sistema
	 * 
	 * @param nome
	 *            nome do usuario a ser cadastrado
	 * @param telefone
	 *            telefone do usuario a ser cadastrado
	 * @param email
	 *            email do usuario a ser cadastrasdo
	 * @throws Exception
	 */
	public void cadastrarUsuario(String nome, String telefone, String email) throws Exception {
		usuarioController.adicionar(nome, telefone, email);
	}

	/**
	 * Recuperar informacoes de um usuario
	 * 
	 * @param nome
	 *            nome do usuario a ser pesquisado
	 * @param telefone
	 *            telefone do usuario
	 * @param atributo
	 *            atribudo a ser atendido
	 * @return retorna uma string com atributos do ususario
	 * @throws Exception
	 */
	public String getInfoUsuario(String nome, String telefone, String atributo) throws Exception {
		return usuarioController.getInfoUsuario(nome, telefone, atributo);
	}

	/**
	 * Remove um usuario
	 * 
	 * @param nome
	 *            nome do usuario
	 * @param telefone
	 *            telefone do usuario
	 * @throws Exception
	 */
	public void removerUsuario(String nome, String telefone) throws Exception {
		usuarioController.removerUsuario(nome, telefone);
	}

	/**
	 * Edita um atributo de um usuario
	 * 
	 * @param nome
	 *            do usuario
	 * @param telefone
	 *            telefone
	 * @param atributo
	 *            atributo a ser atendido
	 * @param email
	 * @throws Exception
	 */
	public void atualizarUsuario(String nome, String telefone, String atributo, String email) throws Exception {
		usuarioController.atualizarUsuario(nome, telefone, atributo, email);
	}

	/**
	 * Metodo que retorna a informacao do item de acordo com o atributo.
	 * 
	 * @param nome nome do usuario
	 * @param telefone telefone do usuario
	 * @param itemNome nome do item
	 * @param atributo atriuto solicitado
	 * @return retorna a informacao do item de acordo com o atributo
	 * @throws Exception
	 */
	public String getInfoItem(String nome, String telefone, String itemNome, String atributo) throws Exception {
		return this.itemController.getInfoItem(nome, telefone, itemNome, atributo);
	}

	/**
	 * @param nome
	 *            nome do usuario
	 * @param telefone
	 *            telefone do usuario
	 * @param nomeItem
	 *            nome do item
	 * @param preco
	 *            preco do item
	 * @param plataforma
	 *            plataforma do jogo eletronico
	 * @throws Exception
	 */
	public void cadastrarEletronico(String nome, String telefone, String nomeItem, double preco, String plataforma)
			throws Exception {
		itemController.cadastrarEletronico(nome, telefone, nomeItem, preco, plataforma);
	}

	/**
	 * Metodo que cadastra um novo jogo de tabuleiro
	 * 
	 * @param nome
	 *            nome do usuario
	 * @param telefone
	 *            telefone do usuario
	 * @param nomeItem
	 *            nome do item
	 * @param preco
	 *            preco do item
	 * @throws Exception
	 */
	public void cadastrarJogoTabuleiro(String nome, String telefone, String nomeItem, double preco) throws Exception {
		itemController.cadastrarJogoTabuleiro(nome, telefone, nomeItem, preco);
	}

	/**
	 * Metodo que adiciona uma nova peca perdida
	 * 
	 * @param nome
	 *            nome do usuario
	 * @param telefone
	 *            telefone do usuario
	 * @param nomeItem
	 *            nome do item
	 * @param nomePeca
	 *            nome da peca perdida
	 */
	public void adicionarPecaPerdida(String nome, String telefone, String nomeItem, String nomePeca) {
		itemController.adicionarPecaPerdida(nome, telefone, nomeItem, nomePeca);
	}

	/**
	 * Metodo que cadastra um novo bluRay de serie
	 * 
	 * @param nome
	 *            nome do usuario
	 * @param telefone
	 *            telefone do usuario
	 * @param nomeItem
	 *            nome do item
	 * @param preco
	 *            preco do item
	 * @param descricao
	 *            descricao da serie
	 * @param duracao
	 *            duracao da serie
	 * @param classificacao
	 *            classificacao da serie
	 * @param genero
	 *            genero da serie
	 * @param temporada
	 *            temporada da serie
	 * @throws Exception
	 */
	public void cadastrarBluRaySerie(String nome, String telefone, String nomeItem, double preco, String descricao,
			int duracao, String classificacao, String genero, int temporada) throws Exception {
		itemController.cadastrarBluRaySerie(nome, telefone, nomeItem, preco, descricao, duracao, classificacao, genero,
				temporada);
	}

	/**
	 * Metodo que cadastra um novo blutay de filme
	 * 
	 * @param nome
	 *            nome do usuario
	 * @param telefone
	 *            telefone do usuario
	 * @param nomeItem
	 *            nome do item
	 * @param valor
	 *            valor do item
	 * @param duracao
	 *            duracao do item
	 * @param genero
	 *            genero do item
	 * @param classificacao
	 *            classificacao do item
	 * @param anoDeLancamento
	 *            ano de lancamento do filme
	 * @throws Exception
	 */
	public void cadastrarBluRayFilme(String nome, String telefone, String nomeItem, double valor, int duracao,
			String genero, String classificacao, String anoDeLancamento) throws Exception {
		itemController.cadastrarBluRayFilme(nome, telefone, nomeItem, valor, duracao, genero, classificacao,
				anoDeLancamento);

	}

	/**
	 * Metodo que cadastra um show
	 * 
	 * @param nome
	 *            nome do usuario
	 * @param telefone
	 *            telefone do usuario
	 * @param nomeItem
	 *            nome do item
	 * @param valor
	 *            valor do item
	 * @param duracao
	 *            duracao do show
	 * @param numeroDeFaixas
	 *            numero de faixas do show
	 * @param artista
	 *            nome do artista do show
	 * @param classificacao
	 *            classificacao do show
	 * @throws Exception
	 */
	public void cadastrarBluRayShow(String nome, String telefone, String nomeItem, double valor, int duracao,
			int numeroDeFaixas, String artista, String classificacao) throws Exception {
		itemController.cadastrarBluRayShow(nome, telefone, nomeItem, valor, duracao, numeroDeFaixas, artista,
				classificacao);

	}

	/**
	 * Metodo que adiciona um episodio na serie
	 * 
	 * @param nome
	 *            nome do usuario
	 * @param telefone
	 *            telefone do usuario
	 * @param nomeItem
	 *            nome do item
	 * @param duracao
	 *            duracao do episodio
	 * @throws DadoInvalido
	 */
	public void adicionarBluRay(String nome, String telefone, String nomeItem, int duracao) throws Exception {
		itemController.adicionarBluRay(nome, telefone, nomeItem, duracao);
	}

	/**
	 * Metodo que remove um item
	 * 
	 * @param nome
	 *            nome do usuario
	 * @param telefone
	 *            telefone do usuario
	 * @param nomeItem
	 *            nome do item
	 * @throws Exception
	 */
	public void removerItem(String nome, String telefone, String nomeItem) throws Exception {
		itemController.removerItem(nome, telefone, nomeItem);
	}

	/**
	 * Metodo que atualiza o nome do item
	 * 
	 * @param nome
	 *            nome do usuario
	 * @param telefone
	 *            telefone do usuario
	 * @param nomeItem
	 *            nome do item
	 * @param atributo
	 *            atributo a ser mudado
	 * @param valor
	 *            novo nome
	 * @throws Exception
	 */
	public void atualizarItem(String nome, String telefone, String nomeItem, String atributo, String valor)
			throws Exception {
		itemController.atualizarItem(nome, telefone, nomeItem, atributo, valor);
	}

	/**
	 * Metodo que lista todos os objetos em ordem alfabetica.
	 * 
	 * @return uma lista em String de itens
	 */
	public String listarItensOrdenadosPorNome() {
		return this.itemController.listarItensOrdenadosPorNome();
	}

	/**
	 * Metodo que lista todos os objetos em ordem de valor.
	 * 
	 * @return uma lista em String de itens
	 */
	public String listarItensOrdenadosPorValor() {
		return this.itemController.ordenacaoItensValor();
	}

	/**
	 * Metodo que pesquisa o item com a descricao completa.
	 * 
	 * @param nome
	 *            em String
	 * @param telefone
	 *            em String
	 * @param nomeItem
	 *            em String
	 * @return a descricao do item em string
	 * @throws Exception
	 */
	public String pesquisarDetalhesItem(String nome, String telefone, String nomeItem) throws Exception {
		return this.itemController.pesquisarDetalhesItem(nome, telefone, nomeItem);
	}

	/**
	 * Metodo que registra um emprestimo
	 * 
	 * @param nomeDono
	 *            nome do dono do item
	 * @param telefoneDono
	 *            telefone do dono do item
	 * @param nomeRequerente
	 *            nome do requerente
	 * @param telefoneRequerente
	 *            telefone do requerente
	 * @param nomeItem
	 *            nome do item
	 * @param dataEmprestimo
	 *            data do emprestimo
	 * @param periodo
	 *            periodo de emprestimo
	 * @throws Exception
	 */
	public void registrarEmprestimo(String nomeDono, String telefoneDono, String nomeRequerente,
			String telefoneRequerente, String nomeItem, String dataEmprestimo, int periodo) throws Exception {
		emprestimoController.registrarEmprestimo(nomeDono, telefoneDono, nomeRequerente, telefoneRequerente, nomeItem,
				dataEmprestimo, periodo);
	}

	/**
	 * Metodo que devolve um item ao seu dono original
	 * 
	 * @param nomeDono
	 *            nome do dono do item
	 * @param telefoneDono
	 *            telefone do dono do item
	 * @param nomeRequerente
	 *            nome do requerente
	 * @param telefoneRequerente
	 *            tefelone do requerente
	 * @param nomeItem
	 *            nome do item
	 * @param dataEmprestimo
	 *            data de emprestimo
	 * @param dataDevolucao
	 *            data da devolucao
	 * @throws Exception
	 */
	public void devolverItem(String nomeDono, String telefoneDono, String nomeRequerente, String telefoneRequerente,
			String nomeItem, String dataEmprestimo, String dataDevolucao) throws Exception {
		emprestimoController.devolverItem(nomeDono, telefoneDono, nomeRequerente, telefoneRequerente, nomeItem,
				dataEmprestimo, dataDevolucao);
	}

	/**
	 * Metodo que lista os emprestimos de um usuario que esta emprestando
	 * 
	 * @param nome nome do usuario
	 * @param telefone telefone do usuario
	 * @return retorna todos os itens que o usuairo esta emprestando no momento
	 * @throws Exception
	 */
	public String listarEmprestimosUsuarioEmprestando(String nome, String telefone) throws Exception {
		return emprestimoController.listarEmprestimosUsuarioEmprestando(nome, telefone);
	}

	/**
	 * Metodo que lista os emprestimos de um usuario que esta pegando emprestado
	 * 
	 * @param nome nome do usuario
	 * @param telefone telefone do usuario
	 * @return retorna todos os itens que o usuario esta com eles emprestados
	 * @throws Exception
	 */
	public String listarEmprestimosUsuarioPegandoEmprestado(String nome, String telefone) throws Exception {
		return emprestimoController.listarEmprestimosUsuarioPegandoEmprestado(nome, telefone);
	}

	/**
	 * Metodo que lista os emprestimos associados ao item
	 * 
	 * @param nomeItem nome do item
	 * @return retorna todos os emprestimos ja associados ao item
	 */
	public String listarEmprestimosItem(String nomeItem) {
		return emprestimoController.listarEmprestimosItem(nomeItem);
	}

	/**
	 * Metodo que lista os itens emprestados
	 * 
	 * @return retorna os itens emprestados
	 */
	public String listarItensEmprestados() {
		return emprestimoController.listarItensEmprestados();
	}

	/**
	 * Metodo que lista os itens nao emprestados
	 * 
	 * @return retorna os itens nao emprestados
	 */
	public String listarItensNaoEmprestados() {
		return this.emprestimoController.listarItensNaoEmprestados();
	}

	/**
	 * Metodo que lista os 10 itens mais emprestados
	 * 
	 * @return retorna os 10 itens mais emprestados
	 */
	public String listarTop10Itens() {
		return emprestimoController.listarTop10Itens();
	}

	/**
	 * Metodo que lista os usuarios caloteiros
	 * 
	 * @return retorna os usuarios caloteiros
	 */
	public String listarCaloteiros() {
		return usuarioController.listarCaloteiros();
	}

	/**
	 * Metodo que lista os 10 melhores usuarios
	 * 
	 * @return retorna os 10 melhores usuarios
	 */
	public String listarTop10MelhoresUsuarios() {
		return usuarioController.listarTop10MelhoresUsuarios();
	}

	/**
	 * Metodo que lista os 10 piores usuarios
	 * 
	 * @return retorna oos 10 piores usuarios
	 */
	public String listarTop10PioresUsuarios() {
		return usuarioController.listarTop10PioresUsuarios();
	}
}
