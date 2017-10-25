package com.mycompany.a3;

import com.mycompany.a3.objects.Birds;
import com.mycompany.a3.objects.Cars;
import com.mycompany.a3.objects.FuelCans;
import com.mycompany.a3.objects.GameObject;
import com.mycompany.a3.objects.GameObjectCollection;
import com.mycompany.a3.objects.ICollider;
//import com.mycompany.a3.objects.IDrawable;
import com.mycompany.a3.objects.IIterator;
import com.mycompany.a3.objects.IMoveable;
import com.mycompany.a3.objects.ISelectable;
//import com.mycompany.a3.objects.Moveable;
import com.mycompany.a3.objects.NonPlayerCar;
import com.mycompany.a3.objects.Pylon;
import com.mycompany.a3.sound.BGSound;
import com.mycompany.a3.sound.Sound;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.geom.Dimension;
//import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Point;

import java.util.Observable;
import java.util.Random;
import java.util.Vector;

public class GameWorld extends Observable {
	private static int clockTime;
	private static int lifeNum;
	private static boolean soundMenu = true;
	private static GameObjectCollection objectList = new GameObjectCollection();
	//private static IStrategy npcStrategy = new NPCCarStrategy(this, npc1); //int for no
	private static int npcStrategyNum = 1;
	public boolean isPlaying = true;
	
	public BGSound bgSound = new BGSound("background.WAV", this);
	Sound carCrashSound = new Sound("carcrash.WAV", this);
	Sound dmgSound = new Sound("hit.WAV", this);
	Sound getFuelSound = new Sound("pickup.WAV", this);
	Sound endSound = new Sound("gameover.WAV", this);
	
	public int width;
	public int height;
	
	public GameWorld(int time, int lives, int wid, int hght) {
		clockTime = time;
		lifeNum = lives;
		width = wid;
		height = hght;
	}
	
	public GameWorld() {
		clockTime = 0;
		lifeNum = 3;
		width = 1024;
		height = 768;
		isPlaying = true;
		soundMenu = true;
	}
	
	public void init(int wid, int hght) {
		int numPylon = 4;
		int numCar = 1;
		int numBirds = 2;
		int numFuelCans = 2;
		this.height =hght;
		this.width =wid;
		
		Random Rnd = new Random();
		Cars c = new Cars();
		NonPlayerCar npc1 = new NonPlayerCar();
		NonPlayerCar npc2 = new NonPlayerCar();
		NonPlayerCar npc3 = new NonPlayerCar();
		FuelCans f = new FuelCans();
		
		bgSound.play();
		
	    System.out.println("height: " + hght);
	    System.out.println("width: " + wid);
		
		int sizePylon = 30;
		int sizeCar = 40;
		int sizeBird = 30;
		int sizeFuelCans = 30;
		int pylonCol = ColorUtil.MAGENTA;
		int carCol = ColorUtil.rgb(255, 0, 0);
		int birdCol = ColorUtil.BLUE;
		int fuelCansCol = ColorUtil.GRAY;
		Point Loc = new Point(0,0);
		int seqNum = 1;
		int capacity = 10;
		
		
		int xLoc, yLoc;
//		xLoc = origin.getX() + Rnd.nextInt(wid);
//		yLoc = origin.getY() + Rnd.nextInt(hght);
//		Loc.setX(xLoc);
//		Loc.setY(yLoc);
//		System.out.println("sizeCar: " + sizeCar + "\nxLoc: " + xLoc + "\nyLoc: " + yLoc);
//		Loc = new Point (Rnd.nextInt(width),Rnd.nextInt(height));
		
		//Create 1 Car
		Loc = new Point (150,150);
//		Loc = new Point (Rnd.nextInt(wid),Rnd.nextInt(hght));
		c.setColor(carCol);
		c.setSize(sizeCar);
		c.setLocation(Loc);
		objectList.addElement(c);
		
		//Create 4 Pylons
		for(int i = 1; i <= numPylon; i++) {
			Loc = new Point(i*250,i*250);
			Pylon p = new Pylon(Loc, sizePylon, ColorUtil.YELLOW, seqNum);
			objectList.addElement(p);
			seqNum++;
		}
				
		//Create 3 NPCs
		/*each one to have an init location "near" the first pylon but not *at* it*/
		/*each NPC should be several car lengths away from the first pylon*/
		//heading initialized to a random value (btw 0-359)
		npc1.setColor(ColorUtil.BLACK);
		npc1.setSize(sizeCar);
		//Loc = new Point(0,sizeCar * 2);
		Loc = new Point (150,300);
//		Loc = new Point (Rnd.nextInt(wid),Rnd.nextInt(hght));
		npc1.setLocation(Loc);
		npc1.setHeading(Rnd.nextInt(360));
		npc1.setStrategy(new NPCCarStrategy(objectList, npc1));
		objectList.addElement(npc1);
		
		npc2.setColor(ColorUtil.BLACK);
		npc2.setSize(sizeCar);
		//Loc = new Point(sizeCar * 2, 0);
		Loc = new Point (150,400);
//		Loc = new Point (Rnd.nextInt(wid),Rnd.nextInt(hght));
		npc2.setLocation(Loc);
		npc2.setHeading(Rnd.nextInt(360));
		npc2.setStrategy(new NPCCarStrategy(objectList, npc2));
		objectList.addElement(npc2);
		
		npc3.setColor(ColorUtil.BLACK);
		npc3.setSize(sizeCar);
		//Loc = new Point(sizeCar * 2, 0);
		Loc = new Point (150,500);
//		Loc = new Point (Rnd.nextInt(wid),Rnd.nextInt(hght));
		npc3.setLocation(Loc);
		npc3.setHeading(Rnd.nextInt(360));
		npc3.setStrategy(new NPCCarStrategy(objectList, npc3));
		objectList.addElement(npc3);
		
		//Create 2 Birds
		//for (int i = 1; i <= numBirds; i++) {
			Birds b1 = new Birds();
			Loc = new Point(100,300);
			b1.setColor(birdCol);
			b1.setSize(sizeBird);
			b1.setLocation(Loc);
			objectList.addElement(b1);
			
			Birds b2 = new Birds();
			Loc = new Point(900,500);
			b2.setColor(birdCol);
			b2.setSize(sizeBird);
			b2.setLocation(Loc);
			objectList.addElement(b2);
			
		//}
		//Create 2 Fuel Cans
		//for(int i = 1; i <= numFuelCans; i++) {
//			xLoc = origin.getX() + Rnd.nextInt(wid);
//			yLoc = origin.getY() + Rnd.nextInt(hght);
			//Loc = new Point (Rnd.nextInt(width),Rnd.nextInt(height));
			
			//Loc = new Point (xLoc,yLoc);
			sizeFuelCans = 10 + Rnd.nextInt(50);
			capacity = sizeFuelCans;
			Loc = new Point (800,800);
			f = new FuelCans(Loc, sizeFuelCans, ColorUtil.GREEN, capacity);
			objectList.addElement(f);
			
			Loc = new Point (900,900);
			//Loc = new Point (xLoc,yLoc);
			sizeFuelCans = 10 + Rnd.nextInt(50);
			capacity = 2*sizeFuelCans;
			f = new FuelCans(Loc, sizeFuelCans, ColorUtil.GREEN, capacity);
			objectList.addElement(f);
		//}
		this.setChanged();
		notifyObservers();
	} //init end
	
