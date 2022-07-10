package com.port.entity.item.player;

import greenfoot.*;

import java.util.List;

import com.game.straferliberator.StraferLiberator;
import com.port.entity.item.Item;
import com.port.entity.mover.npc.hostile.HostileNpc;
import com.port.entity.mover.npc.hostile.SchrodingersCat;
import com.port.entity.mover.npc.hostile.Stroke;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.GifImage;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

public class IceLock extends Item {

	GifImage gif = StraferLiberator.assetManager.get("images/item/iceLock.gif",GifImage.class);

	boolean sent = false;

	public IceLock() {

	}

	private void addIceCube() {
		if (Greenfoot.mouseClicked(null)) {
			if (Greenfoot.getMouseInfo().getButton() == 1) { // right 3 left 1
				int x = (int) Greenfoot.getMouseInfo().getX();
				int y = (int) Greenfoot.getMouseInfo().getY();
				if (getWorld().getObjects(IceCube.class).isEmpty()) {
					sent = true;
					this.setLocation(x, y);
					if (isTouching(HostileNpc.class)) {
						Actor in = getOneIntersectingObject(HostileNpc.class);
						if (!(in instanceof SchrodingersCat) && !(in instanceof Stroke)) {
							getWorld().addObject(new IceCube(in, this), Greenfoot.getMouseInfo().getX(),
									Greenfoot.getMouseInfo().getY());

						}
					} else {
						sent = false;
					}
				}
			}
		}
	}

	private void removeIceLock() {
		Player player = ((PlayWorld) getWorld()).getPlayer();
		if (!player.isEquipIceLock()) {
			getWorld().removeObject(this);
		}
	}

	public void act() {

		if (!WorldData.PAUZA) {
			if (!sent) {
				move();
			}
			addIceCube();
			removeIceLock();
		}
		setImage(gif.getCurrentImage());
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}
}
