package com.port.entity.item.player;

import greenfoot.*;
import java.util.HashMap;

import com.game.straferliberator.StraferLiberator;
import com.port.entity.item.Item;
import com.port.entity.mover.player.Player;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

public class SabieHold extends Item {

	String imgName = "images/item/sabieHold_" + Item.itemGif + ".png";
	Player playerr;

	public SabieHold() {
		setImage(StraferLiberator.assetManager.get(imgName, GreenfootImage.class));

	}

	private void exi() {
		if (Greenfoot.mouseClicked(null)) {
			getWorld().removeObject(this);

		}
		if (!playerr.isEquipSword()) {
			getWorld().removeObject(this);
		}
	}

	protected void move() {
		playerr = ((PlayWorld) getWorld()).getPlayer();
		setLocation(playerr.getX(), playerr.getY());

	}

	public void act() {
		imgName = "images/item/sabieHold_" + Item.itemGif + ".png";
		setImage(StraferLiberator.assetManager.get(imgName, GreenfootImage.class));
		if (!WorldData.PAUZA) {
			move();
			try {
				exi();
			} catch (Exception e) {

			}
		}

	}
}
