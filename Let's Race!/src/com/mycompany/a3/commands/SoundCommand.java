package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class SoundCommand extends Command {
	GameWorld gw;
	
	public SoundCommand(String command, GameWorld gw) {
		super(command);
        this.gw = gw;
    }
	
	public void actionPerformed(ActionEvent e) {
		Dialog.show("Message", "Sound: ", "On", "Off");
		gw.toggleSound();
	}
}