	public double getTime() {
		return clockTime;
	}
	
	public int getLives() {
		return lifeNum;
	}
	
	public boolean getSoundMenu() {
		return soundMenu;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void update(int time) {
		clockTime += time/1000;							//increase clock time
		
		//move all moveable objects
		IIterator i = objectList.getIterator();
		while (i.hasNext()) {
			GameObject o = i.getNext();
			if (o instanceof IMoveable) {
//				System.out.println(((IMoveable) o).getName());
				((IMoveable) o).move(time, new Dimension (this.width, this.height));
		}	
		
		// check if this object collides with any OTHER object
		IIterator j = objectList.getIterator(); //p179
		while(j.hasNext()){
			GameObject o1 = j.getNext();
			if (o1 instanceof ICollider) {
				ICollider curObj = (ICollider) o1; // get a collidable object		
				IIterator j2 = objectList.getIterator();
				while(j2.hasNext()){
					GameObject otherObj = j2.getNext(); // get a collidable object
					// check for collision
					if(otherObj!=curObj){// make sure it's not the SAME object
						if(curObj.collidesWith(otherObj)){
							curObj.handleCollision(otherObj);
							if (otherObj instanceof Cars && soundMenu)			
								carCrashSound.play();
							else if (otherObj instanceof FuelCans)	{
								if (soundMenu) getFuelSound.play();
								Random Rnd = new Random();
								Point Loc = new Point (Rnd.nextInt(width),Rnd.nextInt(height));
								int sizeFuelCans = 1 + Rnd.nextInt(5);		
								int capacity = 10*sizeFuelCans;
								FuelCans nf = new FuelCans(Loc, sizeFuelCans, ColorUtil.GREEN, capacity);
								objectList.remove(otherObj);
								objectList.addElement(nf);
							}
							else if (soundMenu) 								
								dmgSound.play();
						}
					}
				}
			}
		}
//		public void ifSelected() {
//			IIterator i = objectList.getIterator();
//			while (i.hasNext()) {
//				GameObject o = i.getNext();
//				if (o instanceof ISelectable) {
////					System.out.println(((ISelectable) o).getName());
//					((ISelectable) o).contains(pPtrRelPrnt, pCmpRelPrnt);
//			}	
//		}
		if (!this.getCar().canMove() && lifeNum > 0) 	{
			this.getCar().setSpeed(0);
			lifeNum--;
		}
		if (lifeNum == 0) {
			endSound.play();
			if (Dialog.show("Game Over!","You died! Try again?", "Y", "N"))
				System.exit(0);
		}
		this.setChanged();
		this.notifyObservers(); 
	}
}
	public void switchNPCStrategy() {
		if ( npcStrategyNum == 1 ) {
			npcStrategyNum = 2;
		} else if (npcStrategyNum == 2)
			npcStrategyNum = 1;
	}

	//displayStatus
	public String toString() {
		String myDesc = "\nLives: " + lifeNum + "\n" +
						"Clock Time: " + clockTime + "\n" +
						"Pylon Number Reached: " + getCar().getLastPylonReached() + "\n" +
						"Damage Level: " + getCar().getDamageLevel() + "\n";
		return myDesc;
	}
	
	public boolean toggleSound() {
		soundMenu = !soundMenu;
        this.setChanged();
		this.notifyObservers();
		return soundMenu;
	}
	
	public void pauseSound() {
		if (!this.isPlaying) {
			bgSound.pause();
			//pause other active game sounds
		} else if (this.isPlaying && soundMenu) {
			bgSound.play();
			//diff btw play vs resume???
			//list others
		}
	}
	
	public boolean exit() {
		System.exit(0);
		return true;
	}

	public Cars getCar() {
		IIterator i = objectList.getIterator();
		while (i.hasNext()) {
			GameObject o = i.getNext();
			if (o instanceof Cars && !(o instanceof NonPlayerCar)) {
					return (Cars) o;
			}
		}
		return null;
	}
	public GameObjectCollection getObjectList() {
		return objectList;
	}
	public void setObjectList(GameObjectCollection objectList) {
		GameWorld.objectList = objectList;
	}

}
