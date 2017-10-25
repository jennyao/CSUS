package com.mycompany.a3.objects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Pylon extends FixedObject {
	private int sequenceNumber;
//	private IStrategy currentStrategy;
	
	public Pylon() {
		super();
		sequenceNumber = 0;
	}
//	public Pylon(Point loc, int s, int c, int seqNo) {
//		super(loc, s, c);
//		sequenceNumber = seqNo;
//	}
	public Pylon(Point loc, int sizePylon, int pylonCol, int seqNum) {
		super(loc, sizePylon, pylonCol);
		sequenceNumber = seqNum;
	}
	//	public void setStrategy(IStrategy s) {
//		currentStrategy = s;
//	}
//	public void invokeStrategy() {
//		currentStrategy.apply();
//	}
	public void setColor(int c) {		
		color = c;
	}
	public void setSeqNo(int givenSeqNo) {
		sequenceNumber = givenSeqNo;
	}
	public int getSeqNo() {
		return sequenceNumber;
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
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		xPoints[0] = this.location.getX() + pCmpRelPrnt.getX();	
		yPoints[0] = this.size/2 + this.location.getY();
		xPoints[1] = this.location.getX() + pCmpRelPrnt.getX() - this.size/2;
		yPoints[1] = this.location.getY() + pCmpRelPrnt.getY() - this.size/2;
		xPoints[2] = this.location.getX() + pCmpRelPrnt.getX() + this.size/2;
		yPoints[2] = this.location.getY() + pCmpRelPrnt.getY() - this.size/2;
		
		if (this.isSelected())
			g.drawPolygon(xPoints, yPoints, 3);
		else	
			g.fillPolygon(xPoints, yPoints, 3);
		g.setColor(this.color);
		g.drawString	(Integer.toString(this.sequenceNumber), 
						this.location.getX()+pCmpRelPrnt.getX(), 
						this.location.getY()+pCmpRelPrnt.getY());
	}

	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " sequenceNumber=" + this.getSeqNo();
		return parentDesc + myDesc;
	}
	
}
