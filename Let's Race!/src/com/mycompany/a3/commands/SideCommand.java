package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class SideCommand extends Command {
GameWorld gw;
Toolbar t;
	
	public SideCommand(String command, GameWorld gw, Toolbar t) {
        super(command);
        this.gw = gw;
        this.t = t;
    }
	
	public void actionPerformed(ActionEvent e) { 
		t.showToolbar();
	}
}
