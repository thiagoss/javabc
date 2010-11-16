package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import word.MaxWWIWordNumberException;
import word.WordWithImage;

/**
 * Caixa de dialogo para administração de palavras
 * Extende JDialog
 * @author abmargb, thiagoss
 */
public class WordManagerDialog extends JDialog implements ActionListener {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2785686879105841102L;
	private JLabel imageLabel = null;
	private JButton addButton = null;
	private JButton removeButton = null;
	private JButton backButton = null;
	private JList wordList = null;
	private DefaultListModel listModel = null;
	private JScrollPane listScroll = null;
	
	private Container container = null;
	
	/**
	 * Construtor do WordManagerDialog
	 * @param f O frame que chama esta caixa de dialogo
	 * @param title O titulo desta caixa caixa de dialogo
	 */
	protected WordManagerDialog(Frame f, String title) {
		super(f, title, true);
		configure();
		addComponents();
	}
	
	/**
	 * Inicializa as configuracoes basicas do objeto
	 *
	 */
	private void configure() {
		this.setSize(550, 300);
		container = getContentPane();
		container.setLayout(null);
	}
	
	/**
	 * Adiciona os componentes ao container
	 *
	 */
	private void addComponents() {
		container.add(getImageLabel());
		container.add(getAddButton());
		container.add(getRemoveButton());
		container.add(getBackButton());
		container.add(getListScroll());
	}
	
	/**
	 * Retorna o label que contem a imagem, caso eles esteja com valor nulo, inicializa-o
	 * @return o label que contem a imagem.
	 */
	public JLabel getImageLabel() {
		if (imageLabel == null) {
			imageLabel = new JLabel(new ImageIcon(new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB)));
			imageLabel.setBounds(20, 20, 100, 100);
			imageLabel.setBackground(Color.WHITE);
		}
		return imageLabel;
	}
	
	/**
	 * Retorna o botao de adicionar, caso eles esteja com valor nulo, inicializa-o
	 * @return o botao de adicionar.
	 */
	public JButton getAddButton() {
		if(addButton == null) {
			addButton = new JButton("Adicionar");
			addButton.setBounds(420, 20, 100, 30);
			addButton.addActionListener(this);
		}
		return addButton;
	}
	
	/**
	 * Retorna o botao de remover, caso eles esteja com valor nulo, inicializa-o
	 * @return o botao de remover.
	 */
	public JButton getRemoveButton() {
		if(removeButton == null) {
			removeButton = new JButton("Remover");
			removeButton.setBounds(420, 60, 100, 30);
			removeButton.addActionListener(this);
		}
		return removeButton;
	}
	
	/**
	 * Retorna o botao de voltar, caso eles esteja com valor nulo, inicializa-o
	 * @return o botao de voltar.
	 */
	public JButton getBackButton() {
		if(backButton == null) {
			backButton = new JButton("Voltar");
			backButton.setBounds(420, 100, 100, 30);
			backButton.addActionListener(this);
		}
		return backButton;
	}
	
	/**
	 * Retorna a lista, caso eles esteja com valor nulo, inicializa-o
	 * @return a lista com com as palavras.
	 */
	public JList getList() {
		if(wordList == null) {
			listModel = new DefaultListModel();
			Object[] wwis = null;
			try {
				wwis = WordWithImage.readAllWWI().toArray();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i = 0; i < wwis.length; i++) {
				listModel.add(i, wwis[i]);
			}
			wordList = new JList(listModel);
			wordList.setCellRenderer(new MenuSelectableCellRenderer());
			wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			wordList.addListSelectionListener(new ListSelectionListener() {

				public void valueChanged(ListSelectionEvent arg0) {
					selectionChanged();
					
				}
				
			});
			
		}
		return wordList;
	}
	
	/**
	 * Retorna o painel de scroll, caso eles esteja com valor nulo, inicializa-o
	 * @return o painel de scroll.
	 */
	public JScrollPane getListScroll() {
		if(listScroll == null) {
			listScroll = new JScrollPane(getList());
			listScroll.setBounds(150, 20, 250, 200);
		}
		return(listScroll);
	}

	/**
	 * Executada quando a selação é alterada
	 * Muda o wwi corrente e atualiza o label da imagem
	 */
	public void selectionChanged() {
		WordWithImage wwi = (WordWithImage) wordList.getSelectedValue();
		if(wwi != null) {
			try {
				imageLabel.setIcon(new ImageIcon(
					wwi.getImage().getScaledInstance(100, 100, BufferedImage.SCALE_FAST)));
			} catch (IOException e) {
			// 	TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Apaga a wwi selecionada e remove da lista
	 *
	 */
	public void removeWord() {
		
		int index = wordList.getSelectedIndex();
		if(index != -1) {
			WordWithImage wwi = (WordWithImage) listModel.remove(index);			
			wwi.removeWWI();
		}
	}
	
	/**
	 * Configura as acoes determinadas pelos botoes
	 */
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == backButton) {
			this.dispose();
		} else if(arg0.getSource() == addButton) {
			WordWithImage wwi = AddWordDialog.showAddWWIDialog(this, "Crie sua palavra");
			if(wwi != null) {
				try {
					wwi.saveWWI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MaxWWIWordNumberException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listModel.addElement(wwi);
			}
		} else if(arg0.getSource() == removeButton) {
			removeWord();
		} 
		
	}
	
}
