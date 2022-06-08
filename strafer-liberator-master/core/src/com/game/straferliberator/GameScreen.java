package com.game.straferliberator;
import java.util.Scanner;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.game.straferliberator.World.WorldListener;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.TestActor;

public class GameScreen extends ScreenAdapter {

	StraferLiberator game;

	OrthographicCamera camera;
	Vector3 touchPoint;
	greenfoot.TestWorld world;
	WorldRenderer worldRederer;


	GlyphLayout glyphLayout = new GlyphLayout();

	public GameScreen(StraferLiberator game) {
		this.game = game;

		//world = new com.port.PlayWorld();
		 world = new greenfoot.TestWorld();
		//
		Gdx.input.setInputProcessor(world);
		camera = new OrthographicCamera(1024, 576);
		touchPoint = new Vector3();

		
		worldRederer = new WorldRenderer(StraferLiberator.batcher, world);
	}

	public void update(float deltaTime) {
		
		world.act();
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