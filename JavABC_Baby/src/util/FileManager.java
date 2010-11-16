package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


import symbol.SymbolWithImage;

//TODO - keep filenames on a list, instead of looking for them everytime (Arvores patricias? OO)
public class FileManager {

	String imagesPath;
	String soundsPath;
	
	public FileManager(String imagesPath, String soundsPath) {
		//TODO - check if are dirs;
		this.imagesPath = imagesPath;
		this.soundsPath = soundsPath;
	}
	
	public SymbolWithImage openSymbolWithImage(char c) throws MalformedURLException, IOException{
		File imageFile = getRandomImageFile(Character.toLowerCase(c));
		File soundFile = getSoundFile(Character.toLowerCase(c));
		BufferedImage image = null;
		if(imageFile != null) {
			image = ImageIO.read(imageFile.toURL());
		}
		return new SymbolWithImage(Character.toUpperCase(c), image, soundFile);
		
	}

	private File getRandomImageFile(char c) {
		File imagesDir = new File(imagesPath);
		if(!imagesDir.isDirectory()) {
			//TODO - throw exception
		}
		
		File[] imageFiles = imagesDir.listFiles(new ImageFilter());
		List<File> files = getFilesStartingWith(c, imageFiles);
		if(files.size() == 0) {
			return null;
		}
		
		return files.get((int)(Math.random() * files.size()));
	}

	private List<File> getFilesStartingWith(char c, File[] imageFiles) {
		ArrayList<File> list = new ArrayList<File>(imageFiles.length/5);
		for(int i = 0; i < imageFiles.length; i++) {
			if(imageFiles[i].getName().startsWith(c+"")) {
				list.add(imageFiles[i]);
			}
		}
		return list;
	}

	private File getSoundFile(char c) {
		File f = new File(soundsPath + c + "1p1.wav");
		if(f.exists()) {
			return f;
		}
		
		return null;
	}
	
}
