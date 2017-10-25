package com.mycompany.a3.objects;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public abstract class FixedObject extends GameObject implements ISelectable {
	private boolean isSelected;
	
	public FixedObject() {
		super();
	}
	
	public FixedObject(Point loc, int s, int c) { 
		super(loc, s, c);  
	}
	
	public void setSelected(boolean yesNo) { isSelected = yesNo; }
	
	public boolean isSelected() { return isSelected; }
	
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {	return false; }
	public void draw(Graphics g, Point pCmpRelPrnt) {	}
	public void setLocation (Point loc) { this.location =loc;}
}
