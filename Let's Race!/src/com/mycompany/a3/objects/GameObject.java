package com.mycompany.a3.objects;
//import com.mycompany.a3.GameWorld;
//import com.mycompany.a3.ICollider;
import com.mycompany.a3.*;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public abstract class GameObject implements IDrawable {
	  static final double X_MIN_LOCATION = 0.0;
	  static final double Y_MIN_LOCATION = 0.0;
	  static final double X_MAX_LOCATION = 1024;
	  static final double Y_MAX_LOCATION = 768;
	  static final int 	DEFAULT_COLOR  = ColorUtil.rgb(0, 0, 0);
	  static final int 	DEFAULT_SIZE  = 10;
	  static final int 	MAX_SIZE = 50;
	  
	  //Attributes
	  protected int size;
	  protected Point location;
	  protected int color; 
	  
	  //Constructor with default values
	  public GameObject() { 
//		  Random Rnd = new Random();
//		  Point2D loc = new Point2D (Rnd.nextInt(GameWorld.WIDTH),Rnd.nextInt(GameWorld.HEIGHT));
//		  Point loc =
		  color = DEFAULT_COLOR;
		  location =  new Point (0,0);
		  size = DEFAULT_SIZE;
		  
	  }
	  //Constructor with given values
	  public GameObject(Point loc, int s, int c) { 
		  this.location = loc;
		  this.size = s;
		  this.color = c;
	  }
	  
	  public int getSize() {
		  return size;
	  }
	  public Point getLocation() {
		  return location;
	  }
	  public int getColor() {
		  return color;
	  }
	  
	  //display Map
	  public String toString() {
		  Point loc = location;
		  double x = Math.round(loc.getX());
		  double y = Math.round(loc.getY());
			
		  int r = ColorUtil.red(color);
		  int g = ColorUtil.green(color);
		  int b = ColorUtil.blue(color);
		  String myDesc = 	" loc=" + "(" + x + ", " + y + ")" + 
				  			" color=[" + r + "," + g + "," + b + "]" +
				  			" size=" + size;
		  return myDesc;
	  }
	  
	  public boolean collidesWith(ICollider otherObject) {
		return false;
	  }
	  public void handleCollision(ICollider otherObject) {
		  
	  }
}

