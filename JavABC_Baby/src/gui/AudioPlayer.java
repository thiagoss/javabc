package gui;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
	/**
	 * Toca um clipe de audio através de um caminho especifico
	 * @param audioPath O caminho ao clipe de audio
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public static void playAudioClip(File audioPath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		AudioInputStream ais = AudioSystem.getAudioInputStream (audioPath);
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
}
