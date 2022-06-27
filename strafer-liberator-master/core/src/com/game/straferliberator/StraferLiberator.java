package com.game.straferliberator;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.straferliberator.screen.GameScreen;
import com.port.world.WorldData;

public class StraferLiberator extends Game {
	// used by all screens
	public static SpriteBatch batch;
	public static OrthographicCamera camera;
	
	public void create () {
		batch = new SpriteBatch();
		camera=new OrthographicCamera(WorldData.WIDTH,WorldData.HEIGHT);
		setScreen(new GameScreen(this));
	}
	
	public void render() {
		super.render();
	}
}
