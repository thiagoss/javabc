package symbol;

import java.awt.image.BufferedImage;
import java.io.File;

public class SymbolWithImage {

	private char symbol;
	private BufferedImage image;
	File soundPath;
	
	public SymbolWithImage(char s) {
		this(s, null, null);
	}
	
	public SymbolWithImage(char symbol, BufferedImage bi) {
		this(symbol, bi, null);
	}
	
	public SymbolWithImage(char symbol, BufferedImage image, File soundPath) {
		this.symbol = symbol;
		this.image = image;
		this.soundPath = soundPath;
	}
	
	public boolean equals(Object o) {
		if(o instanceof SymbolWithImage) {
			SymbolWithImage other = (SymbolWithImage) o;
			return symbol == other.symbol && image.equals(other.image);
		}
		return false;
	}
	
	public char getChar() {
		return symbol;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public File getSoundFile() {
		return soundPath;
	}
	
}
