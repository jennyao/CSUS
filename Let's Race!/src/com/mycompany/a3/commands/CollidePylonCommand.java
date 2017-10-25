package com.mycompany.a3.commands;

import java.util.Random;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CollidePylonCommand extends Command {
	GameWorld gw;
	Random num = new Random();
	
	public CollidePylonCommand(String command, GameWorld gw) {
        super(command);
        this.gw = gw;
    }
	
	public void actionPerformed(ActionEvent e) {
		//System.out.println("We hit a pylon!");
		Dialog.show("Message", "We hit a pylon!", "Okay", "");
		//gw.carCollidePylon(num.nextInt(9));
	}
}
