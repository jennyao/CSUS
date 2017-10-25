package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ExitCommand extends Command {
	GameWorld gw;
	
	public ExitCommand(String command, GameWorld gw) {
        super(command);
        this.gw = gw;
    }
	
	public void actionPerformed(ActionEvent e) {
		if(Dialog.show("Exit","Are you sure you want to exit?", "Y", "N")) 
			gw.exit();
	}
}
