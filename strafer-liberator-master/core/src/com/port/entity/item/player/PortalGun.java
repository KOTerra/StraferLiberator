package com.port.entity.item.player;


import greenfoot.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.port.entity.item.Item;
import com.port.entity.mover.player.Player;
import com.port.world.WorldData;

public class PortalGun extends Item {

    HashMap<String, GifImage> directie = new HashMap<String, GifImage>();
    GifImage portalGunImg = directie.get(super.itemGif);

    private long time = 0;

    public PortalGun() {

        directie.put("D", new GifImage("item/portalGunD.gif"));
        directie.put("W", new GifImage("item/portalGunW.gif"));
        directie.put("A", new GifImage("item/portalGunA.gif"));
        directie.put("S", new GifImage("item/portalGunS.gif"));

        portalGunImg = directie.get("D");
        this.time = 0;

    }

    protected void move() {
        super.move();

        portalGunImg = directie.get(super.itemGif);
    }

    private void addPortal() {
        if (Greenfoot.mouseClicked(null)) {
            if (Greenfoot.getMouseInfo().getButton() == 1) {  //right 3 left 1
                if (getWorld().getObjects(Portal.class).isEmpty()) {
                    getWorld().addObject(new Portal(), Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
                }
            }
        }
    }

    private void removePortalGun() {
        List players = getWorld().getObjects(Player.class);
        Player player = (Player) players.get(0);
        if (!player.isEquipPortalGun()) {
            getWorld().removeObject(this);
        }
    }

    public void act() {

        if (!WorldData.PAUZA) {
            move();
            addPortal();
            removePortalGun();
        }
        setImage(portalGunImg.getCurrentImage());
    }
}
