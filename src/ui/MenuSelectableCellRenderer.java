package ui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import util.MenuSelectable;

/**
 * Classe que extende JLabel
 * Representa o renderizador das celulas de lista, usado no ListMenuDialop
 * @author Windows XP
 *
 */
public class MenuSelectableCellRenderer extends JLabel implements ListCellRenderer {

  
	private static final long serialVersionUID = 719572934158095086L;

	/**
	 * Reconfigura o JLabel e o retorna, caracterizando o renderizador
	 * @param value O valor a ser mostrado
	 * @param index O indice da celula
	 * @param isSelected Se a a célula está selecionada
	 * @param cellHasFocus Se a lista e a celula tem o foco
	 */
	public Component getListCellRendererComponent(
      JList list,
      Object value,           
      int index,              
      boolean isSelected,      
      boolean cellHasFocus)    
    {
    
    		MenuSelectable item = (MenuSelectable) value;
        String s = item.getMenuName();
        setText(s);
        
          if (isSelected) {
            setBackground(list.getSelectionBackground());
              setForeground(list.getSelectionForeground());
          }
          else {
              setBackground(list.getBackground());
              setForeground(list.getForeground());
          }
          setEnabled(list.isEnabled());
          setFont(list.getFont());
        setOpaque(true);
        return this;
    }
}

