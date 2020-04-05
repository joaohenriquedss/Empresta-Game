package main.reputacao;
/**
 * classe que representa o objeto da reputacao bom amigo.
 * @author Matheus Thiago
 *
 */
public class BomAmigo implements Reputacao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7327739511491333531L;

	/**
	 * Metodo que retorna uma boolean true para emprestar o item.
	 * @return a boolean true
	 */
	@Override
	public boolean pegarEmprestado() {
		return true;
	}

	/**
	 * Metodo que retorna a quantidade de dias do emprestimo.
	 * @return 14 dias de emprestimo
	 */
	@Override
	public int periodoEmprestimo() {
		return 14;
	}

	/**
	 * Metodo que retorna o tipo de reputacao.
	 * @return um String "BomAmigo"
	 */
	@Override
	public String toStringReputacao() {
		return "BomAmigo";
	}

}
