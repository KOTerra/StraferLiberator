package com.port.entity.item.npc;


import greenfoot.*;
import java.util.List;

import com.game.straferliberator.StraferLiberator;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.GifImage;
import com.port.world.PlayWorld;
import com.port.world.WorldData;
import com.port.world.structure.Perete;

public class LaserDroid extends NpcItem {

    public int damage = 8;

    private int speed = 6;
    boolean turned = false;
    Player player;

    private GifImage laserImg = StraferLiberator.assetManager.get("images/npc/inamic/droid/laserDroid.gif",GifImage.class);

    public LaserDroid() {

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
        if (intersects(player)) {
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
