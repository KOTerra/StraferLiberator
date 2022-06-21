package com.port.display;


import com.port.utils.Buton;
import com.port.utils.SaveSystem;
import com.port.world.WorldData;

import greenfoot.*;
import greenfoot.audio.GreenfootSound;

public class MainMenu extends Menu {

    GifImage background = new GifImage("UI/menu/titleScreen.gif");

    
    boolean butoanead = false;

    public GreenfootSound music = new GreenfootSound("music/Rename.mp3");

    public MainMenu() {

        WorldData.PAUZA = true;
        setImage("UI/menu/titleScreen.png");
        WorldData.saveFileNumber=com.port.utils.SaveSystem.getNumberOfSaveFiles()-1;
            
    }

    private void addButoane() {
        getWorld().addObject(new Buton("Continue", this), 805, 225);
        getWorld().addObject(new Buton("New Game", this), 805, 380);
       
    }

    public void act() {
        setImage(background.getCurrentImage());
    
        if (!butoanead) {
            addButoane();
            butoanead = true;
            music.playLoop();
            music.setVolume(60);
        }
    }

    public GreenfootSound getMusic() {
        return music;
    }

    public void setMusic(GreenfootSound music) {
        this.music = music;
    }
}
