package com.mycompany.a3;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Point;

public class PointerListener implements ActionListener {
	private Point pPtrRelPrnt;

	public Point getpPtrRelPrnt() {
		return pPtrRelPrnt;
	}

	public void setpPtrRelPrnt(Point pPtrRelPrnt) {
		this.pPtrRelPrnt = pPtrRelPrnt;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Pointer x and y: " + e.getX() + " " + e.getY());
		setpPtrRelPrnt(new Point(e.getX(), e.getY()));
	}
}
