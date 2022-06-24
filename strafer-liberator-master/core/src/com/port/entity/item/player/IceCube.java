package com.port.entity.item.player;

import com.port.entity.mover.npc.hostile.Inamic;
import com.port.world.WorldData;

import greenfoot.*;  

public class IceCube extends IceLock{
    
    GreenfootImage img=new GreenfootImage("item/iceCube.png");
    Actor actor;
    IceLock iceLock;
    private final long constantEraseTime = 256;int time=0;
    
    public IceCube(Actor actor,IceLock icelock){
        this.actor=actor;
       ((Inamic)this.actor).setFreeze(true);
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
                ((Inamic)actor).setFreeze(false);
                getWorld().removeObject(iceLock);
                getWorld().removeObject(this);
                
            }
        }
    }    
}
