package com.mycompany.a3.objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public class Cars extends Moveable implements IMoveable, ISteerable, ICollider {
	public static final int MAX_STEERING_DIRECTION = 40;
	public static final int MAX_DAMAGE_LEVEL = 100;
	private int steeringDirection;
	private int maximumSpeed;
	private int fuelLevel;
	private double fuelConsumptionRate;
	private int damageLevel;
	private int lastPylonReached;
	public Cars() {
		super();
			this.steeringDirection = 0;
			this.maximumSpeed = 80;
			this.fuelLevel = 100;
			this.fuelConsumptionRate = 0.1;
			this.damageLevel = 0;
			this.lastPylonReached = 0;
	}
//	public Cars(Point2D loc, int s, int c) {
//		super(loc);
//			steeringDirection = 0;
//			maximumSpeed = 80;
//			fuelLevel = 5;
//			fuelConsumptionRate = 0.1;
//			damageLevel = 0;
//			lastPylonReached= 0;
//	}
	public void steer(int givenHeading) {
		//change heading
		super.setHeading(givenHeading);
	}
	
	public void setColor(int givenColor) {
		color = givenColor;
	}
	
	public void setSteeringDirection(int givenDirection) {
		if (givenDirection <= MAX_STEERING_DIRECTION) 
			steeringDirection = givenDirection;
		//insert error handling
	}
	
	public int getSteeringDirection() {
		return steeringDirection;
	}
	
	public void setMaximumSpeed(int givenSpeed) {
		maximumSpeed = givenSpeed;
	}
	
	public int getMaximumSpeed() {
		return maximumSpeed;
	}
	
	public void setFuelLevel(int givenFuel) {
		fuelLevel = givenFuel;
	}
	
	public int getFuelLevel() {
		return fuelLevel;
	}
	
	public void setFuelConsumptionRate(double givenFuelRate) {
		fuelConsumptionRate = givenFuelRate;
	}
	
	public double getFuelConsumptionRate() {
		return fuelConsumptionRate;
	}
	
	public void setDamageLevel(int givenDamage) {
		damageLevel = givenDamage;
	}
	
	public int getDamageLevel() {
		return damageLevel;
	}
	
	public void setLastPylonReached(int givenPylon) {
		lastPylonReached = givenPylon;
	}
	
	public int getLastPylonReached() {
		return lastPylonReached;
	}
	
	public void accelerate () {
		int x = this.getSpeed() + 5;
		int dmg = this.getDamageLevel();
		int maxDmg = Cars.MAX_DAMAGE_LEVEL;
		int maxSpd = this.getMaximumSpeed();
		double fuLvl = this.getFuelLevel();
		if (dmg < maxDmg && x < maxSpd && fuLvl > 0) {
			this.setSpeed(x);
		}
//		this.setChanged();
//		this.notifyObservers();
	}
	
	public void brake() {
		int x = this.speed - 5;
		if (x < 0) 	x = 0;
		this.setSpeed(x);
	}
	
	public void steerLeft() {
		int x = this.heading - 5;
		if (x < 0) 	x = 0;
		this.heading = x;
	}
	
	public void steerRight() {
		int x = this.heading + 5;
		this.heading = x;
	}

	public String toString() {
		double rdFuelLevel = Math.round(fuelLevel);
		double rdFuelConsumptionRate = Math.round(fuelConsumptionRate);
		  String parentDesc = super.toString();
		  String myDesc = 	" steeringDirection= " + steeringDirection +
							" maximumSpeed= " + maximumSpeed  +
							" fuelLevel= " + rdFuelLevel +
							" fuelConsumptionRate= " + rdFuelConsumptionRate +
							" damageLevel= " + damageLevel +
							" lastPylonReached= " + lastPylonReached;
		  return parentDesc + myDesc;
	}	
	
	public String getName() {	return "Car";	}
	
	public boolean collidesWith(GameObject otherObject) {
		boolean result = false;
		int thisCenterX = this.location.getX() + (this.size/2); // find centers
		int thisCenterY = this.location.getY() + (this.size/2);
		int otherCenterX = otherObject.location.getX() + (this.size/2);
		int otherCenterY = otherObject.location.getY() + (this.size/2);
		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		// find square of sum of radii
		int thisRadius = this.size/2;
		int otherRadius = this.size/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
		if (distBetweenCentersSqr <= radiiSqr) { result = true ; }
		return result ;
	}
	public void handleCollision(GameObject otherObject) {
		//if otherObject is NPC
		if (otherObject instanceof Cars) {
			//Increase damage & fade color for car 1
			int x = this.getDamageLevel() + 5;
			if (x > Cars.MAX_DAMAGE_LEVEL) 	
				x = Cars.MAX_DAMAGE_LEVEL;
			this.damageLevel = x;
			this.color = ColorUtil.red(this.getColor()-50);
			//Increase damage & fade color for car 2
			((Cars) otherObject).setDamageLevel(x);
			this.color = ColorUtil.red(this.getColor()-50);
			((Cars) otherObject).setColor(otherObject.getColor()-50);
		} else if (otherObject instanceof Birds) {
			//Increase damage
			int x = this.getDamageLevel() + 3;
			if (x > Cars.MAX_DAMAGE_LEVEL) 	
				x = Cars.MAX_DAMAGE_LEVEL;
			this.damageLevel = x;
			//Fade color - get current and decrement by 50
			this.color = ColorUtil.red(this.getColor()-25);
		} else if (otherObject instanceof Pylon) {
			this.lastPylonReached = ((Pylon) otherObject).getSeqNo();
		} else if (otherObject instanceof FuelCans) {
			this.fuelLevel = this.fuelLevel + ((FuelCans) otherObject).getCapacity();
//			if (gw.sizeFuelCans <= 30)	
//				this.color = ColorUtil.red(this.getColor()+25);
//			else
				this.color = ColorUtil.red(this.getColor()+50);
		}
	}
	
	public boolean canMove() {
		if (fuelLevel == 0 || damageLevel == Cars.MAX_DAMAGE_LEVEL || speed == 0)
			return false;
		else
			return true;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int) (this.location.getX() + pCmpRelPrnt.getX()); 
		int y = (int) (this.location.getY() + pCmpRelPrnt.getY()); 
		int r = this.size/2;
		System.out.println("r: " + r + "\nx: " + x + "\ny: " + y);
		g.setColor(this.color);
		g.drawArc(x, y, 2*r, 2*r, 0, 360);
		g.fillArc(x, y, 2*r, 2*r, 0, 360);
	}
	
	public void move(int time, Dimension dCmpSize) {
		double distance = this.speed * (time/1000);
		double deltaX = Math.cos(Math.toRadians(90-this.heading)) * distance;
		double deltaY = Math.sin(Math.toRadians(90-this.heading)) * distance;
		double currentX = deltaX + this.location.getX();
		double currentY = deltaY + this.location.getY();
		
		if ((currentX + this.size >= dCmpSize.getWidth()) || (currentX < 0))
			deltaX = -deltaX;
		if ((currentY + this.size >= dCmpSize.getHeight()) || (currentY < 0))
			deltaY = -deltaY;
		
		Point curLoc = this.getLocation();
		Point loc = new Point((int) (curLoc.getX() + deltaX), (int)(curLoc.getY() + deltaY));
		this.setLocation(loc); 
		
//		this.location = new Point((int) (this.location.getX() + deltaX), (int)(this.location.getY() + deltaY)); 
		this.fuelLevel = (int) (this.fuelLevel - (this.fuelConsumptionRate*time/1000));
//		works but too well. loses too much fuel with the timer and loses game within seconds
	}
	
	
}
