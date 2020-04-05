package main.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import main.exception.DadoInvalido;
import main.facade.TTFacade;

/**
 * Classe de testes referente a EmprestimoController
 * @author Redson
 *
 */
public class EmprestimoControllerTest {

	private TTFacade facade;

	/**
	 * Metodo que eh iniciado antes de qualquer outro
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		facade = new TTFacade();
		facade.cadastrarUsuario("Redson", "98888-8888", "redson.filho@ccc.ufcg.edu.br");
		facade.cadastrarUsuario("Joao", "89999-9999", "joao.soares@ccc.ufcg.edu.br");
		facade.cadastrarUsuario("Lucas", "97777-7777", "lucas.anthony@ccc.ufcg.edu.br");
		facade.cadastrarUsuario("Pedro", "96666-6666", "pedro.wanderley@ccc.ufcg.edu.br");
		facade.cadastrarEletronico("Joao", "89999-9999", "Super Mario", 20, "Super Nintendo");
		facade.cadastrarEletronico("Joao", "89999-9999", "FIFA 18", 99.90, "PS4");
		facade.cadastrarEletronico("Redson", "98888-8888", "PES 18", 99.90, "PS4");
		facade.cadastrarJogoTabuleiro("Pedro", "96666-6666", "Xadrez", 20);
		facade.cadastrarJogoTabuleiro("Lucas", "97777-7777", "Dama", 11.50);
		facade.cadastrarBluRayFilme("Lucas", "97777-7777", "Kung-Fu Futebol Clube", 3.50, 113, "Esportes/Acao", "12",
				"2001");
		facade.cadastrarBluRaySerie("Redson", "98888-8888", "The Flash", 30,
				"The Flash e uma serie de televisao americana desenvolvida por Greg Berlanti, Andrew Kreisberg e Geoff Johns, e estrelada por Grant Gustin.",
				160, "12", "Super-Heroi/Acao/Ficcao Cientifica", 3);
		facade.registrarEmprestimo("Lucas", "97777-7777", "Redson", "98888-8888", "Dama", "05/09/2017", 3);
		facade.registrarEmprestimo("Redson", "98888-8888", "Joao", "89999-9999", "The Flash", "09/08/2017", 7);
		facade.registrarEmprestimo("Joao", "89999-9999", "Redson", "98888-8888", "Super Mario", "18/08/2017", 5);
		facade.registrarEmprestimo("Lucas", "97777-7777", "Redson", "98888-8888", "Kung-Fu Futebol Clube", "29/08/2017",
				3);
		facade.registrarEmprestimo("Pedro", "96666-6666", "Lucas", "97777-7777", "Xadrez", "10/11/2017", 1);
		facade.adicionarBluRay("Redson", "98888-8888", "The Flash", 40);
	}

	/**
	 * Testa o metodo listarItensEmprestados
	 */
	@Test
	public void testListarItensEmprestados() {
		assertEquals("Dono do item: Joao, Nome do item emprestado: Super Mario|"
				+ "Dono do item: Lucas, Nome do item emprestado: Dama|Dono do item: Lucas, Nome do item emprestado: Kung-Fu Futebol Clube|"
				+ "Dono do item: Pedro, Nome do item emprestado: Xadrez|"
				+ "Dono do item: Redson, Nome do item emprestado: The Flash|", facade.listarItensEmprestados());
	}
	
	/**
	 * Testa o metodo listarItensNaoEmprestados
	 */
	@Test
	public void testListarItensNaoEmprestados() {
		assertEquals("JOGO ELETRONICO: FIFA 18, R$ 99.9, Nao emprestado, PS4|"
				+ "JOGO ELETRONICO: PES 18, R$ 99.9, Nao emprestado, PS4|", facade.listarItensNaoEmprestados());
	}
	
	/**
	 * Testa o metodo listarEmprestimosItem
	 */
	@Test
	public void testListarEmprestimosItem() {
		assertEquals("Emprestimos associados ao item: EMPRESTIMO - De: Lucas, Para: Redson, Dama, 05/09/2017, 3 dias, ENTREGA: Emprestimo em andamento|", facade.listarEmprestimosItem("Dama"));
	}
	
	/**
	 * Testa o metodo listarEmprestimosUsuarioEmprestando
	 * @throws Exception
	 */
	@Test
	public void testListarEmprestimosUsuarioEmprestando() throws Exception {
		assertEquals("Emprestimos: EMPRESTIMO - De: Lucas, Para: Redson, Dama, 05/09/2017, 3 dias, ENTREGA: Emprestimo em andamento|"
				+ "EMPRESTIMO - De: Lucas, Para: Redson, Kung-Fu Futebol Clube, 29/08/2017, 3 dias, ENTREGA: Emprestimo em andamento|", facade.listarEmprestimosUsuarioEmprestando("Lucas", "97777-7777"));
	}
	
	/**
	 * Testa o metodo listarEmprestimosUsuarioPegandoEmprestado
	 * @throws Exception
	 */
	@Test
	public void testListarEmprestimosUsuarioPegandoEmprestado() throws Exception {
		assertEquals("Emprestimos pegos: EMPRESTIMO - De: Lucas, Para: Redson, Dama, 05/09/2017, 3 dias, ENTREGA: Emprestimo em andamento|"
				+ "EMPRESTIMO - De: Lucas, Para: Redson, Kung-Fu Futebol Clube, 29/08/2017, 3 dias, ENTREGA: Emprestimo em andamento|"
				+ "EMPRESTIMO - De: Joao, Para: Redson, Super Mario, 18/08/2017, 5 dias, ENTREGA: Emprestimo em andamento|", facade.listarEmprestimosUsuarioPegandoEmprestado("Redson", "98888-8888"));
	}
	
	/**
	 * Testa o metodo listarTop10Itens
	 */
	@Test
	public void testListarTop10Itens() {
		assertEquals("1) 1 emprestimos - JOGO DE TABULEIRO: Xadrez, R$ 20.0, Emprestado, COMPLETO|"
				+ "2) 1 emprestimos - FILME: Kung-Fu Futebol Clube, R$ 3.5, Emprestado, 113 min, 12, Esportes/Acao, 2001|"
				+ "3) 1 emprestimos - JOGO DE TABULEIRO: Dama, R$ 11.5, Emprestado, COMPLETO|"
				+ "4) 1 emprestimos - JOGO ELETRONICO: Super Mario, R$ 20.0, Emprestado, Super Nintendo|"
				+ "5) 1 emprestimos - SERIE: The Flash, R$ 30.0, Emprestado, 40 min, 12, Super-Heroi/Acao/Ficcao Cientifica, Temporada 3|", facade.listarTop10Itens());
	}
	
}