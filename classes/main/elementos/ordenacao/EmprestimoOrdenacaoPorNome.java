package main.elementos.ordenacao;

import java.util.Comparator;

import main.elementos.Emprestimo;

/**
 * Classe responsavel pela ordenacao do Emprestimo
 * 
 * @author Redson
 *
 */
public class EmprestimoOrdenacaoPorNome implements Comparator<Emprestimo> {

	/**
	 * Metodo que compara 2 objetos pelo nome e ordena em ordem alfabetica
	 */
	@Override
	public int compare(Emprestimo o1, Emprestimo o2) {
		return o1.getItemEmprestado().getNome().compareTo(o2.getItemEmprestado().getNome());
	}

}

