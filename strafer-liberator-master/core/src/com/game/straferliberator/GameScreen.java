/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

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
		 world.setBackground(new GreenfootImage("Capture.png"));

		 world.addObject(new TestActor(), 100, 200);
		 //
		Gdx.input.setInputProcessor(world);
		camera = new OrthographicCamera(1024, 576);
		touchPoint = new Vector3();

		

		worldRederer = new WorldRenderer(StraferLiberator.batcher, world);
		
	}

	public void update(float deltaTime) {
	
		world.act();
		//toate obiectele act
		if(Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
			
			world.setBackground(new GreenfootImage("C.jpg"));
			System.out.println(world.nrActori());
		}
		 
		
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