package tests;

import java.io.IOException;
import junit.framework.TestCase;
import player.LoginExistsException;
import player.Player;

/**
 * Classe que testa os metodos de player.Player
 * @author  abmargb, thiagoss
 */
public class PlayerTest extends TestCase {

	/**
	 * Roda dos testes
	 * @param args
	 */
	public static void main(String[] args) {
		junit.swingui.TestRunner.run(PlayerTest.class);
	}

	Player p = new Player("p", "login");
	
	/**
	 * Inicializa as variaveis necessarias ao teste
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
	}

	/**
	 * Testa o construtor Player(String, String, long, long)
	 */
	public void testPlayerStringStringLongLong() {
		Player t = new Player("t", "log", 20, 1);
		assertEquals(t.getName(), "t");
		assertEquals(t.getLogin(), "log");
		assertEquals(t.getRights(), 1);
		assertEquals(t.getWrongs(), 20);
	}

	/**
	 * Testa o construtor Player(String, String)
	 */
	public void testPlayerStringString() {
		Player t = new Player("t", "log");
		assertEquals(t.getName(), "t");
		assertEquals(t.getLogin(), "log");
		assertEquals(t.getRights(), 0);
		assertEquals(t.getWrongs(), 0);
	}

	/**
	 * Testa o metodo setName(String)
	 */
	public void testSetName() {
		p.setName("teste");
		assertEquals("teste", p.getName());
	}

	/**
	 * Testa o metodo setRights(long)
	 */
	public void testSetRights() {
		p.setRights(40);
		assertEquals(40, p.getRights());
	}

	/**
	 * Testa o metodo setWrongs(long)
	 */
	public void testSetWrongs() {
		p.setWrongs(30);
		assertEquals(30, p.getWrongs());
	}

	/**
	 * Testa o metodo addRight()
	 */
	public void testAddRight() {
		p.setRights(30);
		p.addRight();
		assertEquals(p.getRights(), 31);
	}

	/**
	 * Testa o metodo addWrongs()
	 */
	public void testAddWrongs() {
		p.setWrongs(1);
		p.addWrongs();
		assertEquals(p.getWrongs(), 2);
	}

	/**
	 * Testa o metodo setLogin(String)
	 */
	public void testSetLogin() {
		p.setLogin("l");
		assertEquals("l", p.getLogin());
	}
	
	/**
	 * Testa o metodo savePlayer()
	 * Verifica a possibilidade de erro para salvamento de jogador com login existeste
	 * @throws IOException
	 */
	public void testSavePlayer() throws IOException {
		Player p = new Player("Player", "player");
		p.removePlayer();
		try {
			p.createPlayerFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (LoginExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		p.addRight();
		p.addRight();
		
		p.savePlayer();
		try {
			p.createPlayerFile();
			fail();
		} catch (LoginExistsException e) {
			
		}
	}
	
}
