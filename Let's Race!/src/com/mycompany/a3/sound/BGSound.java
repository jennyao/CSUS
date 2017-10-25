package com.mycompany.a3.sound;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;
import com.mycompany.a3.GameWorld;

public class BGSound extends Sound implements Runnable {
//	private Media m;
//	private GameWorld gw;
	
	public BGSound(String fileName, GameWorld gameWorld) {
		super(fileName, gameWorld);
//		this.gw = gameWorld; 
	}
//	public void pause() { m.pause(); }
//	public void play() { m.play(); }
	
	//entered when media has finished playing
	public void run() {
		//start playing from time 0 (beginning of sound file)
		m.setTime(0);
		m.play();
	}
}
