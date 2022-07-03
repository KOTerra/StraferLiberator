package com.port.entity.mover.npc.friendly;

import com.port.UI.menu.Dialog;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.GifImage;
import com.port.world.PlayWorld;
import com.port.world.Scroller;
import com.port.world.WorldData;

import greenfoot.*;  


public class Keanu extends Friendly
{
    private PlayWorld playWorld;
    private String dialogFile;
    private int maxSlide;
    private int nrDialog;
    GifImage img=new GifImage("npc/friendly/keanu_idle.gif");
    
    public Keanu(PlayWorld pw,Scroller scrl, String dialogFileref, int nrDialog) {
        super(pw,scrl,dialogFileref);
        playWorld=pw;
        this.nrDialog=nrDialog;
        dialogFile=dialogFileref;
        setImage(img.getCurrentImage());
        
    }
    
    public void addDialogs(){
        getWorld().addObject(new Dialog(this, "Keanu",  nrDialog), WorldData.WIDTH/2, WorldData.HEIGHT*7/10);
    }
    
    private int timer=0;
    public void act() {
        
        if (!WorldData.PAUZA) {
            timer ++;
            if (isTouching(Player.class) && !WorldData.addedDialogs&& timer>=WorldData.FPS*3){
                timer=0;
                this.addDialogs();
                WorldData.addedDialogs=true;
            }
            setImage(img.getCurrentImage());
        }
    }
  public int getNrDialog() {
        return nrDialog;
    }

    public void setNrDialog(int nrDialog) {
        this.nrDialog = nrDialog;
    }    
}
