package com.port.UI.buton.touch;

import com.badlogic.gdx.Gdx;
import com.game.straferliberator.StraferLiberator;
import com.port.world.WorldData;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class GenericTouchControl extends Actor {

	GreenfootImage icon;
	GreenfootImage iconTouched;
	int initialx, initialy;

	public GenericTouchControl(int inx, int iny, String img) {
		this.initialx = inx;
		this.initialy = iny;
		setIcon(img);
	}

	protected boolean isTouched() {

		float touchPointerX = Gdx.input.getX();
		float touchPointerY = Gdx.input.getY();

		if (this.getX() - this.iw() / 2 <= touchPointerX && this.getX() + this.iw() / 2 >= touchPointerX
				&& this.getY() - this.ih() / 2 <= touchPointerY && this.getY() + this.ih() / 2 >= touchPointerY) {
			if (Gdx.input.justTouched()) {
				setImage(iconTouched);
				return true;
			}
		}
		setImage(icon);
		return false;
	}

	protected void setIcon(String img) {
		icon = StraferLiberator.assetManager.get("images/UI/touch/" + img + ".png", GreenfootImage.class);
		iconTouched = StraferLiberator.assetManager.get("images/UI/touch/" + img + "1.png", GreenfootImage.class);
	}

	public void act() {
		if (!WorldData.PAUZA) {
			setLocation(initialx, initialy);
		} else {
			setLocation(-200, -200);
		}
	}
}
