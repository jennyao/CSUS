package com.mycompany.a3.objects;

import com.codename1.ui.geom.Dimension;

public interface IMoveable {
	public void move(int time, Dimension dCmpSize);
	public String getName();
	
}