package com.port.entity.item.player;

import com.port.entity.item.Item;
import com.port.entity.mover.player.Player;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

import greenfoot.*;

public class LaserHold extends Item {
	Player p;

	public LaserHold() {
		setImage("item/laserPlayerHold_" + Item.itemGif + ".png");

	}

	private void exi() {
		 p=((PlayWorld)getWorld()).getPlayer();
		if (p != null) {
			if (!p.isEquipLaser()) {
				getWorld().removeObject(this);
			}
		}
	}

	protected void move() {
		if (p != null) {
			this.setLocation(p.getX(), p.getY() - 3);
		}
	}

	public void act() {

		setImage("item/laserPlayerHold_" + Item.itemGif + ".png");
		if (!WorldData.PAUZA) {
			move();
			exi();
		}

	}
}
