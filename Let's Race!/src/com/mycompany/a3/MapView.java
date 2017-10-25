package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.objects.Cars;
import com.mycompany.a3.objects.GameObject;
import com.mycompany.a3.objects.GameObjectCollection;
import com.mycompany.a3.objects.IDrawable;
import com.mycompany.a3.objects.IIterator;
import com.mycompany.a3.objects.ISelectable;

public class MapView extends Container implements Observer {

	private GameWorld gw;				

	public MapView(GameWorld gameWorld) {
		gw = gameWorld;
		this.setLayout(new BorderLayout(CENTER));
//		this.getAllStyles().setTextDecoration(textDecoration, override);
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBorder(Border.createLineBorder(8, ColorUtil.BLUE));
	}

	public void update (Observable o, Object arg) {
		this.repaint();		//repaint();
	}
	
	public void setSelected(Point pPtrRelPrnt) {
		Point pCmpRelPrnt = new Point(this.getX(),this.getY());
		IIterator i = gw.getObjectList().getIterator();
		while (i.hasNext()) {
			GameObject o = i.getNext();
			if (o instanceof ISelectable) {
				((ISelectable) o).contains(pPtrRelPrnt, pCmpRelPrnt);
			}	
		}
	}
	
	public void paint(Graphics g) {
		Point pCmpRelPrnt = new Point(this.getX(),this.getY());
		super.paint(g);
		//System.out.println("00000000000000000000000000000000000000000000000000000000");
		IIterator i = gw.getObjectList().getIterator();
//		System.out.println("(outside) i = " + i);
		while (i.hasNext()) {
			GameObject o = i.getNext();
			if (o instanceof IDrawable) o.draw(g, pCmpRelPrnt);
//			System.out.println("(inside) i = " + i);
//			System.out.println("inside paint");
		}
	}
}
