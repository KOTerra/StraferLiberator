package com.port.UI.button.touch;

import com.port.entity.mover.player.Player;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

public class TouchItemButton extends GenericTouchControl {

	PlayWorld playWorld;
	Player player;

	public TouchItemButton(PlayWorld playWorld) {
		super(WorldData.WIDTH - 245, WorldData.HEIGHT - 50 - 195, "empty");
		this.playWorld=playWorld;
		player = playWorld.getPlayer();
	}

	public void changeItem() {

		if (player.isEquipSword()) {
			super.setIcon("sword");
		} else if (player.isEquipPortalGun()) {
			super.setIcon("portal");
		} else {
			super.setIcon("empty");
		}
	}
	public void act() {
		super.act();
	}

}
