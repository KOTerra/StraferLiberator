package com.game.straferliberator.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MainMenu implements Screen{

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;

	
	@Override
	public void show() {
		TmxMapLoader loader= new TmxMapLoader();
		map=loader.load("assets/data/map11.tmx");
	}

	@Override
	public void render(float delta) {
		
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
		
	}

}
