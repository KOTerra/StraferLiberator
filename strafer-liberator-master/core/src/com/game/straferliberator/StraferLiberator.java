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

public class StraferLiberator extends Game {

	public static SpriteBatch batch;
	public static OrthographicCamera camera;
	public static AssetManager assetManager;
	public static GifDecoder gifDecoder;
	public static Font cFont;
	public static Font getcFont() {
		return cFont;
	}

	public static Font eFont;
	
	public void create () {
		batch = new SpriteBatch();
		
		camera=new OrthographicCamera(WorldData.WIDTH,WorldData.HEIGHT);
		
		assetManager=new AssetManager(new InternalFileHandleResolver());
		
		gifDecoder=new GifDecoder();
		
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
