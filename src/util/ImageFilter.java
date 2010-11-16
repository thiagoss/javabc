package util;

import java.io.File;
import javax.swing.filechooser.*;

/**
 * Classe que abstrai um FileFilter para arquivos de imagem
 */

public class ImageFilter extends FileFilter {
	
	/**
	 * Retona a extens�o de um arquivo
	 * @param f Arquivo a ser analisado
	 * @return A extens�o do arquivo
	 */
	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');
		
		if (i > 0 &&  i < s.length() - 1) {
			ext = s.substring(i+1).toLowerCase();
		}
		return ext;
	}
	
	
	/**
	 * M�todo que inclui neste filtro as extens�es de imagem
	 */
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		
		String extension = getExtension(f);
		if (extension != null) {
			if (extension.equals("bmp") ||
					extension.equals("gif") ||
					extension.equals("jpeg") ||
					extension.equals("jpg") ||
					extension.equals("png")) {
				return true;
			} else {
				return false;
			}
		}
		
		return false;
	}
	
	/**
	 * Retorna a descri��o deste filtro
	 */
	public String getDescription() {
		return "Apenas Imagens";
	}
}
