package main.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import main.elementos.Usuario;
import main.elementos.ordenacao.UsuarioOrdenaPorBaixaReputacao;
import main.elementos.ordenacao.UsuarioOrdenaPorReputacao;
import main.exception.DadoInvalido;
import main.exception.OperacaoNaoPermitida;
import main.repository.UsuarioRepository;

/**
 * Classe que representa o controller de usuario
 * @author Joao
 *
 */
public class UsuarioController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6841527212000176036L;
	private UsuarioRepository repository;
	private Usuario usuario;
	private static final String ATRIBUTO_EMAIL = "email";
	private static final String USUARIO_INVALIDO = "Usuario invalido";
	private static final String USUARIO_CASASTRADO = "Usuario ja cadastrado";
	private static final String ATRIBUTO_REPUTACAO = "Reputacao";
	private static final String ATRIBUTO_CARTAO = "Cartao";

	/**
	 * Construtor de sistemaController
	 */
	public UsuarioController() {
		this.repository = new UsuarioRepository();
	}

	/**
	 * Retorna o repositorio
	 * 
	 * @return retorna o repositorio
	 */
	public UsuarioRepository getRepository() {
		return repository;
	}

	/**
	 * Adiciona um {@link Usuario} em um sistema de usuarios
	 * 
	 * @param nome
	 *            Nome do usuario
	 * @param telefone
	 *            Telefone do usuario
	 * @param email
	 *            Email do usuario
	 * @throws Exception
	 *             Verifica se ja existe um usuario no sistema caso verdade ,
	 *             lanca um Exception
	 */
	public void adicionar(String nome, String telefone, String email) throws Exception {
		if (repository.recuperar(nome, telefone) != null) {
			throw new OperacaoNaoPermitida(USUARIO_CASASTRADO);
		}
		usuario = new Usuario(nome, email, telefone);
		repository.adiciona(usuario);
	}

	/**
	 * Metodo que da uma informacao de um {@link Usuario}
	 * 
	 * @param nome
	 *            Nome do usuario
	 * @param telefone
	 *            Telefone do usuario
	 * @param atributo
	 *            Atributo a ser seguido
	 * @return
	 * @throws Exception
	 *             caso o usuario nao exista
	 */
	public String getInfoUsuario(String nome, String telefone, String atributo) throws Exception {
		usuario = repository.recuperar(nome, telefone);
		if (usuario == null) {
			throw new DadoInvalido(USUARIO_INVALIDO);
		}
		if (ATRIBUTO_EMAIL.equalsIgnoreCase(atributo)) {
			return usuario.getEmail();
		}
		if (ATRIBUTO_REPUTACAO.equalsIgnoreCase(atributo)) {
			return String.valueOf(usuario.getReputacaoValor());
		}
		if (ATRIBUTO_CARTAO.equalsIgnoreCase(atributo)) {
			return usuario.getReputacao().toStringReputacao();
		}
		return null;
	}

	/**
	 * Remove um usuario do sistema
	 * 
	 * @param nome
	 *            Nome do usuario
	 * @param telefone
	 *            Telefone do usuario
	 * @throws Exception
	 *             caso o usuario nao exista
	 */
	public void removerUsuario(String nome, String telefone) throws Exception {
		if (repository.recuperar(nome, telefone) == null) {
			throw new DadoInvalido(USUARIO_INVALIDO);
		}
		repository.remover(nome, telefone);
	}

	/**
	 * Memtodo que atualiza um usuario
	 * 
	 * @param nome
	 *            nome do usuario
	 * @param telefone
	 *            telefone do usuario
	 * @param atributo
	 *            atributo a ser atualizado
	 * @param valor
	 *            novo valor do atributo
	 * @throws Exception
	 */
	public void atualizarUsuario(String nome, String telefone, String atributo, String valor) throws Exception {
		if (repository.recuperar(nome, telefone) == null) {
			throw new DadoInvalido(USUARIO_INVALIDO);
		}

		repository.editar(nome, telefone, atributo, valor);
	}

	/**
	 * Recupera um {@link Usuario} no sistema
	 * 
	 * @param nome
	 *            Nome do usuario
	 * @param telefone
	 *            Telefone do usuario
	 * @return
	 */
	public Usuario recuperar(String nome, String telefone) {
		return recuperar(nome, telefone);
	}

	/**
	 * Metodo que lista os usuarios caloteiros
	 * 
	 * @return retorna uma string com os toStrings dos usuarios caloteiros
	 */
	public String listarCaloteiros() {
		String lista = "Lista de usuarios com reputacao negativa: ";
		for (Usuario usuario : repository.getUsuarios()) {
			if (usuario.getReputacao().toStringReputacao().equals("Caloteiro"))
				lista += usuario.toString();
		}
		return lista;
	}

	/**
	 * Metodo que lista os 10 usuarios com melhor reputacao
	 * 
	 * @return retorna uma string com as toStrings dos 10 melhores usuarios
	 */
	public String listarTop10MelhoresUsuarios() {
		List<Usuario> melhoresUsuarios = repository.getUsuarios();
		Collections.sort(melhoresUsuarios, new UsuarioOrdenaPorReputacao());
		String lista = "";
		if (melhoresUsuarios.size() < 10) {
			for (int i = 0; i < melhoresUsuarios.size(); i++) {
				lista += i + 1 + ": " + melhoresUsuarios.get(i).toStringComReputacao();
			}
		} else {
			for (int i = 0; i < 10; i++) {
				lista += i + 1 + ": " + melhoresUsuarios.get(i).toStringComReputacao();
			}
		}
		return lista;
	}

	/**
	 * Metodo que lista os 10 usuarios com pior reputacao
	 * 
	 * @return retorna uma string com os toStrings dos 10 piores usuarios
	 */
	public String listarTop10PioresUsuarios() {
		List<Usuario> pioresUsuarios = repository.getUsuarios();
		Collections.sort(pioresUsuarios, new UsuarioOrdenaPorBaixaReputacao());
		String lista = "";
		if (pioresUsuarios.size() < 10) {
			for (int i = 0; i < pioresUsuarios.size(); i++) {
				lista += i + 1 + ": " + pioresUsuarios.get(i).toStringComReputacao();
			}
		} else {
			for (int i = 0; i < 10; i++) {
				lista += i + 1 + ": " + pioresUsuarios.get(i).toStringComReputacao();
			}
		}
		return lista;
	}

	/**
	 * Metodo que recupera as informações para o reinicio do sistema
	 * 
	 * @return retorna o usuarioRepository que foi salvo
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public UsuarioRepository iniciarSistema() throws IOException, ClassNotFoundException {
		return repository.iniciarSistema();
	}

	/**
	 * Metodo que salva o o usuarioController
	 * 
	 * @param usuarioController
	 * @throws IOException
	 */
	public void salvarSistema() throws IOException {
		repository.salvarSistema(this.repository);
	}
}
