package main.reputacao;

/**
 * classe que representa o objeto da reputacao noob.
 * @author Matheus Thiago
 *
 */
public class Noob implements Reputacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 383023537677601318L;

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
	 * @return 7 dias de emprestimo
	 */
	@Override
	public int periodoEmprestimo() {
		return 7;
	}

	/**
	 * Metodo que retorna o tipo de reputacao.
	 * @return um String "Noob"
	 */
	@Override
	public String toStringReputacao() {
		return "Noob";
	}

}
