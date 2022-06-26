package com.port.UI.buton;


import greenfoot.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.port.UI.UI;
import com.port.UI.menu.Dialog;
import com.port.UI.menu.GameOver;
import com.port.UI.menu.MainMenu;
import com.port.UI.menu.MapMenu;
import com.port.UI.menu.Menu;
import com.port.UI.menu.Pause;
import com.port.UI.menu.Tutorial;
import com.port.UI.menu.TutorialFolder;
import com.port.entity.mover.player.Player;
import com.port.system.SaveSystem;
import com.port.utils.graphics.Text;
import com.port.world.PlayWorld;
import com.port.world.Scroller;
import com.port.world.WorldData;

public class Buton extends UI {

    GreenfootImage img0, img1;
    private String img = "";

    private String tipTutorial;
    Tutorial tutorial;
    ArrayList<Tutorial> tutorials;
    Tutorial tutorialToOpen;
    TutorialFolder tutorialFolderToOpen;
    HashMap<String, List<GreenfootImage>> tutorialsImages;

    Dialog dialog;

    boolean clicked = false;
    Object obj;

    public Buton(String imgref, Object menuref) {
        img = imgref;
        obj = menuref;
        if (obj instanceof Dialog) {
            dialog = (Dialog) obj;
        }
        if (obj instanceof Tutorial) {
            tutorial = (Tutorial) obj;
        }
        img0 = new GreenfootImage("UI/buton/" + img + "0.png");
        img1 = new GreenfootImage("UI/buton/" + img + "1.png");
        setImage(img0);

    }

    public Buton(String imgref, Object menuref, String tipref, ArrayList<Tutorial> tutorials) {      ///for opening a tutorial category
        img = imgref;
        obj = menuref;
        tipTutorial = tipref;
        this.tutorials = tutorials;

        if (obj instanceof Dialog) {
            dialog = (Dialog) obj;
        }
        if (obj instanceof Tutorial) {
            tutorial = (Tutorial) obj;
        }
        img0 = new GreenfootImage("UI/buton/" + img + "0.png");
        img1 = new GreenfootImage("UI/buton/" + img + "1.png");
        setImage(img0);

    }

    public Buton(String imgref, Object menuref, String tipref, Menu tutToOp) {                  ///for opening a tutorial tutorial from a category
        img = imgref;
        obj = menuref;
        tipTutorial = tipref;
        if (tutToOp instanceof Tutorial) {
            tutorialToOpen = (Tutorial) tutToOp;
        }
        if (tutToOp instanceof TutorialFolder) {
            tutorialFolderToOpen = (TutorialFolder) tutToOp;
        }
        this.tutorials = tutorials;

        if (obj instanceof Dialog) {
            dialog = (Dialog) obj;
        }
        if (obj instanceof Tutorial) {
            tutorial = (Tutorial) obj;
        }
        img0 = new GreenfootImage("UI/buton/" + img + "0.png");
        img1 = new GreenfootImage("UI/buton/" + img + "1.png");
        setImage(img0);

    }

    protected void checkHover() {

        if (Greenfoot.mouseMoved(this)) {
            setImage(img1);

        }
        if ( !Greenfoot.mouseMoved(this)) {
            setImage(img0);
        }

    }

