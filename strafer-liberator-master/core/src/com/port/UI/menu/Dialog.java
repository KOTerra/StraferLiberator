package com.port.UI.menu;

import greenfoot.*;

import java.util.List;

import com.port.UI.buton.Buton;
import com.port.entity.mover.npc.Npc;
import com.port.entity.mover.npc.friendly.Friendly;
import com.port.world.WorldData;
import com.port.utils.graphics.Text;
import com.port.utils.loader.DialogLoader;

public class Dialog extends Menu {

    protected int nrSlide;

    protected int nrDialog;

   
    protected String img;
    protected Npc npc;
    protected GreenfootImage text;
    protected boolean addedButon = false;
    protected boolean addedText = false;
    protected List lines ;
    
    
    public Dialog(Friendly npcref, String imgref,int nrDialog) { 
        WorldData.PAUZA = true;
        img = imgref;
        npc = npcref;
        nrSlide = 0;
        this.nrDialog=nrDialog;
        addedButon = false;
        addedText = false;
        lines=DialogLoader.loadPhrases(img, nrDialog);
        setImage("UI/dialog/dialog" + img + ".png");
    

    }

    public String getLine(int nr) {//nr slideului

        String line = (String)lines.get(nr);
        /////parsarea
        return line;
    }

    public void displayText(String txt) {
        if (!addedText) {
            getWorld().addObject(new Text(txt, 24), WorldData.WIDTH/2+24, getY()-WorldData.HEIGHT/5+30);
            addedText = true;
        }
    }

    public void addButon() {
        if (!addedButon) {
            getWorld().addObject(new Buton("next", this), getX()+218, getY()+getImage().getHeight()/2-33);
            addedButon = true;
        }
    }

    public void act() {
        addButon();
        displayText(getLine(getNrSlide()));
    }

    public int getNrSlide() {
        return nrSlide;
    }

    public void setNrSlide(int nrSlide) {
        this.nrSlide = nrSlide;
    }

    public boolean isLastSlide() {
        return nrSlide == lines.size()-1 ? true : false;
    }

    public boolean isAddedText() {
        return addedText;
    }

    public void setAddedText(boolean addedText) {
        this.addedText = addedText;
    }
     public int getNrDialog() {
        return nrDialog;
    }
}
