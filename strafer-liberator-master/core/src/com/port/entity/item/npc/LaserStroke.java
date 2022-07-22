package com.port.entity.item.npc;


import com.badlogic.gdx.graphics.Color;
import com.port.entity.mover.npc.hostile.Stroke;
import com.port.entity.mover.player.Player;
import com.port.world.WorldData;

import greenfoot.*;

public class LaserStroke extends NpcItem {
    int damage=2;
    boolean gaveDamage=false;
    
    Stroke stroke;
    GreenfootImage art = new GreenfootImage(1024,576);
    int xStart, yStart, dist;

    public LaserStroke(Stroke st, int dist) { ///ochi stang dist intre ochi
        stroke = st;
        this.xStart = stroke.getOchix();
        this.yStart = stroke.getOchiy();
        this.dist = dist;

        Color color = Color.RED;
        art.setColor(color);

        prepareLasers();

    }

    void prepareLasers() {
        int x1, y1, x2, y2;            //puncte de break
        int mod1, mod2;
        mod1 = Greenfoot.getRandomNumber(150);
        mod2 = Greenfoot.getRandomNumber(50);

        x1 = Greenfoot.getRandomNumber(75) + 75 - mod1;
        y1 = Greenfoot.getRandomNumber(25) + 25;
        x2 = Greenfoot.getRandomNumber(175) - mod2;
        y2 = Greenfoot.getRandomNumber(75) + y1;

        Player player = stroke.getPlayer();
        int xFin = (int) ((player.getX() - dist / 2) / 2);
        int yFin = (int) ((player.getY()) / 2);

        art.drawLine(xStart / 2, yStart / 2, x1, y1);
        art.drawLine(xStart / 2 + dist / 2, yStart / 2, x1 + dist / 2, y1);
        art.drawLine(x1, y1, x2, y2);
        art.drawLine(x1 + dist / 2, y1, x2 + dist / 2, y2);
        art.drawLine(x1, y1, x2, y2);
        art.drawLine(x1 + dist / 2, y1, x2 + dist / 2, y2);
        art.drawLine(x2, y2, xFin, yFin);
        art.drawLine(x2 + dist / 2, y2, xFin + dist / 2, yFin);

        art.scale(WorldData.WIDTH, WorldData.HEIGHT);
        setImage(art);

    }

    public void atac(){
        int r=Greenfoot.getRandomNumber(100);
        if(r%5==0){
            gaveDamage=true;
        }
        Player player=stroke.getPlayer();
        if(!gaveDamage){
            player.takeDamage(damage);
            gaveDamage=true;
        }
    }
    
    public void act() {
        if (!WorldData.PAUZA) {
            setLocation(getX()-(xStart-stroke.getOchix()), getY()-(yStart-stroke.getOchiy()));
            atac();
        }
    }
}
