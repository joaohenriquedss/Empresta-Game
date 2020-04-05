package main.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.sun.corba.se.spi.activation.Repository;

import main.elementos.Emprestimo;
import main.elementos.Item;
import main.elementos.Usuario;
import main.elementos.ordenacao.EmprestimoOrdenacaoPorNome;
import main.elementos.ordenacao.ItemOrdenacaoDescricao;
import main.elementos.ordenacao.ItemOrdenacaoPopularidade;
import main.elementos.ordenacao.UsuarioOrdenaPorNome;
import main.exception.DadoInvalido;
import main.repository.EmprestimoRepository;
import main.repository.UsuarioRepository;
import main.util.Util;

/**
 * Classe que representa controla o Emprestimo
 * 
 * @author Joao Henrique
 *
 */
public class EmprestimoController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5394854940996162843L;
	private Util util;
	private EmprestimoRepository emprestimoRepository;
	private static final String USUARIO_INVALIDO = "Usuario invalido";
	private static final String ITEM_JA_EMPRESTADO = "Item emprestado no momento";
	private static final String ITEM_NAO_ENCONTRADO = "Item nao encontrado";
	private static final String EMPRESTIMO_NAO_ENCONTRADO = "Emprestimo nao encontrado";
	private static final String NENHUM_ITEM_EMPRESTADO = "Nenhum item emprestado";
	private static final String NENHUM_ITEM_PEGO_EMPRESTADO = "Nenhum item pego emprestado";
	private static final String NENHUM_EMPRESTIMO_ASSOCIADO_AO_ITEM = "Nenhum emprestimos associados ao item";
	private static final String USUARIO_NAO_PEGAR_EMPRESTADO = "Usuario nao pode pegar nenhum item emprestado";
	private static final String USUARIO_NAO_PEGAR_EMPRESTADO_PERIODO = "Usuario impossiblitado de pegar emprestado por esse periodo";

	/**
	 * Construtor de sistemaController
	 */
	public EmprestimoController(UsuarioRepository usRepositorio) {
		this.util = new Util(usRepositorio);
		emprestimoRepository = new EmprestimoRepository();
	}

	/**
	 * Metodo que registra o emprestimo
	 * 
	 * @param nomeDono
	 *            nome do dono
	 * @param telefoneDono
	 *            telefone do dono
	 * @param nomeRequerente
	 *            nome do requerente
	 * @param telefoneRequerente
	 *            telefone do requerente
	 * @param nomeItem
	 *            nome do item
	 * @param dataEmprestimo
	 *            data em que foi feito o emprestimo
	 * @param periodo
	 *            periodo de emprestimo
	 * @throws Exception
	 */
	public void registrarEmprestimo(String nomeDono, String telefoneDono, String nomeRequerente,
			String telefoneRequerente, String nomeItem, String dataEmprestimo, int periodo) throws Exception {

		if (util.retornaUsuario(nomeDono, telefoneDono) == null
				|| util.retornaUsuario(nomeRequerente, telefoneRequerente) == null)
			throw new DadoInvalido(USUARIO_INVALIDO);
		if (util.retornaUsuario(nomeDono, telefoneDono).recuperItem(nomeItem) == null)
			throw new DadoInvalido(ITEM_NAO_ENCONTRADO);
		if (util.retornaUsuario(nomeDono, telefoneDono).recuperItem(nomeItem).getStatus() == true)
			throw new DadoInvalido(ITEM_JA_EMPRESTADO);
		if (!util.retornaUsuario(nomeRequerente, telefoneRequerente).getReputacao().pegarEmprestado())
			throw new DadoInvalido(USUARIO_NAO_PEGAR_EMPRESTADO);
		if (periodo > util.retornaUsuario(nomeRequerente, telefoneRequerente).getReputacao().periodoEmprestimo())
			throw new DadoInvalido(USUARIO_NAO_PEGAR_EMPRESTADO_PERIODO);
		Usuario dono = util.retornaUsuario(nomeDono, telefoneDono);
		Usuario requerente = util.retornaUsuario(nomeRequerente, telefoneRequerente);
		Item item = util.retornaUsuario(nomeDono, telefoneDono).recuperItem(nomeItem);
		Date data = emprestimoRepository.converteParaData(dataEmprestimo);
		Emprestimo emprestimo = new Emprestimo(dono, requerente, item, data, periodo);
		alocaItemEmprestado(nomeRequerente, telefoneRequerente, item);
		item.setStatus(true);
		util.retornaUsuario(nomeDono, telefoneDono).atualizarReputacao();
		emprestimoRepository.adicionar(emprestimo);
		emprestimoRepository.adicionarEmpIntens(emprestimo);
		item.setPopularidade();
		dono.atualizarReputacaoValor(item.getValor() * 0.1);
	}

	/**
	 * Metodo que aloca um item emprestado
	 * 
	 * @param nomeRequerente
	 *            nome do requerente
	 * @param telefoneRequerente
	 *            telefone do requerente
	 * @param item
	 *            nome do item
	 */
	public void alocaItemEmprestado(String nomeRequerente, String telefoneRequerente, Item item) {
		util.retornaUsuario(nomeRequerente, telefoneRequerente).aloca(item);
	}

	/**
	 * Metodo que devolve um item para o seu dono
	 * 
	 * @param nomeDono
	 *            nome do dono
	 * @param telefoneDono
	 *            telefone do dono
	 * @param nomeRequerente
	 *            nome do requerente
	 * @param telefoneRequerente
	 *            telefone do requerente
	 * @param nomeItem
	 *            nome do item
	 * @param dataEmprestimo
	 *            data do emprestimo
	 * @param dataDevolucao
	 *            data da devolucao
	 * @throws Exception
	 */
	public void devolverItem(String nomeDono, String telefoneDono, String nomeRequerente, String telefoneRequerente,
			String nomeItem, String dataEmprestimo, String dataDevolucao) throws Exception {
		if (util.retornaUsuario(nomeDono, telefoneDono) == null
				|| util.retornaUsuario(nomeRequerente, telefoneRequerente) == null)
			throw new DadoInvalido(USUARIO_INVALIDO);
		if (util.retornaUsuario(nomeDono, telefoneDono).recuperItem(nomeItem) == null)
			throw new DadoInvalido(ITEM_NAO_ENCONTRADO);
		if (!util.retornaUsuario(nomeRequerente, telefoneRequerente).recuperaAlocados(nomeItem))
			throw new DadoInvalido(EMPRESTIMO_NAO_ENCONTRADO);
		Item item = util.retornaUsuario(nomeDono, telefoneDono).recuperItem(nomeItem);
		Date data = emprestimoRepository.converteParaData(dataDevolucao);
		emprestimoRepository.recuperar(nomeItem).setDataDevolucao(data);
		emprestimoRepository.removerItenList(nomeDono, nomeItem);
		item.setStatus(false);
		util.retornaUsuario(nomeRequerente, telefoneRequerente)
				.atualizarReputacaoValor(this.atualizacaoReputacao(dataEmprestimo, dataDevolucao, item));
		util.retornaUsuario(nomeRequerente, telefoneRequerente).atualizarReputacao();
	}

	/**
	 * Metodo que lista os itens emprestados
	 * 
	 * @return retorna uma string com os toString's de todos os itens que estao
	 *         emprestados
	 */
	public String listarItensEmprestados() {
		String listar = "";
		List<Emprestimo> listaUsuario = emprestimoRepository.getEmprestimosItens();
		Collections.sort(listaUsuario, new UsuarioOrdenaPorNome());
		for (Emprestimo emprestimo : listaUsuario) {
			listar += emprestimo.toString2();
		}
		return listar;
	}

	/**
	 * Metodo que retorna os Emprestimos em que o usuario esta emprestando o
	 * item
	 * 
	 * @param nome
	 *            nome o usuario
	 * @param telefone
	 *            telefone do usuario
	 * @return retorna os emprestimos em que o usuario esta emprestando o item
	 * @throws Exception
	 */
	public String listarEmprestimosUsuarioEmprestando(String nome, String telefone) throws Exception {
		if (util.retornaUsuario(nome, telefone) == null)
			throw new DadoInvalido(USUARIO_INVALIDO);
		String lista = "Emprestimos: ";
		List<Emprestimo> emprestimosUsuarioEmprestando = emprestimoRepository.getEmprestimos();
		Collections.sort(emprestimosUsuarioEmprestando, new EmprestimoOrdenacaoPorNome());
		for (Emprestimo emprestimo : emprestimosUsuarioEmprestando) {
			if (emprestimo.getUsuarioDono().getNome().equals(nome)
					&& emprestimo.getUsuarioDono().getNumeroDoCelular().equals(telefone)) {
				lista += emprestimo.toString();
			}
		}
		if (lista.equals("Emprestimos: "))
			return NENHUM_ITEM_EMPRESTADO;
		return lista;
	}

	/**
	 * Metodo que retorna os Emprestimos em que o usuario esta pegando o item
	 * emprestado
	 * 
	 * @param nome
	 *            nome do usuario
	 * @param telefone
	 *            telefone do usuario
	 * @return retorna os emprestimos em que o usuario esta pegando o item
	 *         emprestado
	 * @throws Exception
	 */
	public String listarEmprestimosUsuarioPegandoEmprestado(String nome, String telefone) throws Exception {
		if (util.retornaUsuario(nome, telefone) == null)
			throw new DadoInvalido(USUARIO_INVALIDO);
		String lista = "Emprestimos pegos: ";
		List<Emprestimo> emprestimosUsuarioPegandoEmprestado = emprestimoRepository.getEmprestimos();
		Collections.sort(emprestimosUsuarioPegandoEmprestado, new EmprestimoOrdenacaoPorNome());
		for (Emprestimo emprestimo : emprestimosUsuarioPegandoEmprestado) {
			if (emprestimo.getUsuarioRequerente().getNome().equals(nome)
					&& emprestimo.getUsuarioRequerente().getNumeroDoCelular().equals(telefone)) {
				lista += emprestimo.toString();
			}
		}
		if (lista.equals("Emprestimos pegos: "))
			return NENHUM_ITEM_PEGO_EMPRESTADO;
		return lista;
	}

	/**
	 * Metodo que retorna os emprestimos associados ao item
	 * 
	 * @param nomeItem
	 *            nome do item
	 * @return retorna os emprestimos associados ao item
	 */
	public String listarEmprestimosItem(String nomeItem) {
		String lista = "Emprestimos associados ao item: ";
		for (Emprestimo emprestimo : emprestimoRepository.getEmprestimos()) {
			if (emprestimo.getItemEmprestado().getNome().equals(nomeItem)) {
				lista += emprestimo.toString();
			}
		}
		if (lista.equals("Emprestimos associados ao item: "))
			return NENHUM_EMPRESTIMO_ASSOCIADO_AO_ITEM;
		return lista;

	}

	/**
	 * Metodo que retorna os itens que nao estao emprestados
	 * 
	 * @return retorna os itens que nao estao emprestados
	 */
	public String listarItensNaoEmprestados() {
		List<Item> lista = new ArrayList<>();
		String saida = "";
		for (Usuario usuario : this.util.getUsuarios()) {
			for (Item Item : usuario.getListaItens()) {
				if (Item.getStatus() == false) {
					lista.add(Item);
				}
			}
		}
		Collections.sort(lista, new ItemOrdenacaoDescricao());
		for (Item item : lista) {
			saida += item.toString() + "|";
		}
		return saida;
	}

	/**
	 * Metodo que retorna os 10 itens mais emprestados
	 * 
	 * @return retorna os 10 itens mais emprestados
	 */
	public String listarTop10Itens() {
		List<Item> itensPopulares = new ArrayList<>();
		for (Usuario usuario : this.util.getUsuarios()) {
			for (Item Item : usuario.getListaItens()) {
				if (Item.getPopularidade() > 0)
					itensPopulares.add(Item);
			}
		}
		Collections.sort(itensPopulares, new ItemOrdenacaoPopularidade());
		String top10 = "";
		for (int i = 0; i < itensPopulares.size(); i++) {
			if (i == 10) {
				break;
			}
			top10 += i + 1 + ") " + itensPopulares.get(i).getPopularidade() + " emprestimos - "
					+ itensPopulares.get(i).toString() + "|";
		}
		return top10;
	}

	/**
	 * Metodo que retorna a quantidade a ser atualizada na reputacao do usuario
	 * 
	 * @param dataEmprestimo
	 *            data em que foi feito o emprestimo
	 * @param dataDevolucao
	 *            data que foi feita a devolucao do item
	 * @param item
	 *            item devolvido
	 * @return retorna a quantidade a ser atualizada na reputacao do usuario
	 * @throws Exception
	 */
	public double atualizacaoReputacao(String dataEmprestimo, String dataDevolucao, Item item) throws Exception {
		int dias = this.contaDias(dataEmprestimo, dataDevolucao);
		Emprestimo emprestimoTest = emprestimoRepository.recuperar(item.getNome());
		if (dias > emprestimoTest.getTempoEmprestimo()) {
			int atraso = dias - emprestimoTest.getTempoEmprestimo();
			return -(item.getValor() * (atraso * 0.01));
		}
		return item.getValor() * 0.05;
	}

	/**
	 * Metodo feito para contar dias
	 * 
	 * @param dataInicial
	 *            data inicial do emprestimo
	 * @param dataFinal
	 *            data final do emprestimo
	 * @return
	 * @throws Exception
	 */
	public int contaDias(String dataInicial, String dataFinal) throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		Date dataInicio = df.parse(dataInicial);
		Date dataFim = df.parse(dataFinal);
		long dt = (dataFim.getTime() - dataInicio.getTime()) + 3600000;
		Long diasCorridosAnoLong = (dt / 86400000L);
		Integer diasDecorridosInt = Integer.valueOf(diasCorridosAnoLong.toString());
		return diasDecorridosInt;

	}

	/**
	 * Metodo que recupera os dados salvos do repositorio de emprestimo
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 */
	public void iniciaSistema() throws FileNotFoundException, ClassNotFoundException, IOException {
		this.emprestimoRepository.iniciaSistema();

	}

	/**
	 * Metodo que salva o sistema em seu estado atual
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void salvarSistema() throws FileNotFoundException, IOException {
		this.emprestimoRepository.salvarSistema(this.emprestimoRepository);

	}
	
}