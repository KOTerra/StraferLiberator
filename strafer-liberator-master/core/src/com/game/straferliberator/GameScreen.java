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

public class GameScreen extends ScreenAdapter {

	static final int GAME_RUNNING = 1;

	StraferLiberator game;

	int state;
	OrthographicCamera guiCam;
	Vector3 touchPoint;
	greenfoot.World world;
	WorldListener worldListener;
	WorldRenderer renderer;
	Rectangle pauseBounds;
	Rectangle resumeBounds;
	Rectangle quitBounds;
	int lastScore;
	String scoreString;

	GlyphLayout glyphLayout = new GlyphLayout();

	public GameScreen(StraferLiberator game) {
		this.game = game;

		state = GAME_RUNNING;
		guiCam = new OrthographicCamera(320, 480);
		guiCam.position.set(320 / 2, 480 / 2, 0);
		touchPoint = new Vector3();
		worldListener = new WorldListener() {
			@Override
			public void jump() {
				Assets.playSound(Assets.jumpSound);
			}

			@Override
			public void highJump() {
				Assets.playSound(Assets.highJumpSound);
			}

			@Override
			public void hit() {
				Assets.playSound(Assets.hitSound);
			}

			@Override
			public void coin() {
				Assets.playSound(Assets.coinSound);
			}
		};
		world = new greenfoot.World(1024,576,1,false);
		renderer = new WorldRenderer(game.batcher, world);
		pauseBounds = new Rectangle(320 - 64, 480 - 64, 64, 64);
		resumeBounds = new Rectangle(160 - 96, 240, 192, 36);
		quitBounds = new Rectangle(160 - 96, 240 - 36, 192, 36);
		lastScore = 0;
		scoreString = "SCORE: 0";
	}

	public void update(float deltaTime) {
		if (deltaTime > 0.1f)
			deltaTime = 0.1f;

		switch (state) {

		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;

		}
	}

	private void updateReady() {
		if (Gdx.input.justTouched()) {
			state = GAME_RUNNING;
		}
	}

	private void updateRunning(float deltaTime) {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (pauseBounds.contains(touchPoint.x, touchPoint.y)) {
				// Assets.playSound(Assets.clickSound);
				// state = GAME_PAUSED;
				return;
			}
		}

	/*	float accel = 0;
		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT))
			accel = 5f;
		if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT))
			accel = -5f;
		world.update(deltaTime, accel);
*/
	
		
	}

	

	public void draw() {
		GL20 gl = Gdx.gl;
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.render();

		guiCam.update();
		game.batcher.setProjectionMatrix(guiCam.combined);
		game.batcher.enableBlending();
		game.batcher.begin();
		switch (state) {

		case GAME_RUNNING:
			presentRunning();
			break;

		}
		game.batcher.end();
	}

	private void presentRunning() {
		game.batcher.draw(Assets.pause, 320 - 64, 480 - 64, 64, 64);
		Assets.font.draw(game.batcher, scoreString, 16, 480 - 20);
	}

	

	@Override
	public void render(float delta) {
		update(delta);
		draw();
	}

	@Override
	public void pause() {
		//if (state == GAME_RUNNING)
		//	state = GAME_PAUSED;
	}
}