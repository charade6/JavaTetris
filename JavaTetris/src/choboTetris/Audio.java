package choboTetris;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public class Audio {
	Long nowFrame;
	Clip clip;
	File file;
	AudioInputStream ais;
	BooleanControl mute;
	
	public Audio(String name) {
		try {
			clip = AudioSystem.getClip();
			file = new File(TetrisMain.class.getResource("../sound/" + name +".wav").toURI());
			ais = AudioSystem.getAudioInputStream(file);
			clip.open(ais);

			mute = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
			FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			volume.setValue(-10.0f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play() {
		clip.start();
	}
	public void stop() {
		nowFrame = 0L;
		clip.stop();
		clip.close();
	}
	public void pause() {
		this.nowFrame = this.clip.getMicrosecondPosition();
		clip.stop();
	}
	public void resume() {
		try {
			clip.close();
			resetAudioStream();
			clip.setMicrosecondPosition(nowFrame);
			this.play();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void restart() {
		clip.stop(); 
		clip.close();
		resetAudioStream();
		clip.setMicrosecondPosition(0);
		this.play();
		
	}
	public void efplay() {
		resetAudioStream();
		clip.setMicrosecondPosition(0);
		this.play();
	}
	public void loop() {
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	public void resetAudioStream() {
		try {
			ais = AudioSystem.getAudioInputStream(file);
			clip.open(ais);
		}catch (Exception e) {
			
		}
	}
	
}