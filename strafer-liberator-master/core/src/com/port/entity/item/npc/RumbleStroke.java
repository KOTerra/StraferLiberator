package com.port.entity.item.npc;

import com.game.straferliberator.StraferLiberator;
import com.port.entity.mover.npc.hostile.HostileNpc;
import com.port.entity.mover.npc.hostile.Stroke;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.GifImage;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

import greenfoot.*;

public class RumbleStroke extends NpcItem {
	public static final int damage = 5;
	public static final int mass = 2;
	GifImage gif = StraferLiberator.assetManager.get("images/npc/inamic/stroke/rumble.gif", GifImage.class);

	private long time = 0;

	Actor stroke;

	private final long constantEraseTime = 25;
	boolean gaveDamage = false;

	public RumbleStroke(Stroke stroke) {
		this.stroke = stroke;
		this.time = 0;

	}

	public Actor getStroke() {
		return this.stroke;
	}

	protected void move() {
		super.move(this);
	}

	protected void atac() {
		Player player = ((PlayWorld) getWorld()).getPlayer();
		if (intersects(player)) {

			if (player != null) {
				player.knockbacked = true;
				player.knockback(0.1, stroke, this.mass, 80);
				if (!gaveDamage) {
					player.takeDamage(this.damage);
					gaveDamage = true;
				}
			}
		}
	}

	public void act() {

		if (!WorldData.PAUZA) {
			atac();
			move();
			setImage(gif.getCurrentImage());
			time++;
			if (time > constantEraseTime) {
				((HostileNpc) stroke).setUsedItem(false);
				getWorld().removeObject(this);

			}

		}

	}
}
