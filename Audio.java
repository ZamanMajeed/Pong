//Zaman Majeed
//Audio Files found and used
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Audio {
	
	static File bounceAudio = new File("bounceEffect.wav");
	
	//plays bounce audio
	public static void playBounceAudio() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(bounceAudio);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		clip.start();
	}
	
	
	
}