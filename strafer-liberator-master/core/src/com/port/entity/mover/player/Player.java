package com.port.entity.mover.player;


import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.port.UI.hud.HealthBarPlayer;
import com.port.UI.hud.Inventory;
import com.port.UI.hud.ItemSelect;
import com.port.UI.menu.GameOver;
import com.port.UI.menu.Pause;
import com.port.entity.item.Item;
import com.port.entity.item.player.BlackHole;
import com.port.entity.item.player.IceLock;
import com.port.entity.item.player.Lantern;
import com.port.entity.item.player.Laser;
import com.port.entity.item.player.LaserHold;
import com.port.entity.item.player.PortalGun;
import com.port.entity.item.player.Sabie;
import com.port.entity.item.player.SabieHold;
import com.port.entity.mover.npc.hostile.Inamic;
import com.port.system.SaveSystem;
import com.port.utils.graphics.Animation;
import com.port.utils.graphics.GifImage;
import com.port.world.PlayWorld;
import com.port.world.Scroller;
import com.port.world.WorldData;

import java.lang.Math;

public class Player extends Jucator {

    HashMap<String, GifImage> directie = new HashMap<String, GifImage>();
    GifImage playerImg = directie.get(this.gif);

    public String gif = "Ds";

    int direction;

    PlayWorld playWorld;

    private int worldX, worldY;
    public static int floorLevel = 1;

    private boolean inViata = true;
    private int hp = 400;
    public static final int hpMax = 400;
    private int speed = 7;

    private boolean equipSword = false;
    private boolean equipLaser = false;
    private boolean equipPortalGun = false;
    private boolean equipIceLock = false;
    private boolean equipLantern = false;
    private boolean equipBlackHole = false;

    private boolean toggledInventory = false;
    public static boolean toggledPause = false;
    private boolean toggledGameOver = false;

    private boolean loaded = false;

    Animation animation;
    boolean playedAnimation;

    private HashSet<String> iteme = new HashSet<String>();

    protected long timpPrec;
    private long timp = 0;
    private long timpSab = 0, timpLaser = 0, timpBoaba = 0, timpPumni = 0;

    private boolean apas;

    public Player() {

        prepareData();

        playWorld = (PlayWorld) getWorld();

        //sta
        directie.put("idle", new GifImage("player/player_m_Idle.gif"));

        //merge
        directie.put("D", new GifImage("player/player_m_D.gif"));
        directie.put("W", new GifImage("player/player_m_W.gif"));
        directie.put("A", new GifImage("player/player_m_A.gif"));
        directie.put("S", new GifImage("player/player_m_S.gif"));

        //se uita
        directie.put("Ds", new GifImage("player/vedere_D.gif"));
        directie.put("Ws", new GifImage("player/vedere_W.gif"));
        directie.put("As", new GifImage("player/vedere_A.gif"));
        directie.put("Ss", new GifImage("player/vedere_S.gif"));

        this.timpPrec = System.currentTimeMillis();
    }

    private void prepareData() {
        //date inititale
        equipSword = false;
        equipLaser = false;
        equipPortalGun = false;
        equipIceLock = false;
        equipLantern = false;
        equipBlackHole = false;

        toggledInventory = false;
        toggledPause = false;
        toggledGameOver = false;

        loaded = false;

        prepareAnimation();

        WorldData.PAUZA = false;

        this.floorLevel = 1;
    }

    public void load() {
        //incarca salvarea
        if (!loaded) {
            SaveSystem.load(WorldData.saveFileNumber, this);
            loaded = true;
        }

    }

