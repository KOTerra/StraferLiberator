package com.game.straferliberator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.AbsoluteFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.straferliberator.screen.GameScreen;
import com.game.straferliberator.screen.LoadingScreen;
import com.port.utils.graphics.GifDecoder;
import com.port.world.WorldData;

import greenfoot.Font;

/**
 * Game class-ul
 */
public class StraferLiberator extends Game {

	public static SpriteBatch batch;
	public static OrthographicCamera camera;
	public static AssetManager assetManager;
	public static Font cFont;
	

	
	public void create () {
		batch = new SpriteBatch();
		
		camera=new OrthographicCamera(WorldData.WIDTH,WorldData.HEIGHT);
		
		assetManager=new AssetManager(new InternalFileHandleResolver());
				
		setScreen(new LoadingScreen(this));
	}
	
	public void render() {
		super.render();
	}
	
	public void dispose() {
		batch.dispose();
		assetManager.dispose();
		getScreen().dispose();
		
	}
}
