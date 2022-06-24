package com.port.entity.item.pickup;

import com.port.entity.mover.player.Player;
import com.port.utils.graphics.GifImage;
import com.port.world.WorldData;

import greenfoot.*;  


public class Pill extends PickUp{
    
    int hpToAdd;
    GifImage gif=new GifImage("item/pickUp/pill.gif");

    private final long constantEraseTime = 400;int time=0;
    public Pill(int hpToAdd){
        this.hpToAdd = hpToAdd;
        setImage(gif.getCurrentImage());
    }
    
    protected void pick(){
        if(isTouching(Player.class)){
            Player player=(Player)getOneIntersectingObject(Player.class);
            player.takeDamage(-hpToAdd);//adauga viata
            getWorld().removeObject(this);
        }
    }
    
      public void act() {
        if(!WorldData.PAUZA){
            setImage(gif.getCurrentImage());
            pick();
            time++;
            if (time > constantEraseTime) {
                getWorld().removeObject(this);
            }
        }
    }
    
}
