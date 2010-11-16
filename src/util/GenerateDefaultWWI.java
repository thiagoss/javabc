package util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import word.MaxWWIWordNumberException;
import word.WordWithImage;

/**
 * Classe que gera os WWI padroes, atraves das imagens presentes na pasta /data/images
 * ou a partir de um diretorio definido pelo usuario
 * @author Abmar, Thiago
 *
 */
public class GenerateDefaultWWI {


	
	/**
	 * Metodo que gera os WWI padroes
	 * @throws IOException
	 * @throws MaxWWIWordNumberException
	 */
	public static void generateDefaultWWI(String imagesDir) throws IOException, MaxWWIWordNumberException {
		File dir = new File(imagesDir);
		File[] imageList = dir.listFiles();
		
		FileFilter filter = new FileFilter() {
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".jpg") ||
				f.getName().toLowerCase().endsWith(".gif") ||
				f.getName().toLowerCase().endsWith(".bmp") ||
				f.getName().toLowerCase().endsWith(".png");
			}
		};
		
		imageList = dir.listFiles(filter);
		
		
		
		for (int i = 0; i<imageList.length; i++) {
			
			String absoluteName = imageList[i].getName().substring(0, imageList[i].getName().lastIndexOf("_"));
			WordWithImage tempWWI = new WordWithImage(imageList[i].getPath(), absoluteName);
			
			tempWWI.saveWWI();
		} 
	}
	
	/**
	 * Gera os WWI padroes
	 * Gera wwis a partir da pasta /data/images se nenhum argumento for digitado
	 * Quando um argumento é digitado, gera wwis a partir desse diretório
	 * @param args O diretório onde se encontram as imagens
	 */
	public static void main(String[] args){
		try {
			if (args.length == 0) {
				generateDefaultWWI(FileHandling.IMAGEDIR);
			} else if (args.length == 1) {
				generateDefaultWWI(args[0]);
			} else {
				System.out.println("Sintaxe incorreta!");
			}
		} catch (IOException e) {
			System.out.println("Diretorio nao encontrado");
		} catch (MaxWWIWordNumberException e) {
			System.out.println("Numero maximo de palavras cadastradas alcancado");
		}
		
	}

}
