package util;

/**
 * Interface para definir itens de um menu automatico.<br>
 * O unico metodo eh o getMenuName(), que retorna o nome
 * de menu do Object
 * @author thiago
 *
 */
public interface MenuSelectable {

	/**
	 * Retorna o nome a ser exibido num menu para este objeto
	 * @return String com o nome de menu do objeto
	 */
	public String getMenuName();
	
}
