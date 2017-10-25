package com.mycompany.a3.objects;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class NonPlayerCar extends Cars implements IStrategy {
	private IStrategy currentStrategy;
	

	public NonPlayerCar() {
		super();
		//currentStrategy = s;
	}		
	
	public void setStrategy(IStrategy s) {
		currentStrategy = s;
	}
	
	public void invokeStrategy(IStrategy s) {
		s.apply();
	}
	
	public String getName() {return "NPC " ;}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int) (this.location.getX() + pCmpRelPrnt.getX()); 
		int y = (int) (this.location.getY() + pCmpRelPrnt.getY()); 
		int r = this.size/2;
		
		g.setColor(this.color);
		g.drawArc(x, y, 2*r, 2*r, 0, 360);
	}
	public String toString() {
		String myDesc = "";
		String parentDesc = super.toString();
		myDesc = "currentStrategy is " + currentStrategy;
//		if (currentStrategy == 1) 
//			myDesc = " currentStrategy is PylonStrategy ";
//		else if (currentStrategy == 2)
//			myDesc = " currentStrategy is CarStrategy ";
		 return parentDesc + myDesc;
	}

	public void apply() {
		// TODO Auto-generated method stub
	}
}