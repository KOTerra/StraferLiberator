package com.port.actor.npc.friendly;


import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

import com.port.actor.npc.Npc;
import com.port.utils.Scroller;
import com.port.world.PlayWorld;

public class Friendly extends Npc {

    private PlayWorld playWorld;

    public Friendly(PlayWorld playWorldref, Scroller scrl,String dialogFile) {
        super(scrl);
        playWorld = playWorldref;
    }

    public void addDialogs() {
    }

    public PlayWorld getPlayWorld(){
        return playWorld;
    }
    
    public void act() {
        
    }
}
