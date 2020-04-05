package main.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import main.elementos.Usuario;

/**
 * Classe responsavel pelo CRUD de {@link Usuario}
 *
 */
public class UsuarioRepository implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2854071381660172427L;
	private List<Usuario> usuarios;

	/**
	 * Construtor de usuarioRepository
	 */
	public UsuarioRepository() {
		usuarios = new ArrayList<>();
	}

	/**
	 * Adiciona um {@link Usuario} ao sistema
	 * 
	 * @param usuario
	 *            usuario a ser adicionado
	 */
	public boolean adiciona(Usuario usuario) {
		return usuarios.add(usuario);
	}

	/**
	 * Verifica se existe um {@link Usuario} caso existe ir� retorna
	 * 
	 * @param nome
	 *            Nome do ususario
	 * @param telefone
	 *            Telefone do usuario
	 * @return retorna um usuario que se dejesa ter acesso
	 */
	public Usuario recuperar(String nome, String telefone) {
		for (Usuario usuario : usuarios) {
			if (usuario.getNome().equalsIgnoreCase(nome) && usuario.getNumeroDoCelular().equals(telefone)) {
				return usuario;
			}
		}
		return null;
	}

	/**
	 * Remove um {@link Usuario}
	 * 
	 * @param nome
	 *            Nome do usuario
	 * @param telefone
	 *            Telefone de um usuario
	 * @return retorna um booleano indicando se o usuario foi ou nao removido
	 */
	public boolean remover(String nome, String telefone) {
		return usuarios.remove(recuperar(nome, telefone));
	}

	/**
	 * Edita um {@link Usuario} de acordo com um atributo
	 * 
	 * @param nome
	 *            Nome do usuario
	 * @param telefone
	 *            Telefone do usuario
	 * @param atributo
	 *            Atributo que determina como vai ser a modificacao
	 * @param valor
	 *            Valor da variavel que vai atualizar o usuario
	 */
	public void editar(String nome, String telefone, String atributo, String valor) {
		if (atributo.equalsIgnoreCase("email")) {
			recuperar(nome, telefone).setEmail(valor);
		} else {
			recuperar(nome, telefone).setNumeroDoCelular(valor);
		}
	}

	/**
	 * Metodo que retorna a lista de usuarios
	 * 
	 * @return retorna a lista de usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * Metodo que inicia o sistema com as informacoes salvas
	 * 
	 * @return retorna o repositorio de usuario ja salvo
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public UsuarioRepository iniciarSistema() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream arqObjeto = null;

		try {
			arqObjeto = new ObjectInputStream(new FileInputStream("ur_tt.dat"));
			return (UsuarioRepository) arqObjeto.readObject();
		} finally {
			if (arqObjeto != null) {
				arqObjeto.close();
			}
		}

	}

	/**
	 * Metodo que salva o repositorio no seu atual estado
	 * 
	 * @param usuarioRepository
	 *            repositorio de usuario a ser salvo
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void salvarSistema(UsuarioRepository usuarioRepository) throws FileNotFoundException, IOException {
		ObjectOutput arqObjeto = null;

		try {
			arqObjeto = new ObjectOutputStream(new FileOutputStream("ur_tt.dat"));
			arqObjeto.writeObject(usuarioRepository);
		} finally {
			if (arqObjeto != null) {
				arqObjeto.close();
			}
		}
	}
}