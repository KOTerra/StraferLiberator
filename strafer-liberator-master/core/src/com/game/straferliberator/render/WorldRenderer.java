package com.game.straferliberator.render;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class WorldRenderer<A> {
	static final float WIDTH = com.port.world.WorldData.WIDTH;
	static final float HEIGHT =com.port.world.WorldData.HEIGHT;
	greenfoot.World world;
	OrthographicCamera cam;
	SpriteBatch batch;

	public WorldRenderer(SpriteBatch batch, greenfoot.World world) {
		this.world =  world;
		this.cam = new OrthographicCamera(WIDTH, HEIGHT);
		this.cam.position.set(WIDTH / 2, HEIGHT / 2, 0);
		this.batch = batch;
	}

	public void render() {
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
		
		
	
		batch.end();
	}

}
