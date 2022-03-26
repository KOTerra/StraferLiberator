package com.port;


import greenfoot.*;

public class Effect extends Menu {

    private Animation animation;

    public Effect(String name, int nrf, int cc) {

        java.util.List<GreenfootImage> imgs = new GifImage("effects/" + name + ".gif").getImages();
        GreenfootImage[] images = new GreenfootImage[imgs.size()];

        for (int i = 0; i < imgs.size(); i++) {
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
