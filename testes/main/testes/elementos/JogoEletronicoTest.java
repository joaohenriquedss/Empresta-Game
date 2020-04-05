package main.testes.elementos;

import org.junit.Test;

import main.elementos.JogoEletronico;
import main.exception.DadoInvalido;

/**
 * Classe de teste do JogoEletronico
 * 
 * @author Redson
 *
 */
public class JogoEletronicoTest {

	/**
	 * Teste para nome do jogo vazio, retorna exception.
	 */
	@Test(expected = Exception.class)
	public void testNomeVazio() throws DadoInvalido {
		JogoEletronico item = new JogoEletronico("", 99.99, "PS4");
	}

	/**
	 * Teste para nome do jogo = null, retorna exception.
	 */
	@Test(expected = Exception.class)
	public void testNomeNull() throws DadoInvalido {
		JogoEletronico item = new JogoEletronico(null, 99.99, "PS4");
	}

	/**
	 * Teste para preco do jogo = 0, retorna exception.
	 */
	@Test(expected = Exception.class)
	public void testPrecoNulo() throws DadoInvalido {
		JogoEletronico item = new JogoEletronico("PES 18", 0, "PS4");
	}

	/**
	 * Teste para preco do jogo negativo, retorna exception.
	 */
	@Test(expected = Exception.class)
	public void testPrecoNegativo() throws DadoInvalido {
		JogoEletronico item = new JogoEletronico("PES 18", -5, "PS4");
	}

	/**
	 * Teste para plataforma do jogo vazia, retorna exception.
	 */
	@Test(expected = Exception.class)
	public void testPlataformaVazia() throws DadoInvalido {
		JogoEletronico item = new JogoEletronico("PES 18", 99.99, "");
	}

	/**
	 * Teste para plataforma do jogo = null, retorna exception.
	 */
	@Test(expected = Exception.class)
	public void testPlataformaNull() throws DadoInvalido {
		JogoEletronico item = new JogoEletronico("PES 18", 99.99, null);
	}

}
