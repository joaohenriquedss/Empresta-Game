package main.testes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.elementos.Item;
import main.elementos.JogoDeTabuleiro;
import main.elementos.JogoEletronico;
import main.elementos.bluray.Filme;
import main.elementos.bluray.Serie;
import main.elementos.bluray.Show;
import main.elementos.ordenacao.ItemOrdenacaoValor;
import main.exception.DadoInvalido;

/**
 * Classe Teste que realizar teste da classe ItemOrdenacaoValor. Realizar teste
 * se os itens sao ordenados de acordo com o valor
 * 
 * @author Matheus Tiago
 */
public class ItemOrdenacaoValorTest {

	Item item1;
	Item item2;
	Item item3;
	Item item4;
	Item item5;
	List<Item> lista;

	/**
	 * Metodo que eh executado antes de todos os outros
	 * 
	 * @throws DadoInvalido
	 */
	@Before
	public void setup() throws DadoInvalido {
		this.item1 = new JogoDeTabuleiro("xadrez", 36.0);
		this.item2 = new JogoEletronico("zelda", 25, "Nintendo");
		this.item3 = new Filme("Rei Leao", 50, 120, "adulto", "livre", "10/02/1995");
		this.item4 = new Serie("Pessoas", 56.0, "Crianca", 220, "+16", "acao", 2);
		this.item5 = new Show("A Show", 14.22, 60, 20, "jose", "+10");
		this.lista = new ArrayList<Item>();

		this.lista.add(item1);
		this.lista.add(item2);
		this.lista.add(item3);
		this.lista.add(item4);
		this.lista.add(item5);
	}

	/**
	 * Teste para verificar se os itens estão sendo ordenado por ordem de valor.
	 * Do menor valor para o maior valor.
	 */
	@Test
	public void testOrdenacaoValor() {

		Collections.sort(this.lista, new ItemOrdenacaoValor());
		String listaString = "";

		for (Item item : lista) {
			listaString += item.getNome() + " " + item.getValor() + " - ";
		}
		assertEquals("A Show 14.22 - zelda 25.0 - xadrez 36.0 - Rei Leao 50.0 - Pessoas 56.0 - ", listaString);
	}

}
