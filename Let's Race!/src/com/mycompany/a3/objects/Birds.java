package com.mycompany.a3.objects;

import java.util.Random;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.GameWorld;

public class Birds extends Moveable {
	Random Rnd = new Random();
//	Point2D loc = new Point2D (Rnd.nextInt(GameWorld.getWidth()),Rnd.nextInt(GameWorld.getHeight()));
	public Birds() {
		super();
		this.setSpeed(5 + Rnd.nextInt(11));
		this.setHeading(Rnd.nextInt(360));
	}	
	public void setColor(int c) {		
		color = c;
	}	
	
	public String getName() { return "Bird " ; }
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		xPoints[0] = (int) (this.location.getX() + pCmpRelPrnt.getX());	
		yPoints[0] = (int) (this.size/2 + this.location.getY());
		xPoints[1] = (int) (this.location.getX() + pCmpRelPrnt.getX() - this.size/2);
		yPoints[1] = (int) (this.location.getY() + pCmpRelPrnt.getY() - this.size/2);
		xPoints[2] = (int) (this.location.getX() + pCmpRelPrnt.getX() + this.size/2);
		yPoints[2] = (int) (this.location.getY() + pCmpRelPrnt.getY() - this.size/2);
		
		g.drawPolygon(xPoints, yPoints, 3);
		g.setColor(this.color);
	}
//	public String getName() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
