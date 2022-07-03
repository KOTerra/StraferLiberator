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

import greenfoot.test.TestWorld;

public class GameScreen extends ScreenAdapter {

	StraferLiberator game;

	Vector3 touchPoint;
	greenfoot.World world;

	WorldRenderer<Object> worldRederer;


	public GameScreen(StraferLiberator straferLiberator) {
		this.game = straferLiberator;

		if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
			world = new TestWorld();
			// world=new PlayWorld();
		} else {
			world = new PlayWorld();
			// world = new TestWorld();
		}

		worldRederer = new WorldRenderer<Object>(world);
		Gdx.input.setInputProcessor(world);

		touchPoint = new Vector3();

	}

	public void update(float deltaTime) {

		world.act();
		WorldData.FPS=Gdx.graphics.getFramesPerSecond();

	}

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
	

}