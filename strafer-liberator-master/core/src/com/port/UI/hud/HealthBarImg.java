package com.port.UI.hud;

import greenfoot.*;

public class HealthBarImg extends HealthBar {

	GreenfootImage img;
	String name;
	boolean moveable;
	float x;
	float y;
	boolean added = false;

	public HealthBarImg(String name) {
		super("", "", 1, 1);
		added = false;

		this.name = name;
		switch (name) {
		case "Stroke": {
			img = new GreenfootImage("npc/inamic/stroke/healthBar.png");
			break;
		}
		case "Player": {
			img = new GreenfootImage("UI/hud/healthBar.png");
			break;
		}
		}
		setImage(img);
		moveable = false;
	}

	public void act() {
		if (!added) {
			added = true;
			x = getX();
			y = getY();
		}
		if (name.equals("Stroke")) {
			setLocation(x, y);
		}

	}

}
