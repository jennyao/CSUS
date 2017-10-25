package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class BrakeCommand extends Command {
	GameWorld gw;
	
	public BrakeCommand(String command, GameWorld gw) {
        super(command);
        this.gw = gw;
    }
	
	public void actionPerformed(ActionEvent e) {
//		Dialog.show("Message", "Brake!", "Okay", "");
		gw.getCar().brake();
	}
}
