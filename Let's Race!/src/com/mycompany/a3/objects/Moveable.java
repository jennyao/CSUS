package com.mycompany.a3.objects;
import java.lang.Math;

import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.util.MathUtil;

public abstract class Moveable extends GameObject implements IMoveable {
	protected int heading;
	protected int speed;
	//input time parameter later
	
	public Moveable() {
		super();
		heading = 5;
		speed = 10;
	}
	public Moveable (Point loc, int s, int c, int head, int spd) {
		super(loc, s, c);
		heading = head;
		speed = spd;
	}
	
	public void move(int time, Dimension dCmpSize) {
		double distance = speed * (time/1000);
		double deltaX = Math.cos(Math.toRadians(90-heading)) * distance;
		double deltaY = Math.sin(Math.toRadians(90-heading)) * distance;
		double currentX = deltaX + this.location.getX();
		double currentY = deltaY + this.location.getY();
		
		if ((currentX + this.size >= dCmpSize.getWidth()) || (currentX < 0))
			deltaX = -deltaX;
		if ((currentY + this.size >= dCmpSize.getHeight()) || (currentY < 0))
			deltaY = -deltaY;
		
		//newLocation(x,y) = oldLocation(x,y) + (deltaX, deltaY);
		Point curLoc = this.getLocation();
		Point loc = new Point((int) (curLoc.getX() + deltaX), (int)(curLoc.getY() + deltaY));
		this.setLocation(loc); 
	}
	public void setLocation(Point loc) {
		location = loc;
	}
	public void setSize(int givenSize) {
		  size = givenSize;
	}
	public int getHeading() {
		return heading;
	}
	public int getSpeed() {
		return speed;
	}
	public void setHeading(int givenHeading) {
		heading = givenHeading;
	}
	public void setSpeed(int givenSpeed) {
		speed = givenSpeed;
	}
	
	public String toString() {
		  String parentDesc = super.toString();
		  String myDesc = 	" heading=" + heading + 
				  			" speed=" + speed;
		  return parentDesc + myDesc;
	}
}
