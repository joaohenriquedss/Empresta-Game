package main.testes.elementos;

import static org.junit.Assert.*;
import org.junit.Test;

import main.elementos.Usuario;
import main.exception.DadoInvalido;

/**
 * Testa a classe Usuario
 * 
 * @author Joao Henrique
 *
 */
public class TestUsuario {

	private Usuario usuario;

	/**
	 * Metodo que testa o construtor de usuario
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUsuario() throws Exception {
		try {
			usuario = new Usuario("Henrique", "henrique@gmail.com", "");
		} catch (DadoInvalido e) {
			assertEquals("Dado invalido", e.getMessage());
		}
		try {
			usuario = new Usuario("Henrique", "", "3322");
		} catch (DadoInvalido e) {
			assertEquals("Dado invalido", e.getMessage());
		}
		try {
			usuario = new Usuario("", "henrique@gmail.com", "3322");
		} catch (DadoInvalido e) {
			assertEquals("Dado invalido", e.getMessage());
		}
	}
}
