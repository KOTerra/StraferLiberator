package com.port.UI.hud;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.port.entity.mover.player.Player;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class StaminaBarPlayer extends HealthBar {
	private int maxValue;
	private Color color;
	private int barWidth;
	private int barHeight;
	private int barWidthMax;
	GreenfootImage barImg;

	int value;

	public StaminaBarPlayer(int initValue, int maxValue) {

		this.value = initValue;
		this.maxValue = maxValue;
		this.color = Color.GOLDENROD;

		barWidth = 69;
		barWidthMax = barWidth;
		barHeight = 9;

		barImg = new GreenfootImage(barWidth, barHeight);
		barImg.setColor(color);
		barImg.fill();
	}

	public void update() {
		Player player = ((PlayWorld) getWorld()).getPlayer();
		if (Gdx.app.getType().equals(ApplicationType.Android)) {
			if (player.getPlayWorld().getTouchManager().getSprintButton().isTouchedMultipleFingers()) {
				decrease();
				if (player.getStamina() > 10) {
					player.setCanSprint(true);
				} else {
					player.setCanSprint(false);
				}
			} else {
				player.setCanSprint(false);
				restore();
			}
		}
		if (Greenfoot.isKeyDown("L-Shift")) {
			decrease();
			if (player.getStamina() > 10) {
				player.setCanSprint(true);
			} else {
				player.setCanSprint(false);
			}
		} else {
			player.setCanSprint(false);
			restore();
		}
	}

	private void decrease() {
		Player player = ((PlayWorld) getWorld()).getPlayer();

		if (((int) WorldData.elapsed * WorldData.FPS) % 2 == 0) {
			this.subtract(4);
			player.setStamina(player.getStamina() - 4);
		}

	}

	private void restore() {
		Player player = ((PlayWorld) getWorld()).getPlayer();
		if (((int) WorldData.elapsed * WorldData.FPS) % 2 == 0) {
			this.add(2);
			player.setStamina(player.getStamina() + 2);
		}
		if (this.getValue() >= player.getStaminaMax()) {
			player.setStamina(player.getStaminaMax());
		}
	}

	public void act() {
		super.act();
	}

	private void makeBarImg() {
		barWidth = (int) ((barWidthMax / 100f) * value);
		barImg.scale(barWidth, barHeight);
		this.setImage(barImg);
	}

	public void subtract(int v) {
		value -= v;
		checkValue();
		makeBarImg();
	}

	public void add(int v) {
		value += v;
		checkValue();
		makeBarImg();
	}

	private void checkValue() {
		if (value < 0) {
			value = 0;
		}
		if (value > maxValue) {
			value = maxValue;
		}
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		if (this.value != value) {
			this.value = value;
			checkValue();
		}
	}

}
