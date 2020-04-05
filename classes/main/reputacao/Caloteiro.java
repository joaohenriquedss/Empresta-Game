package main.reputacao;
/**
 * classe que representa o objeto da reputacao caloteiro.
 * @author Matheus Thiago
 *
 */
public class Caloteiro implements Reputacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9087657364417708773L;

	/**
	 * Metodo que retorna uma boolean false para emprestar o item.
	 * @return a boolean false
	 */
	@Override
	public boolean pegarEmprestado() {
		return false;
	}

	/**
	 * Metodo que retorna a quantidade de dias do emprestimo.
	 * @return 0 dias de emprestimo
	 */
	@Override
	public int periodoEmprestimo() {
		return 0;
	}

	/**
	 * Metodo que retorna o tipo de reputacao.
	 * @return um String "Caloteiro"
	 */
	@Override
	public String toStringReputacao() {
		return "Caloteiro";
	}

}
