package com.port.UI.button.touch;

import com.port.world.PlayWorld;
import com.port.world.WorldData;

public class TouchManager {

	PlayWorld playWorld;

	private TouchDpad dpad;
	private TouchItemButton itemButton;
	private GenericTouchControl pauseButton;
	private GenericTouchControl inventoryButton;
	private GenericTouchControl sprintButton;

	public TouchManager(PlayWorld playWorld) {
		this.playWorld = playWorld;
		dpad = new TouchDpad(playWorld);
		itemButton = new TouchItemButton(playWorld);
		pauseButton = new GenericTouchControl(WorldData.WIDTH - 115, 115, "pause");
		inventoryButton = new GenericTouchControl(WorldData.WIDTH - 115, WorldData.HEIGHT - 115, "inventory");
		sprintButton = new GenericTouchControl(WorldData.WIDTH - 115, WorldData.HEIGHT - 245, "sprint");
	}

	public void addButtons() {
		this.dpad.addButtons();
		playWorld.addObject(itemButton, 100, 100);
		playWorld.addObject(inventoryButton, 100, 100);
		playWorld.addObject(pauseButton, 100, 100);
		playWorld.addObject(sprintButton, 100, 100);
	}
	
	public boolean isAnyTouched() {
		if(dpad.isAnyTouched() || itemButton.isTouched()||inventoryButton.isTouched()||pauseButton.isTouched()||sprintButton.isTouched()){
			return true;
		}
		return false;
	}

	public TouchDpad getDpad() {
		return dpad;
	}

	public TouchItemButton getItemButton() {
		return itemButton;
	}

	public GenericTouchControl getPauseButton() {
		return pauseButton;
	}

	public GenericTouchControl getInventoryButton() {
		return inventoryButton;
	}

	public GenericTouchControl getSprintButton() {
		return sprintButton;
	}

}
