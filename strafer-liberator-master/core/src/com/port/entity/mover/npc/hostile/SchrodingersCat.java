package com.port.entity.mover.npc.hostile;


import greenfoot.*;

import java.util.List;

import com.badlogic.gdx.utils.Array;
import com.game.straferliberator.StraferLiberator;
import com.port.UI.menu.Tutorial;
import com.port.entity.item.npc.ExplozieSchrodingersCat;
import com.port.entity.item.player.Laser;
import com.port.entity.item.player.Sword;
import com.port.entity.mover.player.BasePlayer;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.Animation;
import com.port.utils.graphics.GifImage;
import com.port.world.Scroller;
import com.port.world.WorldData;

public class SchrodingersCat extends Goblin {

    Animation animation;  ///enter // exit // explode
    boolean changedAnimation;
    String animationName;

    boolean exploding;
    boolean inBox;
    boolean metPlayer;
    int inBoxTime;
    ExplozieSchrodingersCat explozie = new ExplozieSchrodingersCat();

    public SchrodingersCat(Scroller scrl, int x, int y) {
        super(scrl, x, y);

        
        directie.put("D", StraferLiberator.assetManager.get("images/npc/inamic/schrodingersCat/schrodingersCat_m.gif",GifImage.class)); 
        directie.put("W", StraferLiberator.assetManager.get("images/npc/inamic/schrodingersCat/schrodingersCat_m.gif",GifImage.class)); 
        directie.put("A", StraferLiberator.assetManager.get("images/npc/inamic/schrodingersCat/schrodingersCat_m.gif",GifImage.class)); 
        directie.put("S", StraferLiberator.assetManager.get("images/npc/inamic/schrodingersCat/schrodingersCat_m.gif",GifImage.class));
        directie.put("idle", StraferLiberator.assetManager.get("images/npc/inamic/schrodingersCat/schrodingersCat_m_Idle.gif",GifImage.class)); 
        
        
        changeAnimation("Exit", 10, 60);
        animation.setActiveState(false);
        changedAnimation = false;

        inBox = false;
        mort = false;
        metPlayer = false;
    }

    private void closeBox() {
        if (!inBox && metPlayer) {
            changeAnimation("Enter", 10, 5);
            inBox = true;
        }
    }

    private void openBox() {
        if (inBox && metPlayer) {
            int seed = Greenfoot.getRandomNumber(2);

            switch (seed) {
                case 0: {
                    explode();
                    break;
                }
                default: {
                    changeAnimation("Exit", 10, 5);
                    break;
                }
            }
            inBox = false;
        }

    }

    private void explode() {
        if (metPlayer) {
            changeAnimation("Explode", 16, 6);
            getWorld().addObject(explozie, this.getX(), this.getY());

        }
    }

    int cntAs = 0;//engings in animation

    private void checkAction() {
        if (!animation.isActive() && metPlayer) {
            changedAnimation = false;
            switch (animationName) {
                case "Explode": {
                    mort = true;

                    if (explozie != null) {
                        getWorld().removeObject(explozie);
                    }
                    getWorld().removeObject(this);
                    break;
                }
                case "Enter": {
                    break;
                }
                case "Exit": {
                    if (cntAs > 2) {
                        mort = true;
                    }
                    break;
                }
            }
        }
    }

    public void act() {

        if (WorldData.PAUZA == false && super.checkPlayerInChunck() == true) {
            gif = "W";
            if (!animation.isActive()) {

                if (!inBox) {
                    gif = "idle";
                } else {
                    gif = "W";
                }
            }

            if (mort == true) {

            } else {

                lovitSabie();
                lovitLaser();
                int waitseed = Greenfoot.getRandomNumber(2500);

                if (isTouching(BasePlayer.class)) {

                    timpAtins = 0;//{
                    inBoxTime = 0;
                    atingePlayer = true;//ataca

                    int waitOpen = waitseed / 10;
                    inBoxTime++;
                    if (inBoxTime >= waitOpen / 2) {
                        if (!changedAnimation) {
                            cntAs++;
                            openBox();
                            changedAnimation = true;
                        }
                        inBoxTime = 0;
                    }

                } else {

                    int waitMove = 60 + waitseed;//{
                    if (atingePlayer == true) {
                        timpAtins++;
                        if (timpAtins >= waitMove) {///////////////////////ia o pauza
                            atingePlayer = false;
                            if (!changedAnimation) {
                                cntAs++;
                                openBox();
                                changedAnimation = true;
                            }
                        }
                    }///////////////////////////////////////////////{
                    else {
                        //daca e in range sa nu l caute in toata lumea
                        List players = getWorld().getObjects(Player.class);
                        if (!players.isEmpty()) {
                            Player player = (Player) players.get(0);
                            int deltaPGX = player.getWorldX() - (this.worldX + Scroller.scrolledX);
                            if (deltaPGX < 0) {
                                deltaPGX *= (-1);
                            }
                            int deltaPGY = player.getWorldY() - (this.worldY + Scroller.scrolledY);
                            if (deltaPGY < 0) {
                                deltaPGY *= (-1);
                            }
                            if (deltaPGX <= WorldData.WIDTH*0.65f && deltaPGY <= WorldData.HEIGHT*0.65f) {
                                if (!WorldData.metSchrodingersCat &&WorldData.nrEvent<=22) {
                                    getWorld().addObject(new Tutorial("Combat", "SchrodingersCat", 3, false), WorldData.menuX, WorldData.menuY);
                                    WorldData.metSchrodingersCat = true;
                                }
                                metPlayer = true;
                                if (!changedAnimation) {
                                    cntAs++;
                                    closeBox();
                                    changedAnimation = true;
                                }

                                gaseste();//cauta playerul

                            }
                        }
                        int waitOpen = waitseed / 10;
                        inBoxTime++;
                        if (inBoxTime >= waitOpen) {
                            if (!changedAnimation) {
                                cntAs++;
                                openBox();
                                changedAnimation = true;
                            }
                            inBoxTime = 0;
                        }

                        int difpx = Scroller.scrolledX - prevsx;
                        int difpy = Scroller.scrolledY - prevsy;

                        worldX -= difpx;
                        worldY -= difpy;
                        prevsx = Scroller.scrolledX;
                        prevsy = Scroller.scrolledY;
                    }

                }

                this.knockbackMove();
            }
            checkAction();
            if (animation.isActive()) {
                animation.run();

            } else {

                npcImg = directie.get(gif);
                setImage(npcImg.getCurrentImage());
            }
        }
    }

    protected void lovitSabie() {
        super.lovitSabie(this.mass);
        if (isTouching(Sword.class)) {
            explode();

        }

    }

    protected void lovitLaser() {
        super.lovitLaser();
        Actor a = (Laser) getOneIntersectingObject(Laser.class);
        if (a != null) {
            explode();
        }
    }

    private void changeAnimation(String anim, int nrframeuri, int scalar) {

        animationName = anim;
        String animationToGet="images/npc/inamic/schrodingersCat/schrodingersCat_" + anim + ".gif";
        animation=StraferLiberator.assetManager.get(animationToGet,Animation.class);
        animation.setAnimated(this);
        animation.setCycleActs(nrframeuri);
        animation.setCycleCount(1);
        animation.setScalar(scalar);
        animation.run();
        animation.setActiveState(true);
    }

}
