package main.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.elementos.Usuario;
import main.repository.UsuarioRepository;

public class TestUsuarioRepository {
	
	private UsuarioRepository repositorio = new UsuarioRepository();
	private Usuario usuario;
	private Usuario usuario1;
	private Usuario usuario2;
	
	@Before
	public void setup() throws Exception{
		usuario = new Usuario("Henrique", "lindo@gmail.com", "1000-666");
		usuario1 = new Usuario("Redson", "redson@gmail.com", "3131-4979");
		usuario2 = new Usuario("Matheus", "teteu@gmail.com", "3131-4484");
		repositorio.adiciona(usuario);
	}
	@Test
	public void testAdiciona() throws Exception {
		usuario = new Usuario("Pablo", "pablodoarrocha@gmail.com", "1641654");
		assertTrue(repositorio.adiciona(usuario));
		assertTrue(repositorio.adiciona(usuario1));
		assertTrue(repositorio.adiciona(usuario2));
	}

	@Test
	public void testRecuperar() {
		assertEquals(usuario, repositorio.recuperar("Henrique", "1000-666"));
	}

	@Test
	public void testRemover() {
		assertTrue(repositorio.remover("Henrique", "1000-666"));
		assertFalse(repositorio.remover("Redsooon", "3131-4979"));
		
	}

}
