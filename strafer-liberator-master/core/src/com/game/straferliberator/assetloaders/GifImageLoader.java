package com.game.straferliberator.assetloaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.game.straferliberator.assetloaders.GreenfootImageLoader.GreenfootImageLoaderParameters;
import com.port.utils.graphics.GifDecoder;
import com.port.utils.graphics.GifImage;

import greenfoot.GreenfootImage;
public class GifImageLoader extends AsynchronousAssetLoader<GifImage, GifImageLoader.GifImageLoaderParameter> {

    private Animation<TextureRegion> animation;

    public GifImageLoader(FileHandleResolver resolver) {

        super(resolver);

        this.animation = null;
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, GifImageLoaderParameter parameter) {


    }

    @Override
    public GifImage loadSync(AssetManager manager, String fileName, FileHandle file, GifImageLoaderParameter parameter) {

        PlayMode playMode = PlayMode.LOOP;

        if ( parameter != null ){
            playMode = parameter.playMode;
        }

        animation = (GifDecoder.loadGIFAnimation( playMode, file.read() ));

        return new GifImage(animation );
    }

    @SuppressWarnings( "rawtypes" )
    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, GifImageLoaderParameter parameter) {

        return null;
    }

    public static class GifImageLoaderParameter extends AssetLoaderParameters<GifImage> {

        public PlayMode playMode = PlayMode.LOOP;
    }
}