package main.testes.elementos;

import static org.junit.Assert.*;

import org.junit.Test;

import main.elementos.bluray.Serie;
import main.exception.DadoInvalido;

/**
 * Classe de testa da classe Serie
 * 
 * @author Giovana Brito Oliveira - 116110904
 *
 */
public class SerieTest {

	/**
	 * Testa a criacao de uma serie com a descricao nula
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = NullPointerException.class)
	public void testDescricaoNula() throws DadoInvalido {
		Serie serie = new Serie("nome", 10.0, null, 40, "MAIORES", "comedia", 1);
	}

	/**
	 * Testa a criacao de uma seria com a descricao vazia
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = DadoInvalido.class)
	public void testDescricaoVazia() throws DadoInvalido {
		Serie serie = new Serie("nome", 10.0, "", 40, "MAIORES", "comedia", 1);
	}

	/**
	 * Testando a criacao de uma serie com o genero nulo
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = NullPointerException.class)
	public void testGeneroNulo() throws DadoInvalido {
		Serie serie = new Serie("nome", 10.0, "Uma serie qualquer", 50, "MAIORES DE 18", null, 1);
	}

	/**
	 * Testa a criacao de uma serie com o genero vazio
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = DadoInvalido.class)
	public void testGeneroVazio() throws DadoInvalido {
		Serie serie = new Serie("nome", 10.0, "Uma serie qualquer", 50, "MAIORES DE 18", "", 1);
	}

	/**
	 * Testa a criacao de uma seria com a temporada igual a zero
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = DadoInvalido.class)
	public void testTemporadaIgualZero() throws DadoInvalido {
		Serie serie = new Serie("nome", 10.0, "Aquela la", 50, "LIVRE", "COMEDIA", 0);
	}

	/**
	 * Testa a criacao de uma serie com a temporada menor que zero
	 * 
	 * @throws DadoInvalido
	 */
	@Test(expected = DadoInvalido.class)
	public void testTemporadaMenorQueZero() throws DadoInvalido {
		Serie serie = new Serie("nome", 10.0, "Aquela la", 50, "LIVRE", "ROMANCE", -1);
	}

	/**
	 * Metodo que testa o equals da serie
	 * 
	 * @throws DadoInvalido
	 */
	@Test
	public void testEqualsObject() throws DadoInvalido {
		Serie serie1 = new Serie("nome", 10.0, "Essa msm", 50, "LIVRE,", "ESSA MSM", 3);
		Serie serie2 = new Serie("nome", 10.0, "Essa msm", 50, "LIVRE", "ESSAAI", 3);
		Serie serie3 = new Serie("nome", 10.0, "Essa msm", 50, "LIVRE", "ESSA MSM", 2);
		assertEquals(serie1, serie2);
		assertNotEquals(serie1, serie3);
	}

	/**
	 * Testa o metodo toString() da classe serie
	 * 
	 * @throws DadoInvalido
	 */
	@Test
	public void testToString() throws DadoInvalido {
		Serie serie = new Serie("The Flash", 69.90,
				"Um cara ganha poderes de correr em alta velocidade e vira um super her�i com ajuda dos amigos", 50,
				"Livre", "Ficcao cientifica", 1);
		assertEquals("SERIE: The Flash, R$ 69.9, Nao emprestado, 0 min, Livre, Ficcao cientifica, Temporada 1",
				serie.toString());
		serie.addBluray(10);
		assertEquals("SERIE: The Flash, R$ 69.9, Nao emprestado, 10 min, Livre, Ficcao cientifica, Temporada 1",
				serie.toString());
		serie.addBluray(50);
		assertEquals("SERIE: The Flash, R$ 69.9, Nao emprestado, 60 min, Livre, Ficcao cientifica, Temporada 1",
				serie.toString());
	}

	/**
	 * Testa o metodo getDuracaoTotal() da classe serie
	 * 
	 * @throws DadoInvalido
	 */
	@Test
	public void testGetDuracaoTotal() throws DadoInvalido {
		Serie serie = new Serie("nome", 10.0, "uma descricao", 50, "livre", "ficcao", 1);
		assertEquals(0, serie.getDuracaoTotal());
		serie.addBluray(10);
		assertEquals(10, serie.getDuracaoTotal());
		serie.addBluray(30);
		assertEquals(40, serie.getDuracaoTotal());
	}

}
