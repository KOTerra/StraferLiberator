package com.port.entity.item.pickup;

import com.game.straferliberator.StraferLiberator;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.GifImage;
import com.port.world.WorldData;

import greenfoot.*;

public class Pill extends PickUp {

	int hpToAdd;
	GifImage gif=StraferLiberator.assetManager.get("images/item/pickUp/pill.gif",GifImage.class);

	private final long constantEraseTime = WorldData.FPS * 5;
	int time = 0;

	public Pill(int hpToAdd) {
		this.hpToAdd = hpToAdd;
		setImage(gif.getCurrentImage());
	}

	protected void pick() {
		if (isTouching(Player.class)) {
			Player player = (Player) getOneIntersectingObject(Player.class);
			player.takeDamage(-hpToAdd);// adauga viata
			getWorld().removeObject(this);
		}
	}

	public void act() {
		if (!WorldData.PAUZA) {
			setImage(gif.getCurrentImage());
			pick();
			time++;
			if (time > constantEraseTime) {
				getWorld().removeObject(this);
			}
		}
	}

}
