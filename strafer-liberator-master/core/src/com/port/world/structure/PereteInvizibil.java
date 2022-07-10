package com.port.world.structure;

import greenfoot.*;

import java.util.ArrayList;
import java.util.HashMap;

import com.port.entity.mover.player.Player;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

import java.lang.Math;

public class PereteInvizibil extends Perete {

	private String marime;
	private String pozitie;
	Player player;
	GreenfootImage pereteImg = super.micImg;
	boolean added=false;
	int floor;

	public PereteInvizibil(String pozitie, int floor, String marime) {

		this.floor = floor;
		this.marime = marime;

		switch (marime) {
		case "mic90": {
			pereteImg = super.mic90Img;
			break;
		}
		case "mic": {
			pereteImg = super.micImg;
			break;
		}
		}

		setImage(pereteImg);
		this.pozitie = pozitie;

	}

	void chestie2() {
		
		if (intersects(player)) {
			if (player != null) {
				switch (pozitie) {
				case "W": {
					player.setLocation(player.getX(), player.getY() + player.getSpeed());
					break;
				}
				case "A": {
					player.setLocation(player.getX() + player.getSpeed(), player.getY());
					break;
				}
				case "S": {
					player.setLocation(player.getX(), player.getY() - player.getSpeed());
					break;
				}
				case "D": {
					player.setLocation(player.getX() - player.getSpeed(), player.getY());
					break;
				}

				}
			}
		}
	}

	public void act() {

		if(!added) {
			player = (Player) (((PlayWorld) getWorld()).getPlayer());
			added=true;
		}
		if (isInScreenRange() && !WorldData.PAUZA) {
			// chestie();
			chestie2();
		}

	}

	public String getPozitie() {
		return pozitie;
	}

}
