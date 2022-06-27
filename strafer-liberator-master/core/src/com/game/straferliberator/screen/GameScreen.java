package com.game.straferliberator.screen;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;
import com.game.straferliberator.StraferLiberator;
import com.game.straferliberator.render.WorldRenderer;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

import greenfoot.test.TestWorld;

public class GameScreen extends ScreenAdapter {

	StraferLiberator game;

	OrthographicCamera camera;
	Vector3 touchPoint;
	greenfoot.World world;


	WorldRenderer<Object> worldRederer;


	GlyphLayout glyphLayout = new GlyphLayout();

	public GameScreen(StraferLiberator straferLiberator) {
		this.game = straferLiberator;

		
		//
		//
		if(Gdx.app.getType().equals(Application.ApplicationType.Android)) {
			//world = new TestWorld();
			world=new PlayWorld();
		}
		else {
			world=new PlayWorld();
			//world = new TestWorld();
		}
	

		Gdx.input.setInputProcessor(world);
		
		camera = new OrthographicCamera(WorldData.WIDTH, WorldData.HEIGHT);
		touchPoint = new Vector3();

		
		worldRederer = new WorldRenderer<Object>(StraferLiberator.batcher, world);
	}

	public void update(float deltaTime) {
	
		world.act();

		//System.out.println(Gdx.graphics.getFramesPerSecond());
	}

	public void draw() {
		GL20 gl = Gdx.gl;
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		StraferLiberator.batcher.setProjectionMatrix(camera.combined);
		StraferLiberator.batcher.enableBlending();
		

		worldRederer.render();
		
	}


	@Override
	public void render(float delta) {
		update(delta);
		draw();
		
	}
}