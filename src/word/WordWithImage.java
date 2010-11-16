package word;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import util.FileHandling;
import util.MenuSelectable;

/**
 * Classe que representa o conjunto palavra com imagem Possui a palavra, um caminho ao arquivo wwi e um caminho ao arquivo da imagem
 * @author  abmargb, thiagoss
 */
public class WordWithImage implements Serializable, MenuSelectable {
	
	private static final long serialVersionUID = 1L;
	private String word;
	private String imageFilePath;
	private String filePath;

	/**
	 * Construtor da WordWithImage, recebe um String e 
	 * um path para uma imagem que represente esse string
	 * @param imageFilePath o path da imagem
	 * @param word a palavra representada pela imagem
	 */
	public WordWithImage(String imageFilePath, String word)  {
		setImageFilePath(imageFilePath);
		setWord(word);
	}

	/**
	 * Retorna a imagem associada a palavra
	 * @return a imagem (BufferedImage)
	 * @throws IOException 
	 */
	public BufferedImage getImage() throws IOException {
		BufferedImage image = ImageIO.read(new File(imageFilePath));
		return image;
	}

	
	/**
	 * Retorna o nome desta palavra
	 * @return    o nome da palavra
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Modifica o nome da palavra
	 * @param word    o novo nome
	 */
	public void setWord(String word) {
		this.word = word;
	}
	
	/**
	 * Retorna o caminho a imagem
	 * @return    o cominha a imagem
	 */
	public String getImageFilePath() {
		return imageFilePath;
	}
	
	/**
	 * Modifica o caminho ao arquivo
	 * @param filePath    O novo caminho
	 */
	public void setImageFilePath(String filePath) {
		this.imageFilePath = filePath;
	}
	
	/**
	 * Modifica o path do arquivo do objeto WordWithImage
	 * @param filePath  o novo path
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * retorna o path do arquivo do objeto WordWithImage
	 * @return  String com o path.
	 */
	public String getFilePath() {
		return filePath;
	}
	
	/**
	 * Checa os indices dos WordWithImages ja gravados para evitar que se sobrescreva
	 * um WordWithImage de mesmo nome, retorna um indice valido (um codigo numerico
	 * para o nome do arquivo)
	 * @param absoluteName o nome do WordWithImage (sem o codigo numerico)
	 * @return um indice valido
	 */
	private static int nextPossiblePos(String absoluteName){
		File dir = new File(FileHandling.WWIDIR);
		File[] imageList = dir.listFiles();
		int nextInt = 1;
		for (int i = 0; i<imageList.length; i++) {
			if (imageList[i].getName().indexOf(absoluteName) >=0 ) {
				nextInt++;
			}
		}
		return nextInt;
	}
	
	
	
	/**
	 * Salva o WordWithImage no diretorio correto da pasta do JavABC<br>
	 * Redimensiona imagens com largura ou altura superiores a 500 pixels para poupar
	 * espaco<br>
	 * @throws IOException - erro na gravacao/leitura dos arquivos durante o processo
	 * @throws MaxWWIWordNumberException
	 */
	public void saveWWI() throws IOException, MaxWWIWordNumberException{
		if (nextPossiblePos(this.getWord()) > 999) {
			throw new MaxWWIWordNumberException();
		} else {
			DecimalFormat format = new DecimalFormat("000");
			String WWIPath = FileHandling.WWIDIR + this.getWord() + "_" + 
				format.format(nextPossiblePos(this.getWord())) +  ".wwi";
			filePath = WWIPath;
			
			String filename = getImageFilePath().substring(getImageFilePath().lastIndexOf(FileHandling.SEPARADOR)+1,getImageFilePath().length());
			
			BufferedImage bi = ImageIO.read(new File(getImageFilePath()));
			int height = bi.getHeight();
			int width = bi.getWidth();
			double resizeRate = 0;
			BufferedImage newBi = null;
			
			if (height > 500 || width > 500) {
				if (height > width) {
					resizeRate = (double)500 /height ;
				} else {
					resizeRate = (double)500 /width ;
				}
				
				newBi = FileHandling.resizeImage(resizeRate, bi);
				
			} else {
				newBi = bi;
			}
			
			FileHandling.saveImage(newBi, FileHandling.IMAGEDIR + filename);
			setImageFilePath(FileHandling.IMAGEDIR + filename);
			FileHandling.save(this, WWIPath);
		}
	}
	
	/**
	 * Retorna o WordWithImage correspondente ao wwiName
	 * @param wwiName String com o nome do objeto no WordWithImage
	 * @return o objeto WordWithImage carregado
	 * @throws IOException - Erro na leitura do arquivo
	 * @throws ClassNotFoundException
	 */
	public static WordWithImage readWWI(String wwiName) throws IOException, ClassNotFoundException {
		return (WordWithImage)FileHandling.read(FileHandling.WWIDIR + wwiName + ".wwi");
	}
	
	/**
	 * Retorna um WordWithImage aleatorio dentre os armazenados na pasta do jogo
	 * @return um WordWithImage aleatorio, pode retornar null quando nao houverem 
	 * WordWithImage no diretorio padrao do jogo.
	 * @throws IOException - Erro na leitura do arquivo
	 * @throws ClassNotFoundException
	 */
	//
	public static WordWithImage getRandomWWI() throws IOException, ClassNotFoundException {
		File dir = new File(FileHandling.WWIDIR);
		File[] imageList = null;
		FileFilter filter = new FileFilter() {
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".wwi");
			}
		};
		
		imageList = dir.listFiles(filter);
		if(imageList != null && imageList.length > 0) {
			File randomFile = imageList[(int) (Math.random()*imageList.length)];
			return (WordWithImage)FileHandling.read(randomFile.getPath());
		} else {
			return null;
		}
	}
	
	/**
	 * Monta um ArrayList com todas as WordWithImages armazenadas em disco (na pasta do jogo)
	 * @return um ArrayList contendo as WordWithImage
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<WordWithImage> readAllWWI() throws FileNotFoundException, IOException, ClassNotFoundException {
		File dir = new File(FileHandling.WWIDIR);
		File[] fileList = dir.listFiles();
		ArrayList<WordWithImage> allWWI = new ArrayList<WordWithImage>();
		ObjectInputStream ois = null;
		for (int i = 0; i < fileList.length; i++) {
			ois = new ObjectInputStream(new FileInputStream(fileList[i]));
			allWWI.add((WordWithImage)ois.readObject());
		}
		if(ois != null) {
			ois.close();
		}
		return allWWI;
	}

	/**
	 * Deleta o arquivo correspondente a WordWithImage
	 *
	 */
	public void removeWWI() {
		File dir = new File(FileHandling.WWIDIR);
		File[] fileList = dir.listFiles();
		int i = 0;
		boolean stop = false;
		while(i < fileList.length && !stop) {
			String eachWWI = fileList[i].getPath();
			if (getFilePath().equals(eachWWI)) {
				fileList[i].delete();
				stop = true;
			}
			i++;
		}
		
		File dir2 = new File(FileHandling.IMAGEDIR);
		File[] fileList2 = dir2.listFiles();
		i = 0;
		stop = false;
		while(i < fileList2.length && !stop) {
			String eachWWI = fileList2[i].getPath();
			if (getImageFilePath().equals(eachWWI)) {
				fileList2[i].delete();
				stop = true;
			}
			i++;
		}
		
		
	}
	
	/*
	 *  (non-Javadoc)
	 * @see util.MenuSelectable#getMenuName()
	 */
	public String getMenuName() {
		return word;
	}

}
