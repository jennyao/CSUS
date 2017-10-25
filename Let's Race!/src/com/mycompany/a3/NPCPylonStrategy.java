package com.mycompany.a3;

import com.codename1.util.MathUtil;
import com.mycompany.a3.objects.GameObject;
import com.mycompany.a3.objects.GameObjectCollection;
import com.mycompany.a3.objects.IIterator;
import com.mycompany.a3.objects.IStrategy;
import com.mycompany.a3.objects.NonPlayerCar;
import com.mycompany.a3.objects.Pylon;

public class NPCPylonStrategy implements IStrategy {
	private GameObjectCollection goc;
	private NonPlayerCar npc;
	
	public NPCPylonStrategy(GameObjectCollection givenGOC, NonPlayerCar givenNPC) {
		this.goc = givenGOC;
		this.npc = givenNPC;
	}
	
	public void apply() {
		//code here to make car move to next pylon
		//get npc's coordinates (x1,y1)
		//get pylon's coordinates (x2,y2)
		int currentPylon = npc.getLastPylonReached();
		Pylon nextPylon = getPylon(currentPylon + 1);
		
		double x1 = npc.getLocation().getX();
		double x2 = nextPylon.getLocation().getX();
		double y1 = npc.getLocation().getY();
		double y2 = nextPylon.getLocation().getY();
		
		double a = x2 - x1;
		double b = y2 - y1;
		
		//update it's heading every time it's told to move so that the heading points toward the location of the player's car
		int heading = (int) MathUtil.atan2(b, a);
		npc.setHeading(heading);
	}
	
	public Pylon getPylon(int currentPylon) {
		IIterator i = goc.getIterator();
		while (i.hasNext()) {
			GameObject o = i.getNext();
			if (o instanceof Pylon) {
				int pyNum = ((Pylon) o).getSeqNo();
			
				if (pyNum == currentPylon) {
					return (Pylon) o;
				}
			}
			
		}
		return null;
	}
}
