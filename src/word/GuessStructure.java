package word;


import java.awt.image.BufferedImage;

/**
 * Interface para usar em objetos que contem palavras a ser adivinhadas
 * @author thiagoss
 *
 */
public interface GuessStructure {

	/**
	 * Testa se o char c eh o proximo na palavra a ser adivinhada
	 * @param c o caractere a ser testado
	 * @return true caso o caractere seja o correto
	 */
	public boolean tryKey(char c);
	
	/**
	 * @return Retorna qual eh o proximo caractere da palavra
	 */
	public char getNextKey();
	
	/**
	 * @return retorna a palavra completa
	 */
	public String getWord();
	
	/**
	 * @return Retorna a parte da palavra que pode ser exibida ao usuario
	 */
	public String getVisibleWord();
	
	/**
	 * Verifica se esta palavra ja foi adivinhada
	 * @return true caso tenha sido finalizado, false do contrario
	 */
	public boolean isOver();
	
	/**
	 * 
	 * @return Retorna a imagem associada aquela palavra
	 */
	public BufferedImage getImage();
	
	/**
	 * Retorna a String representando a parte da palavra que ja foi solucionada
	 * @return a parte da palavra que ja foi solucionada
	 */
	public String getSolvedWord();
	
	/**
	 * 
	 * @return o codigo deste nivel de jogo
	 */
	public int getLevelCode();
	
}
