package main.testes.elementos;

import static org.junit.Assert.*;

import org.junit.Test;

import main.elementos.bluray.Show;
import main.exception.DadoInvalido;

/**
 * Classe de teste da classe Show
 * 
 * @author Giovana Brito Oliveira - 116110904
 *
 */
public class ShowTest {

	/**
	 * Testa a criacao de show quando o artista eh nulo
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = NullPointerException.class)
	public void testArtistaNulo() throws DadoInvalido {
		Show show = new Show("Criador do mundo", 80.0, 120, 16, null, "Livre");
	}

	/**
	 * Testa a criacao de um show quando o artista eh vazio
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = DadoInvalido.class)
	public void testArtistaVazio() throws DadoInvalido {
		Show show = new Show("Criador do mundo", 80.0, 120, 16, "", "Livre");
	}

	/**
	 * Testa a criacao de um show quando o numero de faixas eh igual a zero
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = DadoInvalido.class)
	public void testNumeroFaixaIgualZero() throws DadoInvalido {
		Show show = new Show("Criador do mundo", 80.0, 120, 0, "Daniela Araujo", "Livre");
	}

	/**
	 * Testa a criacao de um show quando o numero de faixas eh menor que zero
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = DadoInvalido.class)
	public void testNumeroFaixaMenorZero() throws DadoInvalido {
		Show show = new Show("Criador do mundo", 80.0, 120, -1, "Daniela Araujo", "Livre");
	}

	/**
	 * Testa o metodo toString() da classe Show
	 * 
	 * @throws DadoInvalido
	 */
	@Test
	public void testToString() throws DadoInvalido {
		Show show = new Show("Criador do mundo", 80.0, 120, 16, "Daniela Araujo", "Livre");
		assertEquals("SHOW: Criador do mundo, R$ 80.0, Nao emprestado, 120 min, Livre, Daniela Araujo, 16 faixas",
				show.toString());
	}

}
