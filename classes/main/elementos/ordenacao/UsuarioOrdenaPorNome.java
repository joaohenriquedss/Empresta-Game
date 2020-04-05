package main.elementos.ordenacao;

import java.util.Comparator;
import main.elementos.Emprestimo;

/**
 * Classe de ordenacao por nome
 * 
 * @author Matheus
 *
 */
public class UsuarioOrdenaPorNome implements Comparator<Emprestimo> {
	/**
	 * Metodo que compara usuarios por nome
	 */
	@Override
	public int compare(Emprestimo o1, Emprestimo o2) {
		return o1.getUsuarioDono().getNome().trim().toLowerCase()
				.compareTo(o2.getUsuarioDono().getNome().trim().toLowerCase());
	}

}
