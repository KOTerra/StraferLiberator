package com.port.entity.mover.npc.friendly;


import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

import com.port.entity.mover.npc.Npc;
import com.port.world.PlayWorld;
import com.port.world.Scroller;

public class FriendlyNpc extends Npc {

    private PlayWorld playWorld;

    public FriendlyNpc(PlayWorld playWorldref, Scroller scrl,String dialogFile) {
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
