package main.testes.elementos;

import org.junit.Test;

import main.elementos.JogoDeTabuleiro;
import main.exception.DadoInvalido;

/**
 * Classe de Teste da classe JogoDeTabuleiro
 * 
 * @author Redson
 *
 */
public class JogoDeTabuleiroTest {

	/**
	 * Teste para nome do jogo vazio, retorna exception.
	 */
	@Test(expected = Exception.class)
	public void testNomeVazio() throws DadoInvalido {
		JogoDeTabuleiro item = new JogoDeTabuleiro("", 49.90);
	}

	/**
	 * Teste para nome do jogo = null, retorna exception.
	 */
	@Test(expected = Exception.class)
	public void testNomeNull() throws DadoInvalido {
		JogoDeTabuleiro item = new JogoDeTabuleiro(null, 49.90);
	}

	/**
	 * Teste para preco do jogo = 0, retorna exception.
	 */
	@Test(expected = Exception.class)
	public void testPrecoNull() throws DadoInvalido {
		JogoDeTabuleiro item = new JogoDeTabuleiro("Banco Imobiliario", 0);
	}

	/**
	 * Teste para preco do jogo negativo, retorna exception.
	 */
	@Test(expected = Exception.class)
	public void testPrecoNegativo() throws DadoInvalido {
		JogoDeTabuleiro item = new JogoDeTabuleiro("Banco Imobiliario", -5);
	}

}
