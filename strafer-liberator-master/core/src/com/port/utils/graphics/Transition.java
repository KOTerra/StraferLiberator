package com.port.utils.graphics;


import com.badlogic.gdx.utils.Array;
import com.game.straferliberator.StraferLiberator;
import com.port.UI.menu.Menu;
import com.port.world.WorldData;

import greenfoot.*;

/*
 * clasa ajutatoare
 * animatie fullscreen cu scop estetic
 */
public class Transition extends Menu {

    private Animation animation;

    public Transition(String name, int nrf, int cc) {

        Array imgs = StraferLiberator.assetManager.get(name,GifImage.class).getImages();
        GreenfootImage[] images = new GreenfootImage[imgs.size];

        for (int i = 0; i < imgs.size; i++) {
            images[i] = (GreenfootImage) imgs.get(i);
        }
        animation = new Animation(this, images);

        animation.setCycleActs(nrf);
        animation.setCycleCount(cc);

        animation.run();
        animation.setActiveState(true);
    }

    
    
    public void act() {

        if (!WorldData.PAUZA) {
            setLocation(WorldData.WIDTH/2, WorldData.HEIGHT/2);
            animation.run();
            if (!animation.isActive()) {
                getWorld().removeObject(this);
            }

        }
    }
}