    protected void checkMove() { //verifica tastele pentru mers
        apas = false;

      
        if (Greenfoot.isKeyDown("W")) {
            //merge in nord 
            apas = true;
            gif = "W";
            Item.itemGif = "W";
            setLocation(getX(), getY() - speed);

        }

        if (Greenfoot.isKeyDown("S")) {
            //merge in sud   
            apas = true;
            gif = "S";
            Item.itemGif = "S";
            setLocation(getX(), getY() + speed);

        }
        if (Greenfoot.isKeyDown("D")) {
            //merg in est
            apas = true;
            gif = "D";
            Item.itemGif = "D";
            setLocation(getX() + speed, getY());

        }

        if (Greenfoot.isKeyDown("A")) {
            //merg in vest
            apas = true;
            gif = "A";
            Item.itemGif = "A";
            setLocation(getX() - speed, getY());

        }
        if (apas == false) {
            WorldData.isWalking = true;
            if (equipSword == false && equipLaser == false && equipPortalGun == false && equipIceLock == false && equipLantern == false && equipBlackHole == false) {
                gif = "idle";

            }

            switch (gif) {
                case "W": {
                    gif = "Ws";
                    Item.itemGif = "W";
                    break;
                }

                case "A": {
                    gif = "As";
                    Item.itemGif = "A";
                    break;
                }

                case "S": {
                    gif = "Ss";
                    Item.itemGif = "S";
                    break;
                }
                case "D": {
                    gif = "Ds";
                    Item.itemGif = "D";
                    break;
                }

                case "Ws": {
                    Item.itemGif = "W";
                    break;
                }
                case "As": {
                    Item.itemGif = "A";
                    break;
                }
                case "Ss": {
                    Item.itemGif = "S";
                    break;
                }
                case "Ds": {
                    Item.itemGif = "D";
                    break;
                }
                default: {
                    Item.itemGif = "S";
                }

            }

        } else {
            WorldData.isWalking = false;
        }

    }

    public void move() {//se misca daca merge

        if (gif != "idle") {
            super.atingeNpc();
        }
        sprint();
        checkMove();
        worldX = (int) (getX() + Scroller.scrolledX);
        worldY = (int) (getY() + Scroller.scrolledY);

        playerImg = directie.get(this.gif);
    }
    
    private void sprint() {
    	  if (Greenfoot.isKeyDown("L-Shift")) {
          	speed=14;
          }
          else {
          	speed=7;
          }
    }
    
    protected void vedere() {
        playerImg = directie.get(this.gif);
    }

    protected void toggleMenu() {// deschide inventoryul

        if (!toggledInventory) {

            if (Greenfoot.isKeyDown("E")) {
                toggledInventory = !toggledInventory;
                getWorld().addObject(new Inventory(this), WorldData.WIDTH-150, WorldData.HEIGHT-150);

                getWorld().addObject(new ItemSelect(this), WorldData.WIDTH-150, WorldData.HEIGHT-150);
            }

        }

    }

