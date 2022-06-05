
package com.game.straferliberator;

import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import greenfoot.Actor;

public class WorldRenderer<A> {
	static final float WIDTH = 1024;
	static final float HEIGHT = 576;
	greenfoot.World world;
	OrthographicCamera cam;
	SpriteBatch batch;

	public WorldRenderer(SpriteBatch batch, greenfoot.World world) {
		this.world = world;
		this.cam = new OrthographicCamera(WIDTH, HEIGHT);
		this.cam.position.set(WIDTH / 2, HEIGHT / 2, 0);
		this.batch = batch;
	}

	public void render() {
		//if (world.bob.position.y > cam.position.y) {
		//cam.position.y = world.bob.position.y;
			//cam.position.x=world.bob.position.x;
		//}
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		renderBackground();
		renderObjects();
	}

	public void renderBackground() {
		batch.disableBlending();
		batch.begin();
		batch.draw(world.getBackground(), cam.position.x - WIDTH / 2, cam.position.y - HEIGHT / 2,
				WIDTH, HEIGHT);
		
		batch.end();
	}

	public void renderObjects() {
		batch.enableBlending();
		batch.begin();
		
		Array<com.badlogic.gdx.scenes.scene2d.Actor> l=world.getActors();
		for(com.badlogic.gdx.scenes.scene2d.Actor a:l) {
			
			((greenfoot.Actor) a).draw();
										
			
		}
		
		//aici veneau totate fct comentate de render
		batch.end();
	}

		
		
		
	/*private void renderBob() {
		TextureRegion keyFrame;
		switch (world.bob.state) {
		case Bob.BOB_STATE_FALL:
			keyFrame = Assets.bobFall.getKeyFrame(world.bob.stateTime, Animation.ANIMATION_LOOPING);
			break;
		case Bob.BOB_STATE_JUMP:
			keyFrame = Assets.bobJump.getKeyFrame(world.bob.stateTime, Animation.ANIMATION_LOOPING);
			break;
		case Bob.BOB_STATE_HIT:
		default:
			keyFrame = Assets.bobHit;
		}

		float side = world.bob.velocity.x < 0 ? -1 : 1;
		if (side < 0)
			batch.draw(keyFrame, world.bob.position.x + 0.5f, world.bob.position.y - 0.5f, side * 1, 1);
		else
			batch.draw(keyFrame, world.bob.position.x - 0.5f, world.bob.position.y - 0.5f, side * 1, 1);
	}*/

/*	private void renderPlatforms() {
		int len = world.platforms.size();
		for (int i = 0; i < len; i++) {
			Platform platform = world.platforms.get(i);
			TextureRegion keyFrame = Assets.platform;
			if (platform.state == Platform.PLATFORM_STATE_PULVERIZING) {
				keyFrame = Assets.brakingPlatform.getKeyFrame(platform.stateTime, Animation.ANIMATION_NONLOOPING);
			}

			batch.draw(keyFrame, platform.position.x - 1, platform.position.y - 0.25f, 2, 0.5f);
		}
	}*/

	/*private void renderItems() {
		int len = world.springs.size();
		for (int i = 0; i < len; i++) {
			Spring spring = world.springs.get(i);
			batch.draw(Assets.spring, spring.position.x - 0.5f, spring.position.y - 0.5f, 1, 1);
		}

		len = world.coins.size();
		for (int i = 0; i < len; i++) {
			Coin coin = world.coins.get(i);
			TextureRegion keyFrame = Assets.coinAnim.getKeyFrame(coin.stateTime, Animation.ANIMATION_LOOPING);
			batch.draw(keyFrame, coin.position.x - 0.5f, coin.position.y - 0.5f, 1, 1);
		}
	}*/

	/*private void renderSquirrels() {
		int len = world.squirrels.size();
		for (int i = 0; i < len; i++) {
			Squirrel squirrel = world.squirrels.get(i);
			TextureRegion keyFrame = Assets.squirrelFly.getKeyFrame(squirrel.stateTime, Animation.ANIMATION_LOOPING);
			float side = squirrel.velocity.x < 0 ? -1 : 1;
			if (side < 0)
				batch.draw(keyFrame, squirrel.position.x + 0.5f, squirrel.position.y - 0.5f, side * 1, 1);
			else
				batch.draw(keyFrame, squirrel.position.x - 0.5f, squirrel.position.y - 0.5f, side * 1, 1);
		}
	}*/

	/*private void renderCastle() {
		Castle castle = world.castle;
		batch.draw(Assets.castle, castle.position.x - 1, castle.position.y - 1, 2, 2);
	}*/
}
