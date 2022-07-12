package com.port.UI.hud;

import com.badlogic.gdx.graphics.Color;
import com.port.entity.mover.player.Player;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

import greenfoot.Greenfoot;

public class StaminaBarPlayer extends HealthBar {

	public StaminaBarPlayer(String refText, String unitType, int initValue, int maxValue) {
		super(refText, unitType, initValue, maxValue);

		setSafeColor(Color.GOLDENROD);
		setDangerColor(Color.ORANGE);
		setBarWidth(68);
		setBarHeight(7);
		setReferenceText("");
		setTextColor(new Color(4, 69, 85, 214));

	}

	public void update() {
		Player player = ((PlayWorld) getWorld()).getPlayer();
		if (Greenfoot.isKeyDown("L-Shift")) {
			decrease();
			if (player.getStamina() > 10) {
				player.setCanSprint(true);
			} else {
				player.setCanSprint(false);
			}
		} else {
			player.setCanSprint(false);
			restore();
		}
	}

	private void decrease() {
		Player player = ((PlayWorld) getWorld()).getPlayer();

		if (((int) WorldData.elapsed*WorldData.FPS) % 2 == 0) {
			this.subtract(4);
			player.setStamina(player.getStamina() - 4);
		}

	}

	private void restore() {
		Player player = ((PlayWorld) getWorld()).getPlayer();
		if (((int) WorldData.elapsed*WorldData.FPS) % 2 == 0) {
			this.add(2);
			player.setStamina(player.getStamina()+2);
		}
		if (this.getValue() >= player.getStaminaMax()) {
			player.setStamina(player.getStaminaMax());
		}
	}

	public void act() {
		super.act();
	}

}
