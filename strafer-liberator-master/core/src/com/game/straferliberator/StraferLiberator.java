
package com.game.straferliberator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.straferliberator.screens.MainMenu;

public class StraferLiberator extends Game {
	
	private static final String log="StreferLib version 1.0";
	
	@Override
	public void create () {
		//batcher = new SpriteBatch();
		//Settings.load();
		//Assets.load();
		setScreen(new MainMenu());
		Gdx.app.log(log, "create()");
	}
	
	@Override
	public void dispose () {
		Gdx.app.log(log, "dispose()");
		super.dispose();
	}

	@Override
	public void pause () {
		Gdx.app.log(log, "pause()");
		super.pause();
	}

	@Override
	public void resume () {
		Gdx.app.log(log, "resume()");
		super.resume();
	}

	@Override
	public void render () {
		//Gdx.app.log(log, "render()");
		super.render();
	}

	@Override
	public void resize (int width, int height) {
		Gdx.app.log(log, "resize()");
		super.resize(width, height);
	}
}
