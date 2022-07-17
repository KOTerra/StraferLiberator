package com.port.entity.mover.npc.hostile;


import greenfoot.*;  

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.utils.Array;
import com.game.straferliberator.StraferLiberator;
import com.port.UI.menu.Tutorial;
import com.port.entity.item.npc.SabieGoblin;
import com.port.entity.item.player.BlackHole;
import com.port.entity.item.player.Laser;
import com.port.entity.item.player.Sword;
import com.port.entity.mover.player.BasePlayer;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.Animation;
import com.port.utils.graphics.GifImage;
import com.port.world.Scroller;
import com.port.world.WorldData;

public class Goblin extends HostileNpc {

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

        
        directie.put("W", StraferLiberator.assetManager.get("images/npc/inamic/goblin/goblin_m_W.gif",GifImage.class));
        directie.put("A", StraferLiberator.assetManager.get("images/npc/inamic/goblin/goblin_m_A.gif",GifImage.class));
        directie.put("S", StraferLiberator.assetManager.get("images/npc/inamic/goblin/goblin_m_S.gif",GifImage.class));
        directie.put("D", StraferLiberator.assetManager.get("images/npc/inamic/goblin/goblin_m_D.gif",GifImage.class));
        directie.put("idle", StraferLiberator.assetManager.get("images/npc/inamic/goblin/goblin_m_Idle.gif",GifImage.class));

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
        if (isTouching(Sword.class)) {
            timpSab++;
            if (timpSab >= 15) {
                takeDamage(Sword.damage);
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

                if (isTouching(BasePlayer.class)) {

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
                        if (deltaPGX <= WorldData.WIDTH*0.65f && deltaPGY <= WorldData.HEIGHT*0.65f) {
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
    	
    	animation=StraferLiberator.assetManager.get("images/npc/inamic/goblin/goblin_death.gif",Animation.class);
		animation.setAnimated(this);
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
