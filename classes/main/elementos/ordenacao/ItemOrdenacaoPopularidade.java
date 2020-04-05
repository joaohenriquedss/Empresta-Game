package main.elementos.ordenacao;

import java.util.Comparator;

import main.elementos.Item;

/**
 * Classe de ordenacao por popularidade
 * @author Matheus
 *
 */
public class ItemOrdenacaoPopularidade implements Comparator<Item>{

	/**
	 * Metodo que compara itens por popularidade
	 */
	@Override
	public int compare(Item o1, Item o2) {
		if (o1.getPopularidade() > o2.getPopularidade())
			return -1;
		else if (o1.getPopularidade() < o2.getPopularidade())
			return 1;
		else
			return -1;
	}

}
