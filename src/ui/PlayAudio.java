package ui;

import java.io.*;

import javax.sound.sampled.*;

import util.FileHandling;

/**
 * Classe que reune os metodos de manipulação de audio
 * @author abmargb, thiagoss
 *
 */
public class PlayAudio {

	/**
	 * Toca um clipe de audio através de um caminho especifico
	 * @param audioPath O caminho ao clipe de audio
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public static void playAudioClip(String audioPath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		AudioInputStream ais = AudioSystem.getAudioInputStream ( new File ( audioPath ) );
	    AudioFormat af = ais.getFormat();
	    DataLine.Info info = new DataLine.Info ( SourceDataLine.class, af );
	
		
	    int frameRate = (int)af.getFrameRate();
	    int frameSize = af.getFrameSize();
	    int bufSize = frameRate * frameSize / 10;
	     
	
	    SourceDataLine line = (SourceDataLine)
	                           AudioSystem.getLine( info );
	    line.open( af, bufSize );
	    line.start();
	
	    byte[] data = new byte[bufSize];
	    int bytesRead;
	
	    while ( ( bytesRead = ais.read( data, 0, data.length ) ) != -1 )
	        line.write( data, 0, bytesRead );
	
	    line.drain();
	    line.stop();
	    line.close();
	    //TODO closed all conections???
     
	}
	
	/**
	 * Toca um clipe de audio de acordo com o caractere digitado
	 * @param typedKey O caractere digitado
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public static void playAudioKey(char typedKey) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		char charSound = Character.toLowerCase(typedKey);
		playAudioClip(FileHandling.SOUNDSDIR+charSound+"1p1.wav");
		
	}
	
	/**
	 * Toca o clipe de áudio que representa um erro de caractere
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public static void playMistakeSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		playAudioClip(FileHandling.SOUNDSDIR+"erro.wav");
		
	}

	/**
	 * Toca o clipe de áudio que representa um acerto de palavra
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public static void playCongratSound () throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		playAudioClip(FileHandling.SOUNDSDIR+"acerto.wav");
	}

}
