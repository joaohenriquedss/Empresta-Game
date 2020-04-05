package main.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.exception.DadoInvalido;
import main.facade.TTFacade;

/**
 * Testa o controller de usuario
 * 
 * @author Joao Henrique
 *
 */
public class TestUsuarioController {

	private TTFacade facade;

	/**
	 * Metodo que eh executado antes da execussão de qualquer outro
	 * 
	 * @throws Exception
	 */
	@Before
	public void testAdicionar() throws Exception {
		facade = new TTFacade();
		facade.cadastrarUsuario("Joao", "3322", "henrique@gmail.com");
		facade.cadastrarBluRayFilme("Joao", "3322", "Pneu Assasino", 10, 60, "Terror", "14", "2016");
		facade.cadastrarBluRayFilme("Joao", "3322", "Jogos Mortais", 15, 60, "Terror", "16", "2016");
		facade.cadastrarBluRayFilme("Joao", "3322", "Xuxa", 2, 60, "Infantil", "14", "2016");
		facade.cadastrarBluRayFilme("Joao", "3322", "Xuxa o retorno do pacto", 50, 60, "Terror", "14", "2020");
		facade.cadastrarEletronico("Joao", "3322", "Guitarrinha", 150, "Pancadao");
		facade.cadastrarUsuario("Redson", "7813", "redson@ccc.ufcg.br");
		facade.cadastrarUsuario("Giovana", "2233", "giovana@ccc.ufcg.br");
		facade.cadastrarUsuario("Matheus", "1234", "matheus@ccc.ufcg.br");
		facade.cadastrarEletronico("Matheus", "1234", "GTA V", 109.90, "XBOX");
		facade.cadastrarJogoTabuleiro("Giovana", "2233", "Dama", 10);
		facade.cadastrarBluRaySerie("Redson", "7813", "24 Horas", 100, "Vida loka", 500, "18", "Acao", 1);
		facade.cadastrarBluRaySerie("Redson", "7813", "Supernatural", 100, "Cacando o capeta", 500, "18", "Acao", 1);
		facade.registrarEmprestimo("Redson", "7813", "Giovana", "2233", "24 Horas", "21/08/2017", 7);
		facade.registrarEmprestimo("Redson", "7813", "Joao", "3322", "Supernatural", "21/08/2017", 7);
		facade.registrarEmprestimo("Joao", "3322", "Matheus", "1234", "Guitarrinha", "25/09/2017", 2);
		facade.devolverItem("Redson", "7813", "Giovana", "2233", "24 Horas", "21/08/2017", "10/09/2017");
		facade.devolverItem("Redson", "7813", "Joao", "3322", "Supernatural", "21/08/2017", "12/08/2017");
		facade.devolverItem("Joao", "3322", "Matheus", "1234", "Guitarrinha", "25/09/2017", "27/09/2017");
	}

	/**
	 * Testa o metodo adicionar
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAdicionarException() throws Exception {
		try {
			facade.cadastrarUsuario("", "3322-55544", "jf");
		} catch (DadoInvalido e) {
			assertEquals("Dado invalido", e.getMessage());
		}
	}

	/**
	 * Testa o metodo getInfoUsuario()
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetInfoUsuario() throws Exception {
		assertEquals("henrique@gmail.com", facade.getInfoUsuario("Joao", "3322", "email"));
	}

	/**
	 * Testa o metodo ordenacaoValor()
	 */
	@Test
	public void testOrdenacaoValor() {
		assertEquals(
				"FILME: Xuxa, R$ 2.0, Nao emprestado, 60 min, 14, Infantil, 2016|"
				+ "FILME: Pneu Assasino, R$ 10.0, Nao emprestado, 60 min, 14, Terror, 2016|"
				+ "JOGO DE TABULEIRO: Dama, R$ 10.0, Nao emprestado, COMPLETO|"
				+ "FILME: Jogos Mortais, R$ 15.0, Nao emprestado, 60 min, 16, Terror, 2016|"
				+ "FILME: Xuxa o retorno do pacto, R$ 50.0, Nao emprestado, 60 min, 14, Terror, 2020|"
				+ "SERIE: Supernatural, R$ 100.0, Nao emprestado, 0 min, 18, Acao, Temporada 1|"
				+ "SERIE: 24 Horas, R$ 100.0, Nao emprestado, 0 min, 18, Acao, Temporada 1|"
				+ "JOGO ELETRONICO: GTA V, R$ 109.9, Nao emprestado, XBOX|"
				+ "JOGO ELETRONICO: Guitarrinha, R$ 150.0, Nao emprestado, Pancadao|",
				facade.listarItensOrdenadosPorValor());
	}

