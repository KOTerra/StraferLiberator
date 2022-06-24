package com.port.entity.item.npc;

import com.port.entity.mover.player.Player;
import com.port.world.WorldData;

import greenfoot.*;

public class ExplozieSchrodingersCat extends NpcItem {

    
    public static final int mass = 2;

    boolean gaveDamage=false;
    public ExplozieSchrodingersCat(){
        setImage("npc/inamic/schrodinger's cat/explosion.png");
    }
    protected void atac() {
        if (isTouching(Player.class)) {
            Player player = (Player) getWorld().getObjects(Player.class).get(0);
            if (player != null) {
                player.knockbacked = true;
                player.knockback(0.1, this, this.mass, 80);
                if (!gaveDamage) {
                    player.takeDamage(player.getHp()/2);
                    gaveDamage = true;
                    getWorld().removeObject(this);
                }
            }
        }
    }

    public void act() {
        if(!WorldData.PAUZA){
            atac();
        }
    }
}
