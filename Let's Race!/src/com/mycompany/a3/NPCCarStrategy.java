package com.mycompany.a3;

import com.codename1.util.MathUtil;
import com.mycompany.a3.objects.Cars;
import com.mycompany.a3.objects.GameObject;
import com.mycompany.a3.objects.GameObjectCollection;
import com.mycompany.a3.objects.IIterator;
import com.mycompany.a3.objects.IStrategy;
import com.mycompany.a3.objects.NonPlayerCar;

public class NPCCarStrategy implements IStrategy {
	private GameObjectCollection goc;
	private NonPlayerCar npc;
	
	public NPCCarStrategy(GameObjectCollection givenGOC, NonPlayerCar givenNPC) {
		this.goc = givenGOC;
		this.npc = givenNPC;
	}

	public void apply() {
		double x1 = npc.getLocation().getX();
		double x2 = getCar().getLocation().getX();
		double y1 = npc.getLocation().getY();
		double y2 = getCar().getLocation().getY();
				
		double a = x2 - x1; 
		double b = y2 - y1;
				
		//update it's heading every time it's told to move so that the heading points toward the location of the player's car
		int heading = (int) MathUtil.atan2(b, a);
		npc.setHeading(heading);
	}

	public Cars getCar() {
		IIterator i = goc.getIterator();
		while (i.hasNext()) {
			GameObject o = i.getNext();
			if (o instanceof Cars) {
					return (Cars) o;
			}
		}
		return null;
	}

}
