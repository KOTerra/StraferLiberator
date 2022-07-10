package com.port.entity.item;

import greenfoot.*;

import java.util.List;

import com.port.entity.mover.player.Player;
import com.port.world.PlayWorld;

public class Item extends Actor {

	@Override
	public String toString() {
		// the name of the class
		return this.getClass().getSimpleName();
	}

	public static String itemGif = "D";// the state of the item

	protected void move() {
		Player playerr = ((PlayWorld) getWorld()).getPlayer();
		Actor player = (Actor) playerr;
		setLocation(player.getX(), player.getY() + 15);

	}

}
