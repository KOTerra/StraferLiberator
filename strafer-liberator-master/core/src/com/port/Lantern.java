package com.port;


import greenfoot.*;

public class Lantern extends Item {

    GifImage gif = new GifImage("item/lantern.gif");
    int speed = 5;
    boolean isLight = false;

    int timer = 0;
    
    private Player player;
    public Lantern(Player player)
    {
        this.player=player;
    }
    
    public void checkMove() {
        timer++;
        if (Greenfoot.isKeyDown("Right")) {
            setLocation(getX() + speed, getY());
        }
        if (Greenfoot.isKeyDown("Left")) {
            setLocation(getX() - speed, getY());
        }
        if (Greenfoot.isKeyDown("Up")) {
            setLocation(getX(), getY() - speed);
        }
        if (Greenfoot.isKeyDown("Down")) {
            setLocation(getX(), getY() + speed);
        }
        if (timer >= 20) {
            if (Greenfoot.mouseClicked(null)) {
                if (Greenfoot.getMouseInfo().getButton() == 1) {
                    if (getWorld().getObjects(Light.class).isEmpty()) {
                        getWorld().addObject(new Light(this), this.getX(), this.getY());
                        isLight = true;
                    } else {
                        getWorld().removeObject((Actor) getWorld().getObjects(Light.class).get(0));
                        isLight = false;
                    }

                    timer = 0;
                }
            }
            if (Greenfoot.mouseClicked(null)) {
                if (Greenfoot.getMouseInfo().getButton() == 3) {
                    getWorld().removeObjects(getWorld().getObjects(Light.class));
                    getWorld().removeObject(this);
                    
                    
                    player.setEquipLantern (false);
                    return;
                }
            }

        }

    }
    
    public void act() {
        if (!WorldData.PAUZA) {

            setImage(gif.getCurrentImage());

            if (isAtEdge()) {
                getWorld().removeObjects(getWorld().getObjects(Light.class));
                getWorld().removeObject(this);
                
                
                player.setEquipLantern ( false);
                return;
            }
            checkMove();
            
        }

    }
    
     public boolean isIsLight() {
        return isLight;
    }

    public void setIsLight(boolean isLight) {
        this.isLight = isLight;
    }
}
