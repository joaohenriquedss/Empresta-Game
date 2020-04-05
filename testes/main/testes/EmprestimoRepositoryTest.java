package main.testes;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import main.elementos.Emprestimo;
import main.elementos.JogoEletronico;
import main.elementos.Usuario;
import main.repository.EmprestimoRepository;

/**
 * Classe que testa a Classe EmprestimoRepository
 * @author redson
 *
 */
public class EmprestimoRepositoryTest {

	private EmprestimoRepository repository;
	private Emprestimo emprestimo1;
	private Emprestimo emprestimo2;
	private Usuario usuario1;
	private Usuario usuario2;
	private Usuario usuario3;
	private Date data;
	private JogoEletronico item;
	private JogoEletronico item2;
	
	@Before
	public void setUp() throws Exception {
		repository = new EmprestimoRepository();
		usuario1 = new Usuario("Joao", "redson.filho@ccc.ufcg.edu.br", "98888-8888");
		usuario2 = new Usuario("Carlos", "joao.soares@ccc.ufcg.edu.br", "89999-9999");
		usuario3 = new Usuario("Josefina", "lucas.anthony@ccc.ufcg.edu.br", "77777-7777");
		data = new Date("01/01/2018");
		item = new JogoEletronico("War", 99.90, "PS4");
		item2 = new JogoEletronico("FIFA 18", 100, "PS4");
		emprestimo1 = new Emprestimo(usuario1, usuario2, item, data, 7);
		emprestimo2 = new Emprestimo(usuario1, usuario3, item2, data, 7);
		repository.adicionar(emprestimo1);
		repository.adicionar(emprestimo2);
		
	}
	
	/**
	 * Teste para verificar se a lista contem o numero certo de elementos
	 */
	@Test
	public void testListaDeEmprestimos() {
		assertEquals(2, repository.getEmprestimos().size());
	}
	
	/**
	 * Teste para recuperar o Emprestimo a partir do nome do item.
	 */
	@Test
	public void testRecuperar() {
		assertNotNull(repository.recuperar("War"));
		assertNotNull(repository.recuperar("FIFA 18"));
	}

	/**
	 * Teste para remover o Emprestimo a partir do nome do item.
	 */
	@Test
	public void testRemove() {
		repository.remove("War");
		assertNull(repository.recuperar("War"));
	}
	
}
