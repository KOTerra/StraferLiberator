package com.port.entity.item.player;

import com.port.entity.item.Item;
import com.port.world.WorldData;

import greenfoot.*;  

public class LaserHold extends Item{
     public LaserHold(){
         setImage("item/laserPlayerHold_"+Item.itemGif+".png");
       
    }
                                                                                                                       
    
    private void exi(){
        if(getWorld().getObjects(Laser.class)!=null){
            getWorld().removeObject(this);
        }
    }
    protected void move(){
        super.move();
        
       
    }
    
    
    public void act() {
        
         setImage("item/laserPlayerHold_"+Item.itemGif+".png");
        if(!WorldData.PAUZA){
            move(); 
            exi();
        }
         

    }    
}
  

