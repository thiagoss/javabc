package gameLogic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import symbol.SymbolWithImage;
import util.FileManager;

public class JavABCBabyLogic {

	private static final long MINIMUM_TIME_DIFFERENCE = 2000;
	FileManager fileManager;
	SymbolWithImage symbol;
	private Character lastKey = null;
	private Date lastTime = null;
	
	public JavABCBabyLogic(FileManager fm) {
		fileManager = fm;
		lastKey = ' ';
		lastTime = new Date();
	}
	
	public JavABCBabyLogic(String imagesPath, String soundsPath) {
		this(new FileManager(imagesPath, soundsPath));
	}

	public char getDisplayChar() {
		return symbol.getChar();
	}
	
	public BufferedImage getDisplayImage() {
		return symbol.getImage();
	}
	
	public File getDisplaySoundFile() {
		return symbol.getSoundFile();
	}
	
	public void keyPressed(char c) throws MalformedURLException, IOException, JavABCException {
		Date actual = new Date();
		if(c == lastKey && timePassed(lastTime, actual) < MINIMUM_TIME_DIFFERENCE) {
			throw new JavABCException("Same key pressed in short time interval");
		}
		SymbolWithImage swi = fileManager.openSymbolWithImage(c);
		symbol = swi;
		lastKey = c;
		lastTime = new Date();
	}

	private long timePassed(Date lastTime2, Date actual) {
		return actual.getTime() - lastTime2.getTime();
		
	}
}
