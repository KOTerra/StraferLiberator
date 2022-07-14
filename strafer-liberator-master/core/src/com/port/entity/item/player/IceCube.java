package com.port.entity.item.player;

import com.game.straferliberator.StraferLiberator;
import com.port.entity.mover.npc.hostile.HostileNpc;
import com.port.world.WorldData;

import greenfoot.*;  

public class IceCube extends IceLock{
    
    GreenfootImage img=StraferLiberator.assetManager.get("images/item/iceCube.png",GreenfootImage.class);
    Actor actor;
    IceLock iceLock;
    private final long constantEraseTime = 256;int time=0;
    
    public IceCube(Actor actor,IceLock icelock){
        this.actor=actor;
       ((HostileNpc)this.actor).setFreeze(true);
        this.iceLock=icelock;
        iceLock.setSent(false);
        
        int width=(int) actor.getImage().getWidth();
        int height=(int) actor.getImage().getHeight();
        img.scale(width, height);
        setImage(img);
        
    }
    
    
    public void act() {
          if (!WorldData.PAUZA) {
        	  setLocation(this.actor.getX()+175,this.actor.getY()-120);
            time++;
            if (time > constantEraseTime) {
                ((HostileNpc)actor).setFreeze(false);
                getWorld().removeObject(iceLock);
                getWorld().removeObject(this);
                
            }
        }
    }    
}
