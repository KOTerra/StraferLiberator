package com.game.straferliberator.render;

import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.game.straferliberator.StraferLiberator;

import greenfoot.Actor;

public class WorldRenderer<A> {
	static final float WIDTH = com.port.world.WorldData.WIDTH;
	static final float HEIGHT = com.port.world.WorldData.HEIGHT;
	greenfoot.World world;

	SpriteBatch batch;
	OrthographicCamera camera;

	public WorldRenderer(greenfoot.World world) {
		this.world = world;
		this.batch = StraferLiberator.batch;
		this.camera = StraferLiberator.camera;
		camera.position.set(WIDTH / 2, HEIGHT / 2, 0);
	}

	public void render() {
		batch.setProjectionMatrix(camera.combined);
		camera.update();

		renderBackground();
		renderObjects();
	}

	public void renderBackground() {
		batch.disableBlending();
		batch.begin();
		batch.draw(world.getBackground(), camera.position.x - WIDTH / 2, camera.position.y - HEIGHT / 2, WIDTH, HEIGHT);
		batch.end();
	}

	public void renderObjects() {
		batch.enableBlending();
		batch.begin();
		//Array<com.badlogic.gdx.scenes.scene2d.Actor> l = world.getActors();
		//for (com.badlogic.gdx.scenes.scene2d.Actor a : l) {

		//	((greenfoot.Actor) a).draw();

		//}

		 for(greenfoot.Actor a:PaintUtilities.getActorsToRenderInOrder()) {
				 a.draw();
		 }

		batch.end();
	}

}