    protected void useItem() {      //foloseste itemele equipate

        //portalGun
        if (equipPortalGun) {
            long timpCurent = System.currentTimeMillis();
            if (timpCurent - timpPrec >= 20) {
                if (getWorld().getObjects(PortalGun.class).isEmpty()) {
                    getWorld().addObject(new PortalGun(), getX(), getY());
                }
                timpPrec = timpCurent;
            }
            Item.itemGif = gif;

            if (Greenfoot.mouseClicked(null)) {
                if (Greenfoot.getMouseInfo().getButton() == 3) {
                    equipPortalGun = false;
                }
            }
        }
        //portalGun

        //sabie
        if (equipSword) {

            if (getWorld().getObjects(Sabie.class).isEmpty()) {
                getWorld().addObject(new SabieHold(), getX(), getY());
            }

            if (Greenfoot.mouseClicked(null)) {
                if (Greenfoot.getMouseInfo().getButton() == 1) {  //right 3 left 1

                    long timpCurent = System.currentTimeMillis();
                    if (timpCurent - timpPrec >= 20) {
                        if (getWorld().getObjects(Sabie.class).isEmpty()) {

                            getWorld().addObject(new Sabie(this), getX(), getY());
                        }
                        timpPrec = timpCurent;
                    }
                    Item.itemGif = gif;
                }
            }
            if (Greenfoot.mouseClicked(null)) {
                if (Greenfoot.getMouseInfo().getButton() == 3) {
                    equipSword = false;
                }
            }
        }
        //sabie

        //laser
        if (equipLaser) {

            if (getWorld().getObjects(Sabie.class).isEmpty()) {
                getWorld().addObject(new LaserHold(), getX(), getY());
            }

            long timpCurent = System.currentTimeMillis();
            if (Greenfoot.mouseClicked(null)) {
                if (Greenfoot.getMouseInfo().getButton() == 1) {
                    if (timpCurent - timpPrec >= 20) {

                        if (getWorld().getObjects(PortalGun.class).isEmpty()) {

                            MouseInfo mouseul = Greenfoot.getMouseInfo();
                            if (mouseul != null) {

                                double delta_x = Greenfoot.getMouseInfo().getX() - getX();
                                double delta_y = Greenfoot.getMouseInfo().getY() - getY();
                                double grade = Math.toDegrees(Math.atan2(delta_y, delta_x));

                                int gr = (int) grade;
                                gr -= gr % 10;
                                gr += (Greenfoot.getRandomNumber(2) - 1) * 5;  //reduce din precizie
                                grade = gr;

                                if (grade >= -45 && grade < 45) {       //se intoarce playerul in dir in care trage 

                                    this.gif = "D";
                                }
                                if (grade >= 45 && grade < 135) {

                                    this.gif = "S";

                                }
                                if (grade >= 135 && grade < 225) {

                                    this.gif = "A";

                                }
                                if (grade >= -135 && grade < -45) {

                                    this.gif = "W";
                                }
                                vedere();

                                getWorld().addObject(new Laser(grade), getX(), getY());

                            }
                            timpPrec = timpCurent;
                        }

                    }
                    Item.itemGif = gif;
                }
            }
            if (Greenfoot.mouseClicked(null)) {
                if (Greenfoot.getMouseInfo().getButton() == 3) {
                    equipLaser = false;
                }
            }
        }
        //laser

        //lantern
        if (equipLantern) {
            long timpCurent = System.currentTimeMillis();
            if (timpCurent - timpPrec >= 20) {
                if (getWorld().getObjects(Lantern.class).isEmpty()) {
                    getWorld().addObject(new Lantern(this), getX() + 30, getY());
                }
                timpPrec = timpCurent;
            }

            if (Greenfoot.mouseClicked(null)) {
                if (Greenfoot.getMouseInfo().getButton() == 3) {
                    equipLantern = false;
                }
            }
        }
        //lantern

        //icelock
        if (equipIceLock) {
            long timpCurent = System.currentTimeMillis();
            if (timpCurent - timpPrec >= 20) {
                if (getWorld().getObjects(IceLock.class).isEmpty()) {
                    getWorld().addObject(new IceLock(), getX(), getY());
                }
                timpPrec = timpCurent;
            }
            Item.itemGif = gif;

            if (Greenfoot.mouseClicked(null)) {
                if (Greenfoot.getMouseInfo().getButton() == 3) {
                    equipIceLock = false;
                }
            }
        }
        //icelock

        //blackHole
        if (equipBlackHole) {

            long timpCurent = System.currentTimeMillis();
            if (timpCurent - timpPrec >= 20) {

                if (getWorld().getObjects(BlackHole.class).isEmpty()) {
                    getWorld().addObject(new BlackHole(), getX()+150, getY());
                }

                timpPrec = timpCurent;
            }

            if (Greenfoot.mouseClicked(null)) {
                if (Greenfoot.getMouseInfo().getButton() == 3) {
                    equipBlackHole = false;
                }
            }
        }
        //blackhole

    }

    protected void checkPauza() {

        if (!toggledPause && !WorldData.PAUZA) {

            if (Greenfoot.isKeyDown("Escape")) {
                toggledPause = !toggledPause;
                WorldData.PAUZA = true;

                getWorld().removeObjects(getWorld().getObjects(Inventory.class));
                getWorld().removeObjects(getWorld().getObjects(ItemSelect.class));
                toggledInventory = false;

                Pause pause = new Pause();
                getWorld().addObject(pause, WorldData.menuX, WorldData.menuY);

            }

        }

    }

    long cntF = 0;

    public void lookForEnemies() {
        if (!getObjectsInRange(WorldData.WIDTH+200, Inamic.class).isEmpty()) {
            WorldData.isFighting = true;
        } else {
            if (WorldData.isFighting) {
                cntF++;
                if (cntF > 2000) {
                    WorldData.isFighting = false;
                    cntF = 0;
                }
            }
        }
    }

 

	public void takeDamage(int dmg) {// cand e atact pierde viata
        hp -= dmg;
        getHealthBar().subtract(dmg);
    }

