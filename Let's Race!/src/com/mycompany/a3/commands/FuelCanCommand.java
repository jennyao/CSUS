package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class FuelCanCommand extends Command {
	GameWorld gw;
	
	public FuelCanCommand(String command, GameWorld gw) {
        super(command);
        this.gw = gw;
    }
	
	public void actionPerformed(ActionEvent e) {
		Dialog.show("Message", "We picked up more fuel!", "Okay", "");
		//gw.carCollideFuelCan();
	}
}
