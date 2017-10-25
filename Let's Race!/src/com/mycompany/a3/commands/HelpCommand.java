package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class HelpCommand extends Command {
	GameWorld gw;
	
	final String helpMe = (""
			+ "\n" + "a: Accelerate"
			+ "\n" + "b: Brake"
			+ "\n" + "l: Left"
			+ "\n" + "r: Right"
			+ "\n" + "c: Collision with a Car"
			+ "\n" + "#: Collision with a Pylon"
			+ "\n" + "f: Acquire more Fuel"
			+ "\n" + "g: Collision with a Bird"
			+ "\n" + "t: Tick"
			+ "\n" + "x: Exit Prompt"
	);
	
	public HelpCommand(String command, GameWorld gw) {
        super(command);
        this.gw = gw;
    }
	
	public void actionPerformed(ActionEvent e) {
		Dialog.show("Commands", helpMe, "Okay", null);
	}
}
