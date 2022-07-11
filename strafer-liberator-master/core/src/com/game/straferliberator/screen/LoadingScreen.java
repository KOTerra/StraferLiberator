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
				// System.out.println("gata");
				if (game.assetManager.isFinished()) {
					game.setScreen(new GameScreen(game));
				}
			}
		}

		// System.out.println("loader");
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

		game.assetManager.setLoader(GreenfootImage.class, new GreenfootImageLoader(new InternalFileHandleResolver()));
		// dam load la imageuri
		game.assetManager.load("images/map/worldSection/worldSection11.png", GreenfootImage.class);
		game.assetManager.load("images/map/worldSection/worldSection12.png", GreenfootImage.class);
		game.assetManager.load("images/map/worldSection/worldSection13.png", GreenfootImage.class);
		game.assetManager.load("images/map/worldSection/worldSection21.png", GreenfootImage.class);
		game.assetManager.load("images/map/worldSection/worldSection22.png", GreenfootImage.class);
		game.assetManager.load("images/map/worldSection/worldSection23.png", GreenfootImage.class);
		game.assetManager.load("images/UI/menu/mainMenu/logo.png", GreenfootImage.class);
		game.assetManager.load("images/UI/menu/pauseMenu/pauseLogo.png", GreenfootImage.class);
		game.assetManager.load("images/item/sabieHold_W.png", GreenfootImage.class);
		game.assetManager.load("images/item/sabieHold_A.png", GreenfootImage.class);
		game.assetManager.load("images/item/sabieHold_S.png", GreenfootImage.class);
		game.assetManager.load("images/item/sabieHold_D.png", GreenfootImage.class);

		game.assetManager.setLoader(Font.class, new FontLoader(new InternalFileHandleResolver()));
		// dam load la fonturi
		game.assetManager.load("fonts/consolas.fnt", Font.class, new FontLoaderParameters(false, false, 24));
		game.assetManager.load("fonts/edo.fnt", Font.class, new FontLoaderParameters(false, false, 10));

		game.assetManager.setLoader(GreenfootSound.class, new GreenfootSoundLoader(new InternalFileHandleResolver()));
		// dam load la sunete
		game.assetManager.load("sounds/music/Rename.mp3", GreenfootSound.class);
		game.assetManager.load("sounds/music/Default.mp3", GreenfootSound.class);
		game.assetManager.load("sounds/music/Combat.mp3", GreenfootSound.class);
		game.assetManager.load("sounds/sabie.mp3", GreenfootSound.class);
		game.assetManager.load("sounds/laser.mp3", GreenfootSound.class);

		game.assetManager.setLoader(GifImage.class, new GifImageLoader(new InternalFileHandleResolver()));
		// dam load la gifuri

		// fundal
		game.assetManager.load("images/UI/menu/mainMenu/blur.gif", GifImage.class);
		game.assetManager.load("images/UI/menu/pauseMenu/pauseBlur.gif", GifImage.class);
		game.assetManager.load("images/UI/menu/mainMenu/playerAnimation.gif", GifImage.class);
		game.assetManager.load("images/effects/kingcrimson.gif", GifImage.class);

		// npc
		game.assetManager.load("images/npc/inamic/goblin/goblin_m_Idle.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/goblin/goblin_death.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/goblin/goblin_m_W.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/goblin/goblin_m_A.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/goblin/goblin_m_S.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/goblin/goblin_m_D.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/goblin/sabie_goblin_W.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/goblin/sabie_goblin_A.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/goblin/sabie_goblin_S.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/goblin/sabie_goblin_D.gif", GifImage.class);

		game.assetManager.load("images/npc/inamic/stroke/rumble.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/stroke/stroke_death.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/stroke/stroke_idle.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/stroke/stroke_m.gif", GifImage.class);

		game.assetManager.load("images/npc/inamic/schrodingersCat/schrodingersCat_m.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/schrodingersCat/schrodingersCat_m_Idle.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/schrodingersCat/schrodingersCat_Enter.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/schrodingersCat/schrodingersCat_Exit.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/schrodingersCat/schrodingersCat_Explode.gif", GifImage.class);

		game.assetManager.load("images/npc/inamic/droid/droid.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/droid/droid_death.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/droid/laserDroid.gif", GifImage.class);

		game.assetManager.load("images/npc/inamic/dolpatian/dolpatian_m_Idle.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/dolpatian/dolpatian_m_W.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/dolpatian/dolpatian_m_A.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/dolpatian/dolpatian_m_S.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/dolpatian/dolpatian_m_D.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/dolpatian/dolpatian_death.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/dolpatian/taserDolpatian_W.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/dolpatian/taserDolpatian_A.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/dolpatian/taserDolpatian_S.gif", GifImage.class);
		game.assetManager.load("images/npc/inamic/dolpatian/taserDolpatian_D.gif", GifImage.class);

		game.assetManager.load("images/npc/friendly/keanu_idle.gif", GifImage.class);
		game.assetManager.load("images/npc/friendly/stonks_idle.gif", GifImage.class);
		game.assetManager.load("images/npc/friendly/tsoukalos_idle.gif", GifImage.class);

		// pickUps
		game.assetManager.load("images/item/pickUp/blackHolePickUp.gif", GifImage.class);
		game.assetManager.load("images/item/pickUp/iceLockPickUp.gif", GifImage.class);
		game.assetManager.load("images/item/pickUp/lanternPickUp.gif", GifImage.class);
		game.assetManager.load("images/item/pickUp/laserPickUp.gif", GifImage.class);
		game.assetManager.load("images/item/pickUp/pill.gif", GifImage.class);
		game.assetManager.load("images/item/pickUp/portalGunPickUp.gif", GifImage.class);
		game.assetManager.load("images/item/pickUp/swordPickUp.gif", GifImage.class);

		// items
		game.assetManager.load("images/item/blackHole.gif", GifImage.class);
		game.assetManager.load("images/item/iceLock.gif", GifImage.class);
		game.assetManager.load("images/item/lantern.gif", GifImage.class);
		game.assetManager.load("images/item/laserPlayer.gif", GifImage.class);
		game.assetManager.load("images/item/portalA.gif", GifImage.class);
		game.assetManager.load("images/item/portalD.gif", GifImage.class);
		game.assetManager.load("images/item/portalGunW.gif", GifImage.class);
		game.assetManager.load("images/item/portalGunA.gif", GifImage.class);
		game.assetManager.load("images/item/portalGunS.gif", GifImage.class);
		game.assetManager.load("images/item/portalGunD.gif", GifImage.class);
		game.assetManager.load("images/item/sabie_a_W.gif", GifImage.class);
		game.assetManager.load("images/item/sabie_a_A.gif", GifImage.class);
		game.assetManager.load("images/item/sabie_a_S.gif", GifImage.class);
		game.assetManager.load("images/item/sabie_a_D.gif", GifImage.class);

	}

}