package com.port.actor.npc.items;


import greenfoot.*;
import java.util.List;

import com.port.actor.player.Player;
import com.port.actor.utils.Perete;
import com.port.display.GifImage;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

public class LaserDroid extends NpcItem {

    public int damage = 8;

    private int speed = 6;
    boolean turned = false;
    Player player;

    public static GifImage laserImg = new GifImage("npc/inamic/droid/laserDroid.gif");

    public LaserDroid() {
        //  GreenfootSound sunet=new GreenfootSound("shootshoot.mp3");
        //sunet.play();
        

    }

    private void atac() {
        move(speed);
        setImage(laserImg.getCurrentImage());
        if (isAtEdge() || isTouching(Perete.class)) {
            getWorld().removeObject(this);
            return;
        }
        player = ((PlayWorld) getWorld()).getPlayer();
        if (!turned) {
            turnTowards(player.getX(), player.getY());
            turned = true;
        }
        if (isTouching(Player.class)) {
            player.takeDamage(damage);
            getWorld().removeObject(this);
        }

    }

    public void act() {
        if (!WorldData.PAUZA) {
            atac();
        }
    }

    public int getDamage() {
        return damage;
    }
}
