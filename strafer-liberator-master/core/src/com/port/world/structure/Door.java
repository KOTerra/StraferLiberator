package com.port.world.structure;


import greenfoot.*;

import java.util.ArrayList;

import com.port.entity.item.player.Light;
import com.port.entity.mover.player.Player;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

public class Door extends PereteInvizibil {

    //worldsection 12 x3300 y6080
    GreenfootImage img = new GreenfootImage("perete/pereteInviz_mare.png");

    public Door() {
        super("W", 1, "mare");
        img.setTransparency(0);
        setImage(img);

    }
    int b = -1;

    void update() {
        Player player = ((PlayWorld) getWorld()).getPlayer();
        setImage(img);
        if (isTouching(Light.class)) {
            img.setTransparency(100);

        } else {
            img.setTransparency(0);
        }
        if (isTouching(Player.class)) {
            b *= (-1);
            if (b == 1) {
                img.setTransparency(0);
            } else {
                img.setTransparency(100);
            }

            player.knockbacked = true;
            player.knockback(0.1, this, 80, 100);
        }

        if (isTouching(Light.class)) {
            if (WorldData.hasBlackHole && WorldData.hasIceLock && WorldData.hasSword && WorldData.hasLaser && WorldData.hasLantern && WorldData.hasPortalGun) {
                getWorld().removeObject(this);

            }
        }

    }

    public void act() {
        if (!WorldData.PAUZA) {
            update();
        }
    }
}
