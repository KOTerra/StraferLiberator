package com.port.display;

import com.port.utils.Buton;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.display.GreenfootImage;


public class Pause extends Menu{

    GreenfootImage img=new GreenfootImage("UI/menu/pauseMenu.png");
    boolean butoanead=false;
    public Pause(){
        setImage(img);
        
    }
    
    private void addButoane(){
        this.getWorld().addObject(new Buton("Resume",this),97,173);
        this.getWorld().addObject(new Buton("Map",this),52,238);
        this.getWorld().addObject(new Buton("Main Menu",this),119,390);
    }
    
    public void act() {
        if(!butoanead){
            addButoane();
            butoanead=true;
        }
    }    
}
