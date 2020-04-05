package main.elementos.ordenacao;

import java.util.Comparator;

import main.elementos.Usuario;

/**
 * Classe que ordena os usuarios pela reputacao do maior para o menor
 * 
 * @author Redson
 *
 */
public class UsuarioOrdenaPorReputacao implements Comparator<Usuario> {
	/**
	 * Metodo que compara usuarios por reputacao
	 */
	@Override
	public int compare(Usuario o1, Usuario o2) {
		if (o1.getReputacaoValor() > o2.getReputacaoValor())
			return -1;
		if (o1.getReputacaoValor() < o2.getReputacaoValor())
			return 1;
		return 0;
	}

}
