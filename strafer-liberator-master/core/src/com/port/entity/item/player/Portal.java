package com.port.entity.item.player;

import greenfoot.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.game.straferliberator.StraferLiberator;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.GifImage;
import com.port.utils.graphics.Transition;
import com.port.world.PlayWorld;
import com.port.world.Scroller;
import com.port.world.WorldData;

public class Portal extends PortalGun {

	HashMap<String, GifImage> directie = new HashMap<String, GifImage>();

	GifImage portalImg;
	private long time = 0;

	public Portal() {

		directie.put("A", StraferLiberator.assetManager.get("images/item/portalA.gif", GifImage.class));
		directie.put("D", StraferLiberator.assetManager.get("images/item/portalD.gif", GifImage.class));

		if (Greenfoot.getRandomNumber(12) % 2 == 0) {
			portalImg = directie.get("D");
		} else {
			portalImg = directie.get("A");
		}

		this.time = 0;

	}

	public void teleport() {
		Player player = ((PlayWorld) getWorld()).getPlayer();
		if (Greenfoot.isKeyDown("T")) {

			getWorld().addObject(new Transition("images/effects/kingcrimson.gif", 30, 1), 0, 0);

			player.setWorldX((int) (player.getWorldX() + (this.getX() + Scroller.scrolledX - player.getX())));
			player.setWorldY((int) (player.getWorldY() + (this.getY() + Scroller.scrolledY - player.getY())));

			player.setLocation(this.getX(), this.getY());

			getWorld().removeObject(this);
		}
		if (Gdx.app.getType().equals(ApplicationType.Android)) {
			if (player.getPlayWorld().getTouchManager().getItemButton().isTouched()) {
				getWorld().addObject(new Transition("images/effects/kingcrimson.gif", 30, 1), 0, 0);

				player.setWorldX((int) (player.getWorldX() + (this.getX() + Scroller.scrolledX - player.getX())));
				player.setWorldY((int) (player.getWorldY() + (this.getY() + Scroller.scrolledY - player.getY())));

				player.setLocation(this.getX(), this.getY());

				getWorld().removeObject(this);
			}
		}
	}

	public void act() {

		if (!WorldData.PAUZA) {
			teleport();
		}
		setImage(portalImg.getCurrentImage());

	}
}
