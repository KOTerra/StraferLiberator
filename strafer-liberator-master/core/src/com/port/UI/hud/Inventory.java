package com.port.UI.hud;

import greenfoot.*;

import java.util.*;

import com.port.UI.menu.Menu;
import com.port.entity.mover.player.Player;
import com.port.world.WorldData;

import java.awt.event.*;

public class Inventory extends Menu {

	GreenfootImage wheel0 = new GreenfootImage("UI/hud/inventory.png");
	GreenfootImage wheel1 = new GreenfootImage("UI/hud/itemWheelSelect.png");

	public static int nrItem = 5; // 1 sword
	// 2 icelock
	// 3 blackhole
	// 4 lantern
	// 5 laser
	// 6 portal gun
	public int raza = 275 / 2;
	int x = WorldData.WIDTH - 149;
	int y = WorldData.HEIGHT - 146;

	protected Player player;

	public Inventory(Player player) {
		this.player = player;
		prepareData();
		setImage(wheel0);

		// player.setToggledInventory(true);
	}

	private void prepareData() {
		WorldData.PAUZA = true;
		player.setEquipSword(false);
		player.setEquipLaser(false);
		player.setEquipPortalGun(false);
		player.setEquipIceLock(false);
		player.setEquipLantern(false);
		player.setEquipBlackHole(false);
	}

	public String getItemSelected() {
		switch (this.nrItem) {
		case 1: {
			return "sword";
		}
		case 2: {
			return "icelock";
		}
		case 3: {
			return "blackhole";
		}
		case 4: {
			return "lantern";
		}
		case 5: {
			return "laser";
		}
		case 6: {
			return "portalgun";
		}
		}
		return "";
	}

	public void checkMouse() {

		MouseInfo mouseul = Greenfoot.getMouseInfo();
		if (mouseul != null) {

			if (Math.sqrt(
					(Greenfoot.getMouseInfo().getX() - this.getX()) * (Greenfoot.getMouseInfo().getX() - this.getX())
							+ (Greenfoot.getMouseInfo().getY() - this.getY())
									* (Greenfoot.getMouseInfo().getY() - this.getY())) <= raza) {

				setImage(wheel1);// cand sunt pe buton

				double delta_x = Greenfoot.getMouseInfo().getX() - x;
				double delta_y = Greenfoot.getMouseInfo().getY() - y;
				double grade = Math.toDegrees(Math.atan2(delta_y, delta_x));

				if (grade >= -90 && grade < -30) {
					nrItem = 1;
					this.setRotation(0);

				}
				if (grade >= -30 && grade < 30) {
					nrItem = 2;
					this.setRotation(60);

				}
				if (grade >= 30 && grade < 90) {
					nrItem = 3;
					this.setRotation(120);

				}
				if (grade >= 90 && grade < 150) {
					nrItem = 4;
					this.setRotation(180);

				}
				if (grade >= 150 && grade < 240) {
					nrItem = 5;
					this.setRotation(240);

				}
				if (grade >= -180 && grade < -150) {
					nrItem = 5;
					this.setRotation(240);
				}
				if (grade >= -150 && grade < -90) {
					nrItem = 6;
					this.setRotation(300);

				}

			}

			else {
				setImage(wheel0);// altfel

				setRotation(0);
			}
		} else {
			this.setRotation(0);
			setImage(wheel0);

		}

	}

	public void act() {
		checkMouse();
		this.setLocation(x, y);
		if (!player.isToggledInventory()) {
			WorldData.PAUZA = false;
			getWorld().removeObject(this);
		}

	}
}
