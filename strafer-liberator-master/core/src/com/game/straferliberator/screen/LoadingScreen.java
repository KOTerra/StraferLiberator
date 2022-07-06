package com.game.straferliberator.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.port.utils.graphics.GifImage;
import com.port.world.WorldData;
import com.game.straferliberator.StraferLiberator;

import com.game.straferliberator.assetloaders.*;
import com.game.straferliberator.assetloaders.FontLoader.FontLoaderParameters;
import com.game.straferliberator.assetloaders.GreenfootImageLoader.GreenfootImageLoaderParameters;

import greenfoot.*;

public class LoadingScreen implements Screen {

	private final StraferLiberator game;

	private ShapeRenderer shapeRenderer;

	private float progress;

	public LoadingScreen(final StraferLiberator game) {
		this.game = game;
		shapeRenderer = new ShapeRenderer();

		queueAssetsToLoad();
	}

	@Override
	public void show() {
	}

	private void update(float delta) {

		progress = MathUtils.lerp(progress, game.assetManager.getProgress(), .1f);

		if (game.assetManager.update()) {
			if (progress >= game.assetManager.getProgress() - .001f) {
				//System.out.println("gata");
				 game.setScreen(new GameScreen(game));
			}
		}

		//System.out.println("loader");
	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 0);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		update(delta);

		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.valueOf("#40444b"));
		shapeRenderer.rect(0, WorldData.HEIGHT / 2 - 435 / 2f, WorldData.WIDTH * progress, 435);
		shapeRenderer.end();

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() { 
	}

	@Override
	public void dispose() {
		shapeRenderer.dispose();

	}

	private void queueAssetsToLoad() {
		//test
		game.assetManager.load("images/UI/hud/healthBar.png", Texture.class);
		
		game.assetManager.load("images/logo.png", Texture.class);
		//test
		
		
		game.assetManager.setLoader(Font.class, new FontLoader(new InternalFileHandleResolver()));
		//dam load la fonturi
		game.assetManager.load("fonts/consolas.fnt",Font.class, new FontLoaderParameters(false,false,24));
		game.assetManager.load("fonts/edo.fnt",Font.class, new FontLoaderParameters(false,false,10));

		
		
		game.assetManager.setLoader(GreenfootSound.class, new GreenfootSoundLoader(new InternalFileHandleResolver()));
		//dam load la sunete
		
		
		
		game.assetManager.setLoader(GreenfootImage.class, new GreenfootImageLoader(new InternalFileHandleResolver()));
		//dam load la imageuri
		game.assetManager.load("images/UI/menu/mainMenu/logo.png",GreenfootImage.class);
		
		
		
		game.assetManager.setLoader(GifImage.class,new GifImageLoader(new InternalFileHandleResolver()));
		//dam load la Animationuri pe care le incarcam in gifuri
		game.assetManager.load("images/UI/menu/mainMenu/blur.gif",GifImage.class);
		game.assetManager.load("images/UI/menu/mainMenu/playerAnimation.gif",GifImage.class);
		
	}

}
