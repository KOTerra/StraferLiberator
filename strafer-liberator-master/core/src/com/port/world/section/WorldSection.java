package com.port.world.section;


import com.port.entity.mover.player.Player;
import com.port.world.PlayWorld;
import com.port.world.Scroller;
import com.port.world.WorldData;
import com.port.world.section.sections.WorldSection11;
import com.port.world.section.sections.WorldSection12;
import com.port.world.section.sections.WorldSection13;
import com.port.world.section.sections.WorldSection21;
import com.port.world.section.sections.WorldSection22;
import com.port.world.section.sections.WorldSection23;
import com.port.world.structure.Door;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/*
 * clasa generala de WorldSection
 * un WorldSection reprezinta o portiune din intreaga lume
 */
public class WorldSection extends Actor {

    protected PlayWorld world;

    protected Scroller scroller;
    protected Player player;

    public void initWorldSection11() {
        WorldData.visitedWorldSections[1][1] = true;
       
        WorldSection11 ws = new WorldSection11(world, scroller, player);
        ws.init();

   

    }

    public void initWorldSection12() {
        WorldData.visitedWorldSections[1][2] = true;

        world.initObject(new Door(), 3300, 6080);
        WorldSection12 ws = new WorldSection12(world, scroller, player);
        ws.init();

    }

    public void initWorldSection13() {
        WorldData.visitedWorldSections[1][3] = true;

        WorldSection13 ws = new WorldSection13(world, scroller, player);
        ws.init();
    }

    public void initWorldSection21() {
        WorldData.visitedWorldSections[2][1] = true;

        WorldSection21 ws = new WorldSection21(world, scroller, player);
        ws.init();
    }

    public void initWorldSection22() {
        WorldData.visitedWorldSections[2][2] = true;

        WorldSection22 ws = new WorldSection22(world, scroller, player);
        ws.init();
    }

    public void initWorldSection23() {
        WorldData.visitedWorldSections[2][3] = true;

        WorldSection23 ws = new WorldSection23(world, scroller, player);
        ws.init();
    }

    public void act() {
        // Add your action code here.
    }

    public PlayWorld getWorld() {
        return world;
    }

    public Scroller getScroller() {
        return scroller;
    }

    public Player getPlayer() {
        return player;
    }
}
