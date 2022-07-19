package com.port.utils.graphics;

import com.badlogic.gdx.utils.Array;
import com.game.straferliberator.StraferLiberator;
import com.port.UI.menu.Menu;
import com.port.world.WorldData;

import greenfoot.*;

/*
 * clasa ajutatoare 
 * animatie cu scop estetic ce nu e legata de un actor anume
 */
public class Transition extends Actor {

	private Animation animation;
	private AnimationRunner animationRunner;

	public Transition(String name, int nrf, int cc) {

		animation = StraferLiberator.assetManager.get(name, Animation.class);

		animation.setCycleActs(nrf);
		animation.setCycleCount(cc);
		animation.setScalar(1);
		animationRunner = new AnimationRunner(this, animation);
		animationRunner.run();
		animationRunner.setActiveState(true);
	}

	public void act() {

		if (!WorldData.PAUZA) {
			setLocation(WorldData.WIDTH / 2, WorldData.HEIGHT / 2);
			animationRunner.run();
			if (!animationRunner.isActive()) {
				getWorld().removeObject(this);
			}

		}
	}
}
