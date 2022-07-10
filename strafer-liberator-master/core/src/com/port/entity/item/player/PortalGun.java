package com.port.entity.item.player;

import greenfoot.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.game.straferliberator.StraferLiberator;
import com.port.entity.item.Item;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.GifImage;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

public class PortalGun extends Item {

	HashMap<String, GifImage> directie = new HashMap<String, GifImage>();
	GifImage portalGunImg = directie.get(super.itemGif);

	private long time = 0;

	public PortalGun() {

		directie.put("D", StraferLiberator.assetManager.get("images/item/portalGunD.gif",GifImage.class));
		directie.put("W", StraferLiberator.assetManager.get("images/item/portalGunW.gif",GifImage.class));
		directie.put("A", StraferLiberator.assetManager.get("images/item/portalGunA.gif",GifImage.class));
		directie.put("S", StraferLiberator.assetManager.get("images/item/portalGunS.gif",GifImage.class));

		portalGunImg = directie.get("D");
		this.time = 0;

	}

	protected void move() {
		super.move();

		portalGunImg = directie.get(super.itemGif);
	}

	private void addPortal() {
		if (Greenfoot.mouseClicked(null)) {
			if (Greenfoot.getMouseInfo().getButton() == 1) { // right 3 left 1
				if (getWorld().getObjects(Portal.class).isEmpty()) {
					getWorld().addObject(new Portal(), Greenfoot.getMouseInfo().getX(),
							Greenfoot.getMouseInfo().getY());
				}
			}
		}
	}

	private void removePortalGun() {
		Player player = ((PlayWorld) getWorld()).getPlayer();
		if (!player.isEquipPortalGun()) {
			getWorld().removeObject(this);
		}
	}

	public void act() {

		if (!WorldData.PAUZA) {
			move();
			addPortal();
			removePortalGun();
		}
		setImage(portalGunImg.getCurrentImage());
	}
}
