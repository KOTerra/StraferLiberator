package com.port.UI.button.touch;

import com.port.world.PlayWorld;
import com.port.world.WorldData;

import greenfoot.Actor;

public class TouchDpad {

	PlayWorld playWorld;
	GenericTouchControl dpadUp;
	GenericTouchControl dpadLeft;
	GenericTouchControl dpadDown;
	GenericTouchControl dpadRight;

	public TouchDpad(PlayWorld playWorld) {
		this.playWorld = playWorld;
	}

	public void addButtons() {
		dpadUp = new GenericTouchControl(245, WorldData.HEIGHT - 50 - 260 - 65, "dpadup");
		dpadRight = new GenericTouchControl(375, WorldData.HEIGHT - 50 - 130 - 65, "dpadright");
		dpadDown = new GenericTouchControl(245, WorldData.HEIGHT - 50 - 65, "dpaddown");
		dpadLeft = new GenericTouchControl(115, WorldData.HEIGHT - 50 - 130 - 65, "dpadleft");
		playWorld.addObject(dpadUp, 0, 0);
		playWorld.addObject(dpadRight, 0, 0);
		playWorld.addObject(dpadDown, 0, 0);
		playWorld.addObject(dpadLeft, 0, 0);

	}

	public boolean isDpadDown(String dir) {
		switch (dir) {
		case "up": {
			return dpadUp.isTouched();
		}
		case "right": {
			return dpadRight.isTouched();
		}
		case "down": {
			return dpadDown.isTouched();
		}
		case "left": {
			return dpadLeft.isTouched();
		}
		}
		return false;
	}
	
	
}
