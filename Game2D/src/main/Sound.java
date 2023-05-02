package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound implements Runnable {
	Clip clip;
	
	URL url[] = new URL[30];	
	
	Thread soundThread = new Thread(this);
	
	public Sound() {
		url[0] = getClass().getResource("/sound/bgs.wav");
		url[1] = getClass().getResource("/sound/item-pickup.wav");
		url[2] = getClass().getResource("/sound/chest_open.wav");
		url[3] = getClass().getResource("/sound/chest_close.wav");
		url[4] = getClass().getResource("/sound/hit_enemy.wav");
		url[5] = getClass().getResource("/sound/receive_dam.wav");
		url[6] = getClass().getResource("/sound/swing_sword.wav");
		url[7] = getClass().getResource("/sound/lvlup.wav");
	}
	
	public void setSoundFile(int x) {
		
		try {
			AudioInputStream as = AudioSystem.getAudioInputStream(url[x]);
			clip = AudioSystem.getClip();
			clip.open(as);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void playSound(double vol) {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue((float) vol);
		clip.start();
	}
	
	public void stopSound() {
		clip.close();
	}
	
	public void loopSound() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void startSound() {
		soundThread = new Thread(this);
		soundThread.start();
	}

	public void playBG(int i, double vol) {
		setSoundFile(i);
		playSound(vol);
		loopSound();
	}
	
	public void playSE(int i, double vol) {
		setSoundFile(i);	
		playSound(vol);
	}
	
	@Override
	public void run() {
		playBG(0, -20);
	}
	
}
