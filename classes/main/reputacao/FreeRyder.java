package main.reputacao;
/**
 * classe que representa o objeto da reputacao free ryde.
 * @author Matheus Thiago
 *
 */
public class FreeRyder implements Reputacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8016535632391917903L;

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
	 * @return 5 dias de emprestimo
	 */
	@Override
	public int periodoEmprestimo() {
		return 5;
	}

	/**
	 * Metodo que retorna o tipo de reputacao.
	 * @return um String "FreeRyder"
	 */
	@Override
	public String toStringReputacao() {
		return "FreeRyder";
	}

}
