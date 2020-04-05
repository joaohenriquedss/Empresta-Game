package main.reputacao;

import java.io.Serializable;

/**
 * Iterface para o implementacao de diferentes reputacao.
 * @author Matheus Thiago
 *
 */
public interface Reputacao extends Serializable {
	
	public boolean pegarEmprestado();
	
	public int periodoEmprestimo();
	
	public String toStringReputacao();

}
