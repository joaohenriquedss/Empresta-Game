package main.testes.elementos;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import main.elementos.Emprestimo;
import main.elementos.Item;
import main.elementos.JogoEletronico;
import main.elementos.Usuario;

public class EmprestimoTest {

	private Usuario usuarioDono;
	private Usuario usuarioRequerente;
	private Item item;
	private Date data;

	@Before
	public void setUp() throws Exception {
		usuarioDono = new Usuario("Redson", "redson.filho@ccc.ufcg.edu.br", "98888-8888");
		usuarioRequerente = new Usuario("Joao", "joao.soares@ccc.ufcg.edu.br", "89999-9999");
		item = new JogoEletronico("Super Mario", 15, "Super Nintendo");
		data = new Date("23/04/1997");
	}
	
	@Test(expected = Exception.class)
	public void testUsuarioDonoNull() throws Exception {
		Emprestimo emprestimo = new Emprestimo(null, usuarioRequerente, item, data, 7);
	}
	
	@Test(expected = Exception.class)
	public void testUsuarioRequerenteNull() throws Exception {
		Emprestimo emprestimo = new Emprestimo(usuarioDono, null, item, data, 7);
	}
	
	@Test(expected = Exception.class)
	public void testItemNull() throws Exception {
		Emprestimo emprestimo = new Emprestimo(usuarioDono, usuarioRequerente, null, data, 7);
	}
	
	@Test(expected = Exception.class)
	public void testDataNull() throws Exception {
		Emprestimo emprestimo = new Emprestimo(usuarioDono, usuarioRequerente, item, null, 7);
	}
	
	@Test(expected = Exception.class)
	public void testTempoEmprestimoNull() throws Exception {
		Emprestimo emprestimo = new Emprestimo(usuarioDono, usuarioRequerente, item, data, 0);
	}
	
	@Test(expected = Exception.class)
	public void testTempoEmprestimoNegativo() throws Exception {
		Emprestimo emprestimo = new Emprestimo(usuarioDono, usuarioRequerente, item, data, -1);
	}
	
}
