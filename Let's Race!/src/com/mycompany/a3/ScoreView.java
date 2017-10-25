package com.mycompany.a3;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.objects.GameObject;
import com.mycompany.a3.objects.IIterator;

public class ScoreView extends Container implements Observer {
	private GameWorld gw;
	
	private Label time = new Label("Time:0");
	private Label livesLeft = new Label("Lives Left:3");
	private Label highestPylon = new Label("Highest Pylon #:0");
	private Label fuelRemaining = new Label("Fuel Remaining:100");
	private Label damageLevel = new Label("Damage Level:0");
	private Label sound = new Label("Sound:ON");
	
	public ScoreView(GameWorld gw) {
		this.gw = gw;
		
        this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        this.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.WHITE));
		
        time.getAllStyles().setFgColor(ColorUtil.BLUE);
        livesLeft.getAllStyles().setFgColor(ColorUtil.BLUE);
        highestPylon.getAllStyles().setFgColor(ColorUtil.BLUE);
        fuelRemaining.getAllStyles().setFgColor(ColorUtil.BLUE);
        damageLevel.getAllStyles().setFgColor(ColorUtil.BLUE);
        sound.getAllStyles().setFgColor(ColorUtil.BLUE);
        
        this.add(time);
        this.add(livesLeft);
        this.add(highestPylon);
        this.add(fuelRemaining);
        this.add(damageLevel);
        this.add(sound);
	}
	
	public boolean updateLabels() {
		if(gw == null) return false;
		time.setText("Time:" + Double.toString(gw.getTime()));
		livesLeft.setText("Lives Left:" + Integer.toString(gw.getLives()));
		highestPylon.setText("Highest Pylon #:" + Integer.toString(gw.getCar().getLastPylonReached()));
		fuelRemaining.setText("Fuel Remaining:" + Integer.toString(gw.getCar().getFuelLevel()));
		damageLevel.setText("Damage Level:" + Integer.toString(gw.getCar().getDamageLevel()));

		if (gw.getSoundMenu()) 
			sound.setText("Sound:ON");
		else
			sound.setText("Sound:OFF");
		return true;
	}
	
	public void update (Observable o, Object arg) {			
		// code here to update labels from the game state data
		this.updateLabels();
		this.setVisible(true);
//		System.out.println("~ Labels Updated ~");
	}
}
