package gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Window;



/**
 * Classe que representa o SplashWindow <br>
 * Extende Window <br>
 * O Splash window aparece na inicialização do programa
 * @author abmargb, thiagoss
 *
 */
public class SplashWindow extends Window 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6248886598683537149L;
	private final String imgName = "jABC_baby_splash.jpg";
	Image splashImage;
	Toolkit toolkit;
	private static SplashWindow splash;
	
	// Singleton pra garantir apenas uma unica instancia na memoria
	static {
		splash = new SplashWindow();
	}
	
	/**
	 * Construtor da Janela de Splash
	 *
	 */
	private SplashWindow() {
		super(new Frame());
		setVisible(false);
		splashImage = null;
		toolkit = Toolkit.getDefaultToolkit();	
	}
	
	/**
	 * Retorna a unica instancia desta janela
	 * @return A instancia de SplashWindow
	 */
	public static SplashWindow getInstance() {
		return splash;
	}
	
	/**
	 * Inicializa esta janela, carregando a imagem.
	 * Posiciona a janela no centro da tela
	 */
	private void initSplash()  {
		// Carrega a imagem na memoria
		MediaTracker media = new MediaTracker(this);
		splashImage = toolkit.getImage(imgName);
		
		media.addImage(splashImage, 0);
			
		try {
			media.waitForID(0);
		}
		catch (InterruptedException e) {
			e.printStackTrace();	
		}

		
		// Configura o tamanho do splash e a posicao na tela
		setSize(splashImage.getWidth(this), splashImage.getHeight(this));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = getSize();
		
		if (size.width > screenSize.width)
			size.width = screenSize.width;
		
		if (size.height > screenSize.height)
			size.height = screenSize.height;
			
		setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
		setVisible(true);		
	}

	/**
	 * Desenha a imagem na janela
	 */
	public void paint(Graphics g) {
		// Apenas desenha a imagem
		g.drawImage(splashImage, 0, 0, getBackground(), this);
	}
	
	/**
	 * Roda a SplashWindow, dando uma pausa de 5 segundos para a inicialização do programa
	 *
	 */
	public void runSplashScreen() {
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		initSplash();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setVisible(false);
		dispose();
	}

}

