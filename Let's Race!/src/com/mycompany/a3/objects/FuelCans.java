package com.mycompany.a3.objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public class FuelCans extends FixedObject {
	private int capacity;
	public FuelCans() {
		super(); 
		capacity = 0;
	}
	public FuelCans (Point loc, int s, int c, int capa) {
		super(loc, s, c);
		capacity = capa;
	}
	public void setColor(int givenColor) {
		color = givenColor;
	}
	public void setCapacity(int givenCapa) {
		capacity = givenCapa;
	}
	public int getCapacity() {
		return capacity;
	}
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " capacity=" + this.getCapacity();
		return parentDesc + myDesc;
	}
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int px = pPtrRelPrnt.getX(); // pointer location relative to
		int py = pPtrRelPrnt.getY(); // parent’s origin
		int xLoc = pCmpRelPrnt.getX()+ this.location.getX();// shape location relative
		int yLoc = pCmpRelPrnt.getY()+ this.location.getY();// to parent’s origin
		if ( (px >= xLoc) && (px <= xLoc+this.size) && (py >= yLoc) && (py <= yLoc+this.size) )
			return true; 
		else 
			return false;
	}
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = this.location.getX() + pCmpRelPrnt.getX(); 
		int y = this.location.getY() + pCmpRelPrnt.getY(); 
		int width = this.size;
		int height = this.size;
		
		g.setColor(this.color);
		if (this.isSelected())
			g.drawRect(x, y, width, height);
		else
			g.fillRect(x, y, width, height);
		g.setColor(ColorUtil.rgb(255, 0, 0));
//		g.setFont(Font font);
		g.drawString(Integer.toString(this.capacity), x, y);
	}
	
}
