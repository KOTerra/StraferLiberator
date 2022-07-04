package com.game.straferliberator.assetloaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

import greenfoot.Font;
import greenfoot.GreenfootSound;

public class FontLoader extends AsynchronousAssetLoader<Font, FontLoader.FontLoaderParameters> {

	
	
	public FontLoader(FileHandleResolver resolver) {
		super(resolver);
		// TODO Auto-generated constructor stub
	}

	static public class FontLoaderParameters extends AssetLoaderParameters<Font> {

	}

	@Override
	public void loadAsync(AssetManager manager, String fileName, FileHandle file, FontLoaderParameters parameter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Font loadSync(AssetManager manager, String fileName, FileHandle file, FontLoaderParameters parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, FontLoaderParameters parameter) {
		// TODO Auto-generated method stub
		return null;
	}
}