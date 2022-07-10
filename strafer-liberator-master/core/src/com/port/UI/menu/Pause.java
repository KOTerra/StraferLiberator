package com.port.UI.menu;

import java.util.ArrayList;
import java.util.List;

import com.game.straferliberator.StraferLiberator;
import com.port.UI.buton.Buton;
import com.port.utils.graphics.GifImage;
import com.port.utils.graphics.Picture;
import com.port.world.WorldData;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Pause extends Menu{
	

    GreenfootImage background=new GreenfootImage("UI/menu/pauseMenu/pauseMenu.png");
    GifImage blurGif=StraferLiberator.assetManager.get("images/UI/menu/pauseMenu/pauseBlur.gif",GifImage.class);
    float blurScaleFactor=WorldData.HEIGHT/blurGif.getCurrentImage().getHeight();
    Picture blur=new Picture();
    Actor logo=new Actor();
    
    List<Actor> thingsToRemove = new ArrayList<>();
    

	boolean addedStuff=false;
    public Pause(){
        setImage(background);
        blurGif.scale(blurGif.getCurrentImage().getWidth()*blurScaleFactor,WorldData.HEIGHT);
        logo.setImage(StraferLiberator.assetManager.get("images/UI/menu/pauseMenu/pauseLogo.png",GreenfootImage.class));
    }
    
    private void addStuff(){
        this.getWorld().addObject(blur, blurGif.getCurrentImage().getWidth()/2, blurGif.getCurrentImage().getHeight()+blurGif.getCurrentImage().getHeight()/2);
        this.getWorld().addObject(logo, WorldData.menuX, WorldData.menuY);
        thingsToRemove.add(blur);
        thingsToRemove.add(logo);       
        
        this.getWorld().addObject(new Buton("Resume",this),97,300);
        this.getWorld().addObject(new Buton("Map",this),52,365);
        this.getWorld().addObject(new Buton("mainMenu",this),119,465);
    }
    
    public void act() {
        if(!addedStuff){
            addStuff();
            addedStuff=true;
        }
        else {
        	blur.setImage(blurGif.getCurrentImage());
        }
    }    
    public List<Actor> getThingsToRemove() {
		return thingsToRemove;
	}

}
