package player;

/**
 * Essa excecao e lancada pela classe Player para indicar que a criacao do jogador <br>
 * nao pode acontecer, ja que o login ja existe.
 * 
 * @author Abmar, Thiago
 *
 */
public class LoginExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1516430206952831915L;

	/**
	 * Construtor da excecao LoginExistsException
	 *
	 */
	public LoginExistsException() {
		super();
		
	}


}