    protected void checkClick() {

        if (Greenfoot.mouseClicked(this)) {

            if (Greenfoot.getMouseInfo().getButton() == 1) {
                switch (img) {
                    case "Next": {
                        if (obj instanceof Dialog) {
                            dialog.setNrSlide(dialog.getNrSlide() + 1);
                            dialog.setAddedText(false);
                            getWorld().removeObjects(getWorld().getObjects(Text.class));
                        }

                        if (obj instanceof Tutorial) {
                            tutorial.setNrSlide(tutorial.getNrSlide() + 1);
                            tutorial.setAddedPicture(false);
                            getWorld().removeObject(tutorial.getPicture());
                        }
                        break;
                    }
                    case "Back": {

                        if (obj instanceof Tutorial) {
                            tutorial.setNrSlide(tutorial.getNrSlide() - 1);
                            tutorial.setAddedPicture(false);
                            getWorld().removeObject(tutorial.getPicture());
                        }
                        break;
                    }

                    case "X": {
                        if (obj instanceof Dialog) {
                            WorldData.PAUZA = false;
                            getWorld().removeObject((Actor) obj);
                            WorldData.addedDialogs = false;

                            dialog.setAddedText(false);
                            SaveSystem.save(WorldData.saveFileNumber, ((PlayWorld) getWorld()).getPlayer());

                            WorldData.nrEvent = WorldData.dialogSuccesion[dialog.getNrDialog()] + 1;

                            getWorld().removeObjects(getWorld().getObjects(Text.class));
                        }
                        if (obj instanceof Tutorial) {

                            getWorld().removeObject((Actor) obj);
                            WorldData.addedDialogs = false;

                            tutorial.setAddedPicture(false);
                            getWorld().removeObject(tutorial.getPicture());

                            if (tutorial.getTip() != "Combat") {
                                WorldData.nrEvent++;
                            }
                            SaveSystem.save(WorldData.saveFileNumber, ((PlayWorld) getWorld()).getPlayer());

                            if (!tutorial.isInPause()) {        //daca e direct in lume 
                                WorldData.PAUZA = false;
                            }
                            
                        }

                      
                        if (obj instanceof MapMenu) {
                            MapMenu mm = (MapMenu) obj;
                            mm.clear();
                            getWorld().removeObject(mm);
                            getWorld().addObject(new Pause(), WorldData.menuX, WorldData.menuY);
                        }

                        getWorld().removeObjects(getWorld().getObjects(Buton.class));
                        break;
                    }

                
                    case "Resume": {

                        WorldData.PAUZA = false;
                        Player.toggledPause = false;
                        if (obj instanceof Menu) {
                            getWorld().removeObject((Menu) obj);
                        }

                        getWorld().removeObjects(getWorld().getObjects(Buton.class));
                        break;

                    }
                    case "Map": {

                        getWorld().addObject(new MapMenu((PlayWorld) getWorld()), WorldData.menuX, WorldData.menuY);

                        getWorld().removeObject((Menu) obj);
                        getWorld().removeObjects(getWorld().getObjects(Buton.class));

                        break;
                    }
                    case "Main Menu": {
                        getWorld().addObject(new MainMenu(), WorldData.menuX, WorldData.menuY);

                        ((Player) getWorld().getObjects(Player.class).get(0)).setLoaded(false);
                        Player.toggledPause = false;

                        if (obj instanceof Menu) {
                            getWorld().removeObject((Menu) obj);
                            SaveSystem.save(WorldData.saveFileNumber, ((PlayWorld) getWorld()).getPlayer());
                        }
                        if (obj instanceof GameOver) {
                            GameOver go = (GameOver) obj;
                            go.getMusic().stop();
                        }

                        getWorld().removeObjects(getWorld().getObjects(Buton.class));

                        /////se da save
                        break;
                    }
                  
                    case "New Game": {//continue fara load
                        WorldData.PAUZA = false;
                        Player.toggledPause = false;
                        if (obj instanceof Menu) {
                            getWorld().removeObject((Menu) obj);
                        }

                        //save file nou
                        //inceputu jocului
                        if (obj instanceof MainMenu) {
                            MainMenu mm = (MainMenu) obj;
                            mm.getMusic().stop();
                            Player player = (Player) getWorld().getObjects(Player.class).get(0);
                            WorldData.reset();
                            WorldData.saveFileNumber++;
                            player.setLocation(PlayWorld.originalX - Scroller.scrolledX, PlayWorld.originalY - Scroller.scrolledY);
                            player.setHp(Player.hpMax);
                            SaveSystem.save(WorldData.saveFileNumber, player);
                            player.load();

                            if (!player.isInViata()) {
                                player.revive();
                            }
                        }

                        getWorld().removeObjects(getWorld().getObjects(Buton.class));
                        break;
                    }
                    case "Continue": {
                        WorldData.PAUZA = false;
                        Player.toggledPause = false;
                        if (obj instanceof Menu) {
                            getWorld().removeObject((Menu) obj);
                        }
                        if (obj instanceof MainMenu) {
                            MainMenu mm = (MainMenu) obj;
                            mm.getMusic().stop();
                            Player player = (Player) getWorld().getObjects(Player.class).get(0);
                            player.load();

                            if (!player.isInViata()) {
                                player.revive();
                            }
                        }
                        if (obj instanceof GameOver) {
                            GameOver go = (GameOver) obj;
                            go.getMusic().stop();
                            ((Player) getWorld().getObjects(Player.class).get(0)).setLoaded(false);
                            ((Player) getWorld().getObjects(Player.class).get(0)).load();
                            ((Player) getWorld().getObjects(Player.class).get(0)).revive();
                        }

                        getWorld().removeObjects(getWorld().getObjects(Buton.class));

                        break;
                    }
                    default: {

                    }
                }

            }
        }

    }

    public void exists() { //verifica daca trebuie inchis 
        switch (img) {
            case "Next": {
                if (obj instanceof Dialog) {
                    if (dialog.isLastSlide()) {
                        this.getWorld().addObject(new Buton("X", dialog), this.getX(), this.getY());
                        this.getWorld().removeObject(this);
                    }
                }
                if (obj instanceof Tutorial) {
                    if (tutorial.getNrSlide() > 0) {
                        if (!tutorial.isAddedButonBack()) {
                            this.getWorld().addObject(new Buton("Back", tutorial), 100, this.getY());
                        }
                    }
                    if (tutorial.isLastSlide()) {
                        this.getWorld().addObject(new Buton("X", tutorial), this.getX(), this.getY());
                        this.getWorld().removeObject(this);
                    }
                }
                break;
            }
            case "Back": {
                if (obj instanceof Tutorial) {
                    if (tutorial.isFirstSlide()) {
                        this.getWorld().removeObject(this);
                    }
                }
                break;
            }
            case "X": {
                if (obj instanceof Tutorial) {
                    if (!tutorial.isLastSlide()) {
                        getWorld().addObject(new Buton("Next", obj), this.getX(), this.getY());
                        getWorld().removeObject(this);
                    }
                }
                break;
            }
        }
    }

    public void act() {
        checkHover();
        exists();
        checkClick();
    }

    public String getImg() {
        return img;
    }

}
