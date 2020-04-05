package main.testes.elementos;

import static org.junit.Assert.*;

import org.junit.Test;

import main.elementos.bluray.Filme;
import main.exception.DadoInvalido;

/**
 * Classe de teste da classe Filme
 * 
 * @author Giovana Brito Oliveira - 116110904
 *
 */
public class FilmeTest {

	/**
	 * Testa a criacao de um filme com nome nulo
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = NullPointerException.class)
	public void testNomeNulo() throws DadoInvalido {
		Filme filme = new Filme(null, 10.0, 50, "DRAMA", "MAIORES", "2017");
	}

	/**
	 * Testa a criacao de um filme com nome vazio
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = DadoInvalido.class)
	public void testNomeVazio() throws DadoInvalido {
		Filme filme = new Filme("", 10.0, 50, "DRAMA", "MAIORES", "2017");
	}

	/**
	 * Testa a criacao de um filme com valor menor que zero
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = DadoInvalido.class)
	public void testValorMenorQueZero() throws DadoInvalido {
		Filme filme = new Filme("nome", -1.0, 50, "DRAMA", "MAIORES", "2017");
	}

	/**
	 * Testa a criacao de um filme com valor igual a zero
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = DadoInvalido.class)
	public void testValorIgualAZero() throws DadoInvalido {
		Filme filme = new Filme("nome", 0.0, 50, "DRAMA", "MAIORES", "2017");
	}

	/**
	 * Testa a criacao de um filme com duracao igual a zero
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = DadoInvalido.class)
	public void testDuracaoZero() throws DadoInvalido {
		Filme filme = new Filme("nome", 10.0, 0, "DRAMA", "MAIORES", "2017");
	}

	/**
	 * Testa a criacao de um filme com duracao menor que zero
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = DadoInvalido.class)
	public void testDuracaoMenorQueZero() throws DadoInvalido {
		Filme filme = new Filme("nome", 10.0, -2, "DRAMA", "MAIORES", "2017");
	}

	/**
	 * Testa a criacao de um filme com classificacao vazia
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = DadoInvalido.class)
	public void testClassificacaoVazia() throws DadoInvalido {
		Filme filme = new Filme("nome", 10.0, 10, "", "MAIORES", "2017");
	}

	/**
	 * Testa a criacao de um filme com genero vazio
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = DadoInvalido.class)
	public void testGeneroVazio() throws DadoInvalido {
		Filme filme = new Filme("nome", 10.0, 10, "DRAMA", "", "2017");
	}

	/**
	 * Testa a criacao de umn filme com ano de lancamento nulo
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = NullPointerException.class)
	public void testAnoLancamentoNulo() throws DadoInvalido {
		Filme filme = new Filme("nome", 10.0, 10, "DRAMA", "MAIORES", null);
	}

	/**
	 * Testa a criacao de um filme com o ano de lancamento vazio
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = DadoInvalido.class)
	public void testAnoLancamentoVazio() throws DadoInvalido {
		Filme filme = new Filme("nome", 10.0, 10, "DRAMA", "MAIORES", "");
	}

	/**
	 * Testa o metodo toSring do filme
	 * 
	 * @throws DadoInvalido
	 */
	@Test
	public void testToString() throws DadoInvalido {
		Filme filme = new Filme("nome", 10.0, 50, "DRAMA", "MAIORES", "2017");
		assertEquals("FILME: nome, R$ 10.0, Nao emprestado, 50 min, MAIORES, DRAMA, 2017", filme.toString());
	}

	/**
	 * Testa o metodo equals
	 * 
	 * @throws DadoInvalido
	 */
	@Test
	public void testEqualsObject() throws DadoInvalido {
		Filme filme1 = new Filme("nome", 10.0, 50, "DRAMA", "MAIORES", "2017");
		Filme filme2 = new Filme("sobrenome", 10.0, 50, "DRAMA", "MAIORES", "2017");
		Filme filme3 = new Filme("nome", 10.0, 50, "DRAMA", "MAIORES", "2017");
		assertEquals(filme1, filme3);
	}
}