	/**
	 * Testa o metodo ordenaNome())
	 */
	@Test
	public void testOrdenacaNome() {
		assertEquals(
				"SERIE: 24 Horas, R$ 100.0, Nao emprestado, 0 min, 18, Acao, Temporada 1|"
				+ "JOGO DE TABULEIRO: Dama, R$ 10.0, Nao emprestado, COMPLETO|"
				+ "JOGO ELETRONICO: GTA V, R$ 109.9, Nao emprestado, XBOX|"
				+ "JOGO ELETRONICO: Guitarrinha, R$ 150.0, Nao emprestado, Pancadao|"
				+ "FILME: Jogos Mortais, R$ 15.0, Nao emprestado, 60 min, 16, Terror, 2016|"
				+ "FILME: Pneu Assasino, R$ 10.0, Nao emprestado, 60 min, 14, Terror, 2016|"
				+ "SERIE: Supernatural, R$ 100.0, Nao emprestado, 0 min, 18, Acao, Temporada 1|"
				+ "FILME: Xuxa, R$ 2.0, Nao emprestado, 60 min, 14, Infantil, 2016|"
				+ "FILME: Xuxa o retorno do pacto, R$ 50.0, Nao emprestado, 60 min, 14, Terror, 2020|",
				facade.listarItensOrdenadosPorNome());
	}

	/**
	 * Testa o metodo getInfoItem()
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetInfoItem() throws Exception {
		assertEquals("50.0", facade.getInfoItem("Joao", "3322", "Xuxa o retorno do pacto", "preco"));
		assertEquals("2.0", facade.getInfoItem("Joao", "3322", "Xuxa", "preco"));
	}

	/**
	 * Testa o metodo pesquisarDetalhesItem()
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPesquisarDetalhesItem() throws Exception {
		assertEquals("SERIE: Supernatural, R$ 100.0, Nao emprestado, 0 min, 18, Acao, Temporada 1",
				facade.pesquisarDetalhesItem("Redson", "7813", "Supernatural"));
	}
	
	/**
	 * Testa o metodo listarCaloteiros
	 */
	@Test
	public void testListarCaloteiros() {
		assertEquals("Lista de usuarios com reputacao negativa: Giovana, giovana@ccc.ufcg.br, 2233|", facade.listarCaloteiros());
	}
	
	/**
	 * Testa o metodo listarTop10MelhoresUsuarios
	 */
	@Test
	public void testListarTop10MelhoresUsuarios() {
		assertEquals("1: Joao - Reputacao: 31,35|2: Redson - Reputacao: 30,00|3: Matheus - Reputacao: 13,00|4: Giovana - Reputacao: -12,50|", facade.listarTop10MelhoresUsuarios());
	}
	
	/** 
	 * Testa o metodo listarTop10PioresUsuarios
	 */
	@Test
	public void testListarTop10PioresUsuarios() {
		assertEquals("1: Giovana - Reputacao: -12,50|2: Matheus - Reputacao: 13,00|3: Redson - Reputacao: 30,00|4: Joao - Reputacao: 31,35|", facade.listarTop10PioresUsuarios());
	}
}
