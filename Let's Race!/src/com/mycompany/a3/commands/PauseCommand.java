package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;

public class PauseCommand extends Command {
	GameWorld gw;
	Game game;
	
	public PauseCommand(String command, GameWorld gw, Game game) {
        super(command);
        this.gw = gw;
        this.game = game;
    }
	
	public void actionPerformed(ActionEvent e) {
		
//        String playMessage = gw.isPlaying ? "Playing" : "Paused";
//        AND/OR
//        if 	(gw.isPlaying)	gw.bgSound.pause();
//		else 				gw.bgSound.play();
//        gw.isPlaying = !gw.isPlaying;
		game.setPlayMode();
		game.switchPlayMode(game.getPlayMode());
        if 	(game.getPlayMode()) {
        	gw.bgSound.play();
        	gw.isPlaying = true;
        } 
        else {
        	gw.bgSound.pause();
        	gw.isPlaying = false;
        	
        }
        
//        String playStatus = gw.isPlaying ? "Playing" : "Paused";      
//        game.changePauseStatus(playStatus);
         
        //Dialog.show("Paused", "Play?", "Resume", "");
		//Dialog.show("Message", "Pause", "Okay", "");
        
		//gw.pause();
	}

	public static void setText(String text) {
		// TODO Auto-generated method stub	
	}
}