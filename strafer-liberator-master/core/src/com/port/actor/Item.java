package com.port.actor;

import greenfoot.*;
import greenfoot.actor.Actor;

import java.util.List;

import com.port.actor.player.Player;


public class Item extends Actor
{
  
    @Override public String toString(){
         //the name of the class
         return this.getClass().getSimpleName();
     }
    
    public static String itemGif="D";//the state of the item
    
    protected void move()
    {
        List players = getWorld().getObjects(Player.class);
       
        if (!players.isEmpty()){
            Actor player = (Actor)players.get(0);
             setLocation(player.getX(),player.getY()+15);
        }
       
    }
    
    public void act() 
    {
       
    }    
}