    boolean firstCycle = false;
    public void die() {
        if (hp >= hpMax) {//sa nu aiba mai multa
            hp = hpMax;
            getHealthBar().setValue(getHealthBar().getMaximumValue());
        }
        if (hp <= 0) {
            getHealthBar().setValue(getHealthBar().getMinimumValue());
            inViata = false;
        }

    }

    public void revive() {      //e inviat la load sau dupa game over
        this.toggledGameOver = false;
        playedAnimation = false;
        prepareAnimation();
        getHealthBar().setValue(getHealthBar().getMaximumValue());
        this.inViata = true;
        hp = hpMax;

    }

    public void act() {

        checkPauza();

        if (!WorldData.PAUZA) {
            load();
        }

        if (inViata) {
            if (!WorldData.PAUZA) {
                lookForEnemies();
                useItem();
                move();
                super.knockbackMove();
                die();

                toggleMenu();

                setImage(playerImg.getCurrentImage());

            }
        } else {

            if (!playedAnimation) {
                if (animation.isActive()) {
                    animation.run();
                }
            }
            if (!animation.isActive()) {
                playedAnimation = true;
                if (!toggledGameOver && playedAnimation) {                                              //animatia cand moare
                    getWorld().addObject(new GameOver(playWorld), WorldData.menuX, WorldData.menuY);
                    playedAnimation = false;
                    toggledGameOver = true;

                }
            }
        }

    }

    public int getExitDirection() {  //directia pe care iese din worldSection

        if (getX() >= WorldData.WIDTH-5) {
            direction = 3;
        }
        if (getX() <= 5) {
            direction = 1;
        }
        if (getY() >= WorldData.HEIGHT-5) {
            direction = 2;
        }
        if (getY() <= 5) {
            direction = 0;
        }

        return direction;
    }

    
    
    //gettere settere
    public PlayWorld getPlayWorld() {
        return playWorld;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldX(int val) {
        worldX = val;
    }

    public void setWorldY(int val) {
        worldY = val;
    }

    public HealthBarPlayer getHealthBar() {
        return (HealthBarPlayer) (getWorld().getObjects(HealthBarPlayer.class).get(0));
    }

    public boolean isInViata() {
        return inViata;
    }

    public void setInViata(boolean inViata) {
        this.inViata = inViata;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public HashSet<String> getIteme() {
        return iteme;
    }

    public boolean isEquipSword() {
        return equipSword;
    }

    public void setEquipSword(boolean equipSword) {
        this.equipSword = equipSword;
    }

    public boolean isEquipLaser() {
        return equipLaser;
    }

    public void setEquipLaser(boolean equipLaser) {
        this.equipLaser = equipLaser;
    }

    public boolean isEquipPortalGun() {
        return equipPortalGun;
    }

    public void setEquipPortalGun(boolean equipPortalGun) {
        this.equipPortalGun = equipPortalGun;
    }

    public boolean isEquipIceLock() {
        return equipIceLock;
    }

    public void setEquipIceLock(boolean equipIceLock) {
        this.equipIceLock = equipIceLock;
    }

    public boolean isEquipLantern() {
        return equipLantern;
    }

    public void setEquipLantern(boolean equipLantern) {
        this.equipLantern = equipLantern;
    }

    public boolean isEquipBlackHole() {
        return equipBlackHole;
    }

    public void setEquipBlackHole(boolean equipBlackHole) {
        this.equipBlackHole = equipBlackHole;
    }

    public boolean isToggledInventory() {
        return toggledInventory;
    }

    public void setToggledInventory(boolean toggledInventory) {
        this.toggledInventory = toggledInventory;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public int getHpMax() {
        return hpMax;
    }

    private void prepareAnimation() {       //pregateste animatia pt moarte
        java.util.List<GreenfootImage> imgs = new GifImage("player/player_death.gif").getImages();
        GreenfootImage[] images = new GreenfootImage[imgs.size()];
        for (int i = 0; i < imgs.size(); i++) {
            images[i] = (GreenfootImage) imgs.get(i);
        }
        playedAnimation = false;
        animation = new Animation(this, images);
        animation.setCycleActs(15);
        animation.setCycleCount(1);
        animation.setScalar(5);
        animation.run();
        animation.setActiveState(true);
    }

}
