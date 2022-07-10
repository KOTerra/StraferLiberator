package com.port.entity.item.player;


import greenfoot.*;  
import java.util.List;

import com.game.straferliberator.StraferLiberator;
import com.port.entity.item.Item;
import com.port.entity.mover.npc.hostile.Droid;
import com.port.entity.mover.npc.hostile.Goblin;
import com.port.entity.mover.npc.hostile.HostileNpc;
import com.port.entity.mover.npc.hostile.SchrodingersCat;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.GifImage;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

public class BlackHole extends Item {
 private final long constantEraseTime = 600;int time=0;
    

    GifImage gif = StraferLiberator.assetManager.get("images/item/blackHole.gif",GifImage.class);

    public BlackHole() {
        setImage(gif.getCurrentImage());
    }
 
    public void suck() {
        List npcs = getObjectsInRange(WorldData.WIDTH, HostileNpc.class);
        for (Object in : npcs) {
            HostileNpc inamic = (HostileNpc) in;
            if (!(inamic instanceof SchrodingersCat)) {
                if ((inamic instanceof Droid) || (inamic instanceof Goblin)) {
                    inamic.turnTowards(this.getX(), this.getY());
                    inamic.move(10);
                }
            }
        }

    }
      private void removeBlackHole() {
        Player player=((PlayWorld)getWorld()).getPlayer();
        if (!player.isEquipBlackHole()) {
            getWorld().removeObject(this);
            player.setEquipBlackHole(false);
        }
        time++;
            if (time > constantEraseTime) {
                getWorld().removeObject(this);
                player.setEquipBlackHole(false);
            }
    }
    public void act() {

        if (!WorldData.PAUZA) {
           
            suck();
            
            setImage(gif.getCurrentImage()); 
            removeBlackHole();
        }

    }
}
