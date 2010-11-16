package gui;

import gameLogic.JavABCBabyLogic;
import gameLogic.JavABCException;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JavABCBabyMainWindow extends JFrame  implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static final String SEPARATOR = System.getProperty("file.separator");
	
	
	private static final int SYMBOL_LABEL_X = 10;
	private static final int SYMBOL_LABEL_Y = 50;
	private static final int SYMBOL_LABEL_W = 300;
	private static final int SYMBOL_LABEL_H = 300;
	
	private static final int IMAGE_LABEL_X = SYMBOL_LABEL_X + SYMBOL_LABEL_W + 10;
	private static final int IMAGE_LABEL_Y = 50;
	private static final int IMAGE_LABEL_W = 300;
	private static final int IMAGE_LABEL_H = 300;
	private static final int IMAGE_W = IMAGE_LABEL_W;
	private static final int IMAGE_H = IMAGE_LABEL_H;
	private static final String IMAGES_PATH = "images" + SEPARATOR;
	private static final String SOUNDS_PATH = "sounds" + SEPARATOR;
	
	private static final Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.BLACK, Color.CYAN, Color.PINK,
											Color.MAGENTA, Color.ORANGE, Color.WHITE, Color.YELLOW};
	
	private static final String credits = "(c) Copyright Thiago Sousa Santos and Abmar Grangeiro Barros 2006.  All rights reserved.";
	
	private JLabel imageLabel;
	private JLabel symbolLabel;
	private JLabel creditsLabel;
	private Font symbolFont = new Font("tahoma", Font.BOLD, 200);
	private Container container;
	
	private JavABCBabyLogic game;
	
	private static final String title = "JavABC Baby";
	private static final Dimension defaultWindowSize = new Dimension(700, 500);
	
	public JavABCBabyMainWindow(){
		super(title);
		this.setSize(defaultWindowSize);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container = getContentPane();
		container.setLayout(null);
		
		configureLabels();
		this.setResizable(true);
		
		
		game = new JavABCBabyLogic(IMAGES_PATH, SOUNDS_PATH);
		
		this.addKeyListener(this);
	}
	
	private void configureLabels() {
		symbolLabel = new JLabel();
		symbolLabel.setFont(symbolFont);
		symbolLabel.setHorizontalTextPosition(JLabel.CENTER);
		symbolLabel.setVerticalTextPosition(JLabel.CENTER);
		symbolLabel.setHorizontalAlignment(JLabel.CENTER);
		symbolLabel.setVerticalAlignment(JLabel.CENTER);
		symbolLabel.setBounds(SYMBOL_LABEL_X, SYMBOL_LABEL_Y, SYMBOL_LABEL_W, SYMBOL_LABEL_H);
		symbolLabel.setFocusable(false);
		container.add(symbolLabel);
		
		imageLabel = new JLabel();
		imageLabel.setBounds(IMAGE_LABEL_X, IMAGE_LABEL_Y, IMAGE_LABEL_W, IMAGE_LABEL_H);
		imageLabel.setFocusable(false);
		container.add(imageLabel);
		
		creditsLabel = new JLabel(credits);
		Dimension size = getSize();
		creditsLabel.setBounds(size.width- 600, size.height - 60, 600, 30);
		creditsLabel.setFocusable(false);
		container.add(creditsLabel);
		
				
	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		char key = arg0.getKeyChar();
		try {
			game.keyPressed(key);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavABCException e) {
			return;
		}
		
		try {
			refreshDisplayData();
	
		
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void refreshDisplayData() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		symbolLabel.setForeground(getColor());
		Image displayImage = null;
		if(game.getDisplayImage() != null) { 
			displayImage = game.getDisplayImage().getScaledInstance(IMAGE_W, IMAGE_H, BufferedImage.SCALE_SMOOTH);
		} else {
			displayImage = new BufferedImage(IMAGE_W, IMAGE_H, BufferedImage.TYPE_INT_RGB);
		}
		imageLabel.setIcon(new ImageIcon(displayImage));
		
		symbolLabel.setText(game.getDisplayChar()+"");
		Graphics g = getGraphics();
		this.update(g);
		if(game.getDisplaySoundFile() != null) {
			AudioPlayer.playAudioClip(game.getDisplaySoundFile());
		}
	}
		
	
	private static Color getColor() {
		return colors[(int)(Math.random() * colors.length)];
	}
	
}
