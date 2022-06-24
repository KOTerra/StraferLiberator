package com.port.entity.mover.npc.hostile;


import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.port.UI.menu.Tutorial;
import com.port.entity.item.npc.SabieGoblin;
import com.port.entity.item.player.BlackHole;
import com.port.entity.item.player.Laser;
import com.port.entity.item.player.Sabie;
import com.port.entity.mover.player.Jucator;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.Animation;
import com.port.utils.graphics.GifImage;
import com.port.world.Scroller;
import com.port.world.WorldData;

public class Goblin extends Inamic {

    public static int speed = 5;
    public static int mass = 80;
    int hp = 75;

    private long timpSab = 0;
    private long timpBolt = 0;

  
    Animation animation;
    boolean startedAnimation = false;
    int cntDeath = 0;

    public Goblin(Scroller scrl, int x, int y) {
        super(scrl, x, y);

        directie.put("D", new GifImage("npc/inamic/goblin/goblin_m_D.gif"));
        directie.put("W", new GifImage("npc/inamic/goblin/goblin_m_W.gif"));
        directie.put("A", new GifImage("npc/inamic/goblin/goblin_m_A.gif"));
        directie.put("S", new GifImage("npc/inamic/goblin/goblin_m_S.gif"));
        directie.put("idle", new GifImage("npc/inamic/goblin/goblin_m_Idle.gif"));

        changeAnimation();
        animation.setActiveState(false);
    }

    protected void atac() {
        if (!usedItem) {
            getWorld().addObject(new SabieGoblin(this), getX(), getY());
        }
        super.atac();
    }

    protected void lovitSabie() {
        super.lovitSabie(this.mass);
        if (isTouching(Sabie.class)) {
            timpSab++;
            if (timpSab >= 15) {
                takeDamage(Sabie.damage);
                timpSab = 0;
            }

        }

    }

    protected void lovitLaser() {
        super.lovitLaser();
        Actor a = (Laser) getOneIntersectingObject(Laser.class);
        if (a != null) {
            timpBolt++;//cat timp ating ating laserul
            if (timpBolt >= 5) {
                removeTouching(Laser.class);
                takeDamage(Laser.damage);
                timpBolt = 0;
            }
        }
    }
    private void suckedBlackHole(){
        if(isTouching(BlackHole.class)){
            takeDamage(10);
        }
    }
    private void takeDamage(int dmg) {
        hp -= dmg;
    }

    private void die() {
        if (hp <= 0) {
            mort = true;
        }
    }

    public void act() {

        if (WorldData.PAUZA == false && super.checkPlayerInChunck() == true&&!freeze) {

            gif = "idle";

            if (mort == true) {
                cntDeath++;
                if (cntDeath > 2) {
                    if (!startedAnimation) {
                        animation.setActiveState(true);
                        startedAnimation = true;
                    }
                }
                if (animation.isActive()) {
                    animation.run();
                }
                if (startedAnimation && !animation.isActive()) {
                    super.generateRandomHealthBoost();
                    getWorld().removeObject(this);
                }
            } else {

                lovitSabie();
                lovitLaser();
                suckedBlackHole();
                long waitseed = Greenfoot.getRandomNumber(2500);

                if (isTouching(Jucator.class)) {

                    timpAtins = 0;//{
                    atingePlayer = true;//ataca
                    atac();///////{trebuie dat overload la atac

                    gif = "idle";

                    npcImg = directie.get(super.gif);

                } else {

                    long wait = Greenfoot.getRandomNumber(20) + 30 + waitseed;//{
                    wait = 0;

                    if (atingePlayer == true) {
                        timpAtins++;
                        if (timpAtins >= wait) ///////////////////////ia o pauza
                        {
                            atingePlayer = false;
                            usedItem = false;

                        }
                    }///////////////////////////////////////////////{
                    else {
                        //daca e in range sa nu l caute in toata lumea
                        List players = getWorld().getObjects(Player.class);
                        Player player = (Player) players.get(0);
                        int deltaPGX = player.getWorldX() - (this.worldX + Scroller.scrolledX);
                        if (deltaPGX < 0) {
                            deltaPGX *= (-1);
                        }
                        int deltaPGY = player.getWorldY() - (this.worldY + Scroller.scrolledY);
                        if (deltaPGY < 0) {
                            deltaPGY *= (-1);
                        }
                        if (deltaPGX <= 600 && deltaPGY <= 400) {
                            if(!WorldData.metGoblin&&WorldData.nrEvent<=9){
                                getWorld().addObject(new Tutorial("Combat","Goblin",2,false), WorldData.menuX, WorldData.menuY);
                                WorldData.metGoblin=true;
                            }
                            gaseste();//cauta playerul
                        }
                        
                        int difpx = Scroller.scrolledX - prevsx;
                        int difpy = Scroller.scrolledY - prevsy;

                        worldX -= difpx;
                        worldY -= difpy;
                        prevsx = Scroller.scrolledX;
                        prevsy = Scroller.scrolledY;
                    }

                    this.knockbackMove();
                    die();
                }
            }
            if (animation.isActive()) {
                animation.run();
            } else {
                npcImg = directie.get(gif);
                setImage(npcImg.getCurrentImage());
            }
        }

    }

    private void changeAnimation() {
        java.util.List<GreenfootImage> imgs = new GifImage("npc/inamic/goblin/goblin_death.gif").getImages();
        GreenfootImage[] images = new GreenfootImage[imgs.size()];
        for (int i = 0; i < imgs.size(); i++) {
            images[i] = (GreenfootImage) imgs.get(i);
        }
        animation = new Animation(this, images);
        animation.setCycleActs(22);
        animation.setCycleCount(1);
        animation.setScalar(5);
        animation.run();
        animation.setActiveState(true);
    }
 
 public boolean isFreeze() {
        return freeze;
    }

    public void setFreeze(boolean freeze) {
        this.freeze = freeze;
    }
}
