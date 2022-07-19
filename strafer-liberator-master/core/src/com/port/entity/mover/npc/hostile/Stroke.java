package com.port.entity.mover.npc.hostile;


import greenfoot.*;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.game.straferliberator.StraferLiberator;
import com.port.UI.hud.HealthBar;
import com.port.UI.hud.HealthBarImg;
import com.port.UI.menu.Tutorial;
import com.port.entity.item.npc.LaserStroke;
import com.port.entity.item.npc.RumbleStroke;
import com.port.entity.item.player.Laser;
import com.port.entity.item.player.Sword;
import com.port.entity.mover.player.BasePlayer;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.Animation;
import com.port.utils.graphics.AnimationRunner;
import com.port.utils.graphics.GifImage;
import com.port.world.PlayWorld;
import com.port.world.Scroller;
import com.port.world.WorldData;

public class Stroke extends HostileNpc {

    Player player;
    int ochix, ochiy;

    public static int speed = 6;

    int hp = 1000;
    int hpMax = 1000;

    public HealthBar healthBar;
    HealthBarImg healthBarImg=new HealthBarImg("Stroke");
    private Color cSQ = Color.FOREST;
    private boolean addedHealthBar = false;

    private long timpLaser = 0;

    Animation animation;
    private AnimationRunner animationRunner;
    boolean startedAnimation = false;
    int cntDeath = 0;

    public Stroke(Scroller scrl, int x, int y, Player player) {
        super(scrl, x, y);
        this.player = player;

        
        directie.put("D", StraferLiberator.assetManager.get("images/npc/inamic/stroke/stroke_m.gif",GifImage.class));
        directie.put("W", StraferLiberator.assetManager.get("images/npc/inamic/stroke/stroke_m.gif",GifImage.class));
        directie.put("A", StraferLiberator.assetManager.get("images/npc/inamic/stroke/stroke_m.gif",GifImage.class));
        directie.put("S", StraferLiberator.assetManager.get("images/npc/inamic/stroke/stroke_m.gif",GifImage.class));
        directie.put("idle", StraferLiberator.assetManager.get("images/npc/inamic/stroke/stroke_idle.gif",GifImage.class));

        prepareHealthBar();

        changeAnimation();
        animationRunner.setActiveState(false);

    }

    LaserStroke laserStroke;

    protected void addLasers() {
        ochix = (int) (getX() - 50);
        ochiy = (int) (getY() - 35);
        laserStroke = new LaserStroke(this, 100);
        getWorld().addObject(laserStroke, WorldData.menuX-250, WorldData.menuY+100);

        super.atac();
    }

    protected void atacLaser() {

        addLasers();

    }

    protected void atacMelee() {

        super.atac();
        if (getWorld().getObjects(LaserStroke.class).isEmpty()) {
            getWorld().addObject(new RumbleStroke(this), getX(), getY());
        }
    }

    protected void lovitSabie() {
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

    private void takeDamage(int dmg) {
        hp -= dmg;
        healthBar.setValue(this.hp);
    }

    private void die() {
        if (hp <= 0) {
            mort = true;
        }
    }
boolean enter=false;

    public void act() {

        if (WorldData.PAUZA == false && super.checkPlayerInChunck() == true) {

            gif = "idle";

            if (mort == true) {
                cntDeath++;
                if (cntDeath > 2) {
                    if (!startedAnimation) {
                        animationRunner.setActiveState(true);
                        startedAnimation = true;
                    }
                }
                if (animationRunner.isActive()) {
                    animationRunner.run();
                }
                if (startedAnimation && !animationRunner.isActive()) {
                    PlayWorld playWorld=(PlayWorld)getWorld();
                    playWorld.initObject(new Tutorial("Cutscene", "theend", 1, false), WorldData.menuX, WorldData.menuY);
                    getWorld().removeObjects(getWorld().getObjects(LaserStroke.class));
                    getWorld().removeObject(healthBar);
                    getWorld().removeObject(healthBarImg);
                    getWorld().removeObject(this);
                }
            } else {

                lovitSabie();
                lovitLaser();
                long waitseed = Greenfoot.getRandomNumber(2500);

                if (isTouching(BasePlayer.class)) {

                    timpAtins = 0;//{
                    atingePlayer = true;//ataca
                    atacMelee();///////{trebuie dat overload la atac

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
                            //randomize

                        }
                    }///////////////////////////////////////////////{
                    else {
                        //daca e in range sa nu l caute in toata lumea

                        int deltaPGX = player.getWorldX() - (this.worldX + Scroller.scrolledX);
                        if (deltaPGX < 0) {
                            deltaPGX *= (-1);
                        }
                        int deltaPGY = player.getWorldY() - (this.worldY + Scroller.scrolledY);
                        if (deltaPGY < 0) {
                            deltaPGY *= (-1);
                        }
                        if (deltaPGX <= WorldData.WIDTH*0.75f && deltaPGY <= WorldData.HEIGHT*0.75f) {//e in range
                            if(!enter){
                               ((PlayWorld)getWorld()).initObject(new Tutorial("Cutscene", "bossfight", 1, false), WorldData.menuX, WorldData.menuY);
                                enter=true;
                            }
                            
                            atac();//cauta playerul
                            if (!addedHealthBar) {
                                getWorld().addObject(healthBarImg, WorldData.menuX, WorldData.HEIGHT-36);
                                getWorld().addObject(healthBar, WorldData.menuX + 40, WorldData.HEIGHT-32);
                                healthBar.setValue(this.hp); 
                               
                                
                                addedHealthBar = true;
                            }
                        } else {
                            if (addedHealthBar) {
                                addedHealthBar = false;
                                getWorld().removeObject(healthBar);
                                getWorld().removeObject(healthBarImg);
                            }
                        }
                        int difpx = Scroller.scrolledX - prevsx;
                        int difpy = Scroller.scrolledY - prevsy;

                        worldX -= difpx;
                        worldY -= difpy;
                        prevsx = Scroller.scrolledX;
                        prevsy = Scroller.scrolledY;
                    }

                    die();
                }

            }
            if (animationRunner.isActive()) {
                animationRunner.run();
            } else {
                npcImg = directie.get(gif);
                setImage(npcImg.getCurrentImage());
            }
        }

    }

    protected void atac() {

        if (hp % 3 == 0) {
            //atac cu lee
            getWorld().removeObjects(getWorld().getObjects(LaserStroke.class));
            gaseste();
        } else {

            if (timpLaser > 20) {
                super.gif = "idle";
                npcImg = directie.get(super.gif);
                getWorld().removeObjects(getWorld().getObjects(LaserStroke.class));
                atacLaser();

                timpLaser = 0;

            }
            timpLaser++;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public int getOchix() {
        return ochix;
    }

    public int getOchiy() {
        return ochiy;
    }

    private void prepareHealthBar() {
        addedHealthBar = false;
        healthBar = new HealthBar("", "", this.hpMax, this.hpMax);
        healthBar.setSafeColor(cSQ);
        healthBar.setDangerColor(Color.NAVY.lerp(Color.GOLD, 0.6f));
        healthBar.setBarWidth(567);
        healthBar.setBarHeight(8);
        healthBar.setTextColor(Color.LIGHT_GRAY.lerp(Color.SKY, 0.3f));
       
        
    }

    private void changeAnimation() {
    	animation=StraferLiberator.assetManager.get("images/npc/inamic/stroke/stroke_death.gif",Animation.class);
    	animationRunner = new AnimationRunner(this, animation);
		animationRunner.run();
		animationRunner.setActiveState(true);
    }

}
