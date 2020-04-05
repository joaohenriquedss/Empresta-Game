package main.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.elementos.Emprestimo;

/**
 * Classe que representa o repositorio de emprestimo
 * 
 *
 */
public class EmprestimoRepository implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6414086207971502055L;
	private List<Emprestimo> emprestimos;
	private List<Emprestimo> emprestimosItens;

	/**
	 * Contrutor do EmprestimoRepository
	 */
	public EmprestimoRepository() {
		emprestimos = new ArrayList<>();
		this.emprestimosItens = new ArrayList<>();
	}

	/**
	 * Metodo que representa um emprestimo
	 * 
	 * @param emprestimo
	 *            emprestimo
	 */
	public void adicionar(Emprestimo emprestimo) {
		emprestimos.add(emprestimo);
	}

	/**
	 * Metodo que adiciona um emprestimo ao item
	 * 
	 * @param emprestimo emprestimo a ser adicionado
	 */
	public void adicionarEmpIntens(Emprestimo emprestimo) {
		this.emprestimosItens.add(emprestimo);
	}

	/**
	 * Metodo que recupera um emprestimo
	 * 
	 * @param itemEmprestado
	 *            item emprestado
	 * @return retorna o emprestimo solicitado
	 */
	public Emprestimo recuperar(String itemEmprestado) {
		for (Emprestimo emprestimo : emprestimos) {
			if (emprestimo.getItem().equalsIgnoreCase(itemEmprestado)) {
				return emprestimo;
			}
		}
		return null;
	}

	/**
	 * Metodo que remove um item emprestado
	 * 
	 * @param itemEmprestado
	 *            item emprestado
	 */
	public void remove(String itemEmprestado) {
		emprestimos.remove(recuperar(itemEmprestado));
	}

	/**
	 * Metodo que retorna a lista de emprestimos
	 * 
	 * @return retorna a lista de emprestimos
	 */
	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	/**
	 * Metodo que coverte para o formato de data
	 * 
	 * @param datinha
	 *            data
	 * @return retornaa data no formato certo
	 * @throws Exception
	 */
	public Date converteParaData(String datinha) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date data = sdf.parse(datinha);
		return data;
	}

	/**
	 * Metodo que remove um emprestimo da lista emprestimosItens
	 * 
	 * @param nomeDono
	 *            nome do dono
	 * @param item
	 *            nome do item
	 */
	public void removerItenList(String nomeDono, String item) {
		for (Emprestimo emprestimo : emprestimosItens) {
			if (emprestimo.getUsuarioDono().getNome().equals(nomeDono)
					&& emprestimo.getItemEmprestado().getNome().equals(item)) {
				this.emprestimosItens.remove(emprestimo);
				return;
			}

		}
	}

	/**
	 * Metodo que retorna a lista de emprestimosItens
	 * 
	 * @return retorna a lista de emprestimos
	 */
	public List<Emprestimo> getEmprestimosItens() {
		return emprestimosItens;
	}

	/**
	 * Metodo que recupera a situacao anterior do repositorio de emprestimos
	 * 
	 * @return retorna o repositoiro de emprestimos como estava na sua ultima
	 *         execussao
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public EmprestimoRepository iniciaSistema() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream arqObjeto = null;

		try {
			arqObjeto = new ObjectInputStream(new FileInputStream("er_tt.dat"));
			return (EmprestimoRepository) arqObjeto.readObject();
		} finally {
			if (arqObjeto != null) {
				arqObjeto.close();
			}
		}

	}

	/**
	 * Metodo que salva o repositorio de emprestimos na sua situaccao atual
	 * 
	 * @param emprestimoRepository
	 *            repositorios em emprestimo
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void salvarSistema(EmprestimoRepository emprestimoRepository) throws FileNotFoundException, IOException {
		ObjectOutput arqObjeto = null;

		try {
			arqObjeto = new ObjectOutputStream(new FileOutputStream("er_tt.dat"));
			arqObjeto.writeObject(emprestimoRepository);
		} finally {
			if (arqObjeto != null) {
				arqObjeto.close();
			}
		}

	}

}
