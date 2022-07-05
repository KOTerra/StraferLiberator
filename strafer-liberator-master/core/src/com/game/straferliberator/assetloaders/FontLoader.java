package com.game.straferliberator.assetloaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;

import greenfoot.Font;
import greenfoot.GreenfootSound;

public class FontLoader extends AsynchronousAssetLoader<Font, FontLoader.FontLoaderParameters> {

	BitmapFont bfont;
	Font font;

	public FontLoader(FileHandleResolver resolver) {
		super(resolver);
	}

	@Override
	public void loadAsync(AssetManager manager, String fileName, FileHandle file, FontLoaderParameters parameter) {

	}

	@Override
	public Font loadSync(AssetManager manager, String fileName, FileHandle file, FontLoaderParameters parameter) {
		return new Font(fileName,parameter.bold,parameter.italic,parameter.size,true);
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, FontLoaderParameters parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	static public class FontLoaderParameters extends AssetLoaderParameters<Font> {
		public int size;
		public boolean italic;
		public boolean bold;

		public FontLoaderParameters(boolean bold, boolean italic, int size) {
			this.size = size;
			this.italic = italic;
			this.bold = bold;
		}
	}
}