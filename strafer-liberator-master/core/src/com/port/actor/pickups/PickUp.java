package com.port.actor.pickups;


import com.port.actor.Item;
import com.port.actor.player.Player;
import com.port.display.GifImage;
import com.port.display.Tutorial;
import com.port.world.WorldData;

import greenfoot.*;

public class PickUp extends Item {

    protected boolean picked = false;
    String name;
    GifImage gif;

    public PickUp(String name) {
        this.name = name;
        gif = new GifImage("item/pickUp/" + name + "PickUp.gif");
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
                    case "blackhole": {
                        getWorld().addObject(new Tutorial("Items", name, 2, false), WorldData.menuX, WorldData.menuY);
                        WorldData.hasBlackHole = true;
                        break;
                    }
                    case "icelock": {
                        getWorld().addObject(new Tutorial("Items", name, 2, false), WorldData.menuX, WorldData.menuY);
                        WorldData.hasIceLock = true;
                        break;
                    }
                    case "lantern": {
                        getWorld().addObject(new Tutorial("Items", name, 3, false), WorldData.menuX, WorldData.menuY);
                        WorldData.hasLantern = true;
                        break;
                    }
                    case "portalgun": {
                        getWorld().addObject(new Tutorial("Items", name, 3, false), WorldData.menuX, WorldData.menuY);
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
        return (Player) (getWorld().getObjects(Player.class).get(0));
    }
}
