package com.port.entity.item.pickup;


import com.game.straferliberator.StraferLiberator;
import com.port.UI.menu.Tutorial;
import com.port.entity.item.Item;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.GifImage;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

import greenfoot.*;
/*
 * un item ce poate fi obtinut de player prin atingere
 */
public class PickUp extends Item {

    protected boolean picked = false;
    String name;
    GifImage gif;

    public PickUp(String name) {
        this.name = name;
        String itemToGet="images/item/pickUp/" + name + "PickUp.gif";
        gif=StraferLiberator.assetManager.get(itemToGet,GifImage.class);
    }

    public PickUp() {
    }

    public void act() {
        if (!WorldData.PAUZA) {
            setImage(gif.getCurrentImage());
             pick();
         
        }
    }

    protected void pick() {
        
        if (isTouching(Player.class)) {
            if (!picked) {
                WorldData.items.add(name);
                switch (name) {
                    case "sword": {
                        WorldData.hasSword = true;
                        getWorld().addObject(new Tutorial("Items", name, 2, false), WorldData.menuX, WorldData.menuY);
                        break;
                    }
                    case "laser": {
                        getWorld().addObject(new Tutorial("Items", name, 2, false), WorldData.menuX, WorldData.menuY);
                        WorldData.hasLaser = true;
                        break;
                    }
                    case "blackHole": {
                        getWorld().addObject(new Tutorial("Items", "blackhole", 2, false), WorldData.menuX, WorldData.menuY);
                        WorldData.hasBlackHole = true;
                        break;
                    }
                    case "iceLock": {
                        getWorld().addObject(new Tutorial("Items", "icelock", 2, false), WorldData.menuX, WorldData.menuY);
                        WorldData.hasIceLock = true;
                        break;
                    }
                    case "lantern": {
                        getWorld().addObject(new Tutorial("Items", name, 3, false), WorldData.menuX, WorldData.menuY);
                        WorldData.hasLantern = true;
                        break;
                    }
                    case "portalGun": {
                        getWorld().addObject(new Tutorial("Items", "portalgun", 3, false), WorldData.menuX, WorldData.menuY);
                        WorldData.hasPortalGun = true;
                        break;
                    }
                }
                
                picked = true;
                getWorld().removeObject(this);
            }
        }

    }

    protected Player getPlayer() {
        return ((PlayWorld)getWorld()).getPlayer();
    }
}
