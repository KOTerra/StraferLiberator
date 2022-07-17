package com.port.entity.item.player;

import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.ArrayList;
import java.util.HashMap;

import com.game.straferliberator.StraferLiberator;
import com.port.entity.item.Item;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.GifImage;
import com.port.world.WorldData;

public class Sword extends Item {

	public static final int damage = 25;
	public static final int mass = 2;

	/**
	 * HashMap containing all the GIFs for each direction
	 */
	HashMap<String, GifImage> directie = new HashMap<String, GifImage>();
	GifImage sabieImg = directie.get(Item.itemGif);
	/**
	 * Time variable for each frame
	 */
	private long time = 0;
	/**
	 * The erase time for each "Sabie" instance
	 */
	private short constantEraseTime = (short) (WorldData.FPS/2);
	Actor player;

	public Sword(Player player) {
		this.player = player;
		player.setSpeed(0);
		directie.put("D", StraferLiberator.assetManager.get("images/item/sabie_a_D.gif", GifImage.class));
		directie.put("W", StraferLiberator.assetManager.get("images/item/sabie_a_W.gif", GifImage.class));
		directie.put("A", StraferLiberator.assetManager.get("images/item/sabie_a_A.gif", GifImage.class));
		directie.put("S", StraferLiberator.assetManager.get("images/item/sabie_a_S.gif", GifImage.class));

		sabieImg = directie.get("D");
		this.time = 0;
		Greenfoot.playSound("sounds/sabie.mp3");

	}

	public Actor getPlayer() {
		return player;
	}

	protected void move() {
		super.move();

		sabieImg = directie.get(Item.itemGif);
	}

	protected void atac() {
		sabieImg = directie.get(Item.itemGif);
	}

	public void act() {

		if (!WorldData.PAUZA) {
			atac();
			move();

			time++;
			if (time > constantEraseTime) {
				getWorld().addObject(new SabieHold(),0,0);
				getWorld().removeObject(this);
			}
			setImage(sabieImg.getCurrentImage());
		}

	}
}
