
package com.game.straferliberator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StraferLiberator extends Game {
	// used by all screens
	public SpriteBatch batcher;
	
	public void create () {
		batcher = new SpriteBatch();
		Settings.load();
		Assets.load();
		setScreen(new MainMenuScreen(this));
	}
	
	public void render() {
		super.render();
	}
}
