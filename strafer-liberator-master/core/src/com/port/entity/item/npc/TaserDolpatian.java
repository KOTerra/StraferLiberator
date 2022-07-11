package com.port.entity.item.npc;

import greenfoot.*;

import java.util.HashMap;

import com.game.straferliberator.StraferLiberator;
import com.port.entity.item.Item;
import com.port.entity.mover.npc.hostile.Dolpatian;
import com.port.entity.mover.npc.hostile.HostileNpc;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.GifImage;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

public class TaserDolpatian extends NpcItem {

	public static final int damage = 15;
	public static final int mass = 1;

	HashMap<String, GifImage> directie = new HashMap<String, GifImage>();
	GifImage itemImg = directie.get(Item.itemGif);

	private long time = 0;

	Actor dolpatian;

	private final short constantEraseTime = (short) (WorldData.FPS/2);
	boolean gaveDamage = false;

	public TaserDolpatian(Dolpatian dolpatian) {
		this.dolpatian = dolpatian;
		directie.put("D",
				StraferLiberator.assetManager.get("images/npc/inamic/dolpatian/taserDolpatian_D.gif", GifImage.class));
		directie.put("W",
				StraferLiberator.assetManager.get("images/npc/inamic/dolpatian/taserDolpatian_W.gif", GifImage.class));
		directie.put("A",
				StraferLiberator.assetManager.get("images/npc/inamic/dolpatian/taserDolpatian_A.gif", GifImage.class));
		directie.put("S",
				StraferLiberator.assetManager.get("images/npc/inamic/dolpatian/taserDolpatian_S.gif", GifImage.class));

		itemImg = directie.get("D");
		setImage(itemImg.getCurrentImage());
		this.time = 0;

	}

	public Actor getGoblin() {
		return this.dolpatian;
	}

	protected void move() {
		setLocation(dolpatian.getX(), dolpatian.getY());

		itemImg = directie.get(((HostileNpc) dolpatian).getGifItem());
	}

	protected void atac() {
		itemImg = directie.get(((HostileNpc) dolpatian).getGifItem());
		Player player = ((PlayWorld) getWorld()).getPlayer();
		if (intersects(player)) {

			player.knockbacked = true;
			player.knockback(0.1, dolpatian, this.mass, 80);
			if (!gaveDamage) {
				player.takeDamage(this.damage);
				gaveDamage = true;
			}

		}
	}

	public void act() {

		if (!WorldData.PAUZA) {
			try {
				setImage(itemImg.getCurrentImage());
				atac();
				move();

				time++;
				if (time > constantEraseTime) {
					((HostileNpc) dolpatian).setUsedItem(false);
					getWorld().removeObject(this);

				}
			} catch (Exception e) {
			}

		}

	}
}
