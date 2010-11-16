package ui;

import java.awt.Container;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import util.MenuSelectable;

/**
 * Classe que extende o JDialog <br>
 * Funciona como esqueleto para todas os dialogs que implementam uma lista
 * @author abmargb, thiagoss
 *
 */
public class ListMenuDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8841530630598888168L;
	protected JList list = null;
	protected JScrollPane scrollPane = null;
	protected DefaultListModel model = null;
	
	protected Container container = null;
	
	/**
	 * Construtor do ListMenuDialog
	 * @param f O frame que o chama
	 * @param title O titulo desta caixa de diaolog
	 * @param items Os itens referentes a sua lista
	 */
	public ListMenuDialog(JFrame f, String title, MenuSelectable[] items) {
		super(f, title, true);
		configure();
		initList(items);
		container.add(getJScrollPane());
	}
	
	/**
	 * Inicializa as configuracoes basicas do objeto
	 *
	 */
	public void configure() {
		container = getContentPane();
		container.setLayout(null);
		this.setSize(520, 280);
	}
	
	/**
	 * Retorna a lista
	 * @return  Retorna a lista.
	 * 
	 */
	public JList getList() {
		return list;
	}
	
	/**
	 * Inicializa e retorna a lista desta caixa de dialogo
	 * @param items Os items da lista
	 * @return Retorna a lista
	 */
	public JList initList(MenuSelectable[] items) {
		model = new DefaultListModel();
		for(int i = 0; i < items.length; i++) {
			model.add(i, items[i]);
		}
		list = new JList(model);
		list.setCellRenderer(new MenuSelectableCellRenderer());
		list.setBounds(20, 50, 300, 180);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return list;
	}
	
	/**
	 * Retorna o ScrollPane
	 * @return O scrollpane desta caixa de dialogo
	 */
	public JScrollPane getJScrollPane() {
		if(scrollPane == null) {
			scrollPane = new JScrollPane(list);
			scrollPane.setBounds(20, 50, 300, 180);
		}
		return scrollPane;
	}
	
	


	
}
