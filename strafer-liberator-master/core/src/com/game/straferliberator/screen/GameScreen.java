package com.game.straferliberator.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.straferliberator.StraferLiberator;
import com.game.straferliberator.render.WorldRenderer;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

import greenfoot.Font;
import greenfoot.test.TestWorld;

/**
 * ecranul principal in care ruleaza jocul
 * 
 * @author mihai_stoica
 *
 */
public class GameScreen extends ScreenAdapter {

	StraferLiberator game;

	greenfoot.World world;

	WorldRenderer<Object> worldRederer;

	public GameScreen(StraferLiberator straferLiberator) {
		this.game = straferLiberator;
		game.cFont = game.assetManager.get("fonts/consolas.fnt", Font.class);

		if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
			// world = new TestWorld();
			world = new PlayWorld();
		} else {
			world = new PlayWorld();
			// world = new TestWorld();
		}

		worldRederer = new WorldRenderer<Object>(world);
		Gdx.input.setInputProcessor(world);

	}

	/**
	 * apeleaaza act si schimba variabilele de timp
	 */
	public void update(float deltaTime) {

		world.act();
		timeUpdate();
	}

	/**
	 * foloseste worldRenderer ul pentru
	 */
	public void draw() {
		GL20 gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 0);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		worldRederer.render();

	}

	@Override
	public void render(float delta) {
		update(delta);
		draw();

	}

	public void dispose() {
	}

	private void timeUpdate() {
		WorldData.FPS = Gdx.graphics.getFramesPerSecond();
		WorldData.elapsed += Gdx.graphics.getDeltaTime();
		if (WorldData.elapsed >= 10000) {
			WorldData.elapsed = 0;
		}
	}

}