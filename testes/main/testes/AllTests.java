package main.testes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import main.testes.elementos.JogoDeTabuleiroTest;
import main.testes.elementos.JogoEletronicoTest;
import main.testes.elementos.TestUsuario;

/**
 * Classe que possui todos os testes
 * 
 * @author Joao Henrique
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ JogoDeTabuleiroTest.class, JogoEletronicoTest.class, TestUsuario.class, TestUsuarioController.class, EmprestimoControllerTest.class, 
	EmprestimoRepositoryTest.class, TestUsuarioRepository.class, ItemOrdenacaoDescricaoTest.class, ItemOrdenacaoValorTest.class })
public class AllTests {

}
