package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.MapView;
import com.mycompany.a3.PointerListener;
import com.mycompany.a3.objects.GameObject;
import com.mycompany.a3.objects.IIterator;
import com.mycompany.a3.objects.ISelectable;

public class PositionCommand extends Command {
	GameWorld gw;
	//Game game;
	MapView mv;
	
	public PositionCommand(String command, GameWorld gw, MapView mv) {
		super(command);
		this.gw = gw;
		this.mv = mv;
	}
	
	public void actionPerformed(ActionEvent e) {
		Dialog.show("Position", "You may now move a fixed object.", "Okay", "");
		PointerListener myPL = new PointerListener();
		mv.addPointerPressedListener(myPL);
		Point pointerLoc = myPL.getpPtrRelPrnt();
		IIterator i = gw.getObjectList().getIterator();
		while (i.hasNext()) {
			GameObject o = i.getNext();
			if (o instanceof ISelectable) {
				if (((ISelectable) o).isSelected()) ((ISelectable) o).setLocation(pointerLoc);
		
			}
		}
	}
}
