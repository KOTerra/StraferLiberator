package com.port.entity.item.player;

import greenfoot.*;
import java.util.HashMap;

import com.game.straferliberator.StraferLiberator;
import com.port.entity.item.Item;
import com.port.utils.graphics.GifImage;
import com.port.world.WorldData;
import com.port.world.structure.WorldStructures;

import java.util.ArrayList;

public class Laser extends Item
{
  
    public static final int damage=15;
    
     
    private long time=0;
    private final int speed=10;
    private int caz=0;
    
    GifImage laserImg=StraferLiberator.assetManager.get("images/item/laserPlayer.gif",GifImage.class);
    
    public Laser(double  grade){
        
        this.setRotation((int)grade);
   
        
        this.time=0;
       

        Greenfoot.playSound("sounds/laser.mp3");
    }
    
    
     
    
    protected void atac(){
       move(speed);
    }
    
    public void act() 
    {
        
        //super.damage();
        setImage(laserImg.getCurrentImage());
         
        if(!WorldData.PAUZA){
            if(isAtEdge()|| isTouching(WorldStructures.class)){
                getWorld().removeObject(this);
                return;
            }
            
        
            atac();
        }
    }    
}
