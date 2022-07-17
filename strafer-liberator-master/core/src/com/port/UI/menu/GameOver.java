package com.port.UI.menu;

import com.game.straferliberator.StraferLiberator;
import com.port.UI.button.Button;
import com.port.utils.graphics.GifImage;
import com.port.utils.graphics.Picture;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

import greenfoot.*; 


public class GameOver extends Menu{
   
    PlayWorld playWorld;

    boolean addedStuff = false;
    
    public GreenfootSound music = StraferLiberator.assetManager.get("sounds/music/Rename.mp3",GreenfootSound.class);

    public GameOver(PlayWorld playWorldref) {
    	setImage("UI/menu/gameOver/gameOver.png");
        WorldData.PAUZA = true;
        playWorld=playWorldref;
    }

    private void addStuff() {
        getWorld().addObject(new Button("continue", this), WorldData.WIDTH/2-150,	WorldData.HEIGHT-100);
        getWorld().addObject(new Button("mainMenu", this), WorldData.WIDTH/2+150, WorldData.HEIGHT-100);
    }
    

    public void act() {
    	if (!addedStuff) {
            addStuff();
            addedStuff = true;
          //  music.playLoop();
           // music.setVolume(60);
        }
        
    }

	

    public GreenfootSound getMusic() {
        return music;
    }

    public void setMusic(GreenfootSound music) {
        this.music = music;
    }
}
