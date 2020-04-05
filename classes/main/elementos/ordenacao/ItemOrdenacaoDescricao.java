package main.elementos.ordenacao;

import java.util.Comparator;

import main.elementos.Item;

/**
 * Classe que gera o objeto que ordena em ordem alfabetica.
 * 
 * @author Matheus Thiago
 */
public class ItemOrdenacaoDescricao implements Comparator<Item> {
	
	/**
	 * metodo que recebe dois objetos itens como parametro e compra qual deles vem primeiro em ordem
	 * alfabetica.
	 */
	@Override
	public int compare(Item o1, Item o2) {
		return o1.getNome().trim().toLowerCase().compareTo(o2.getNome().trim().toLowerCase());
	}

}
