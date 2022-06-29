package com.port.UI.menu;

import com.port.UI.buton.Buton;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

import greenfoot.*; 


public class GameOver extends Menu{
   
    PlayWorld playWorld;

    boolean butoanead = false;

    public GreenfootSound music = new GreenfootSound("music/Rename.mp3");

    public GameOver(PlayWorld playWorldref) {

        WorldData.PAUZA = true;
        setImage("UI/menu/gameOver.png");
        playWorld=playWorldref;

    }

    private void addButoane() {
        getWorld().addObject(new Buton("Continue", this), WorldData.WIDTH/2-150,	WorldData.HEIGHT-100);
        getWorld().addObject(new Buton("Main Menu", this), WorldData.WIDTH/2+150, WorldData.HEIGHT-100);
    }
    

    public void act() {
       
        if (!butoanead) {
            addButoane();
            butoanead = true;
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
