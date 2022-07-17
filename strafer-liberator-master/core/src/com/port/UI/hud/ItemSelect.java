package com.port.UI.hud;


import greenfoot.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.game.straferliberator.StraferLiberator;
import com.port.UI.menu.Menu;
import com.port.entity.item.player.SabieHold;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.Picture;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

public class ItemSelect extends Menu {

	String path="images/UI/hud/wheel";

    private Player player;

    int x=WorldData.WIDTH-149, y= WorldData.HEIGHT-146;
    List<Actor> thingsToRemove=new ArrayList<>();
    boolean added=false;
    
    public ItemSelect(Player player) {
        this.player = player;
        setImage(StraferLiberator.assetManager.get("images/UI/hud/select.png",GreenfootImage.class));
        if(Gdx.app.getType().equals(ApplicationType.Android)) {
        	x=WorldData.WIDTH-299;
        	y=WorldData.HEIGHT-296;
        }
    }

    public String getItemSelected() {
        switch (Inventory.nrItem) {
            case 1: {
                return "sword";
            }
            case 2: {
                return "icelock";
            }
            case 3: {
                return "blackhole";
            }
            case 4: {
                return "lantern";
            }
            case 5: {
                return "laser";
            }
            case 6: {
                return "portalgun";
            }
        }
        return "";
    }

    private void select() {
        if (Greenfoot.mouseClicked(this)) {
            if (Greenfoot.getMouseInfo().getButton() == 1) {

                switch (getItemSelected()) {
                    case "sword": {
                        if (WorldData.hasSword) {
                            player.setEquipSword(!player.isEquipSword());
                            getWorld().addObject(new SabieHold(), 0, 0);
                        }
                        break;
                    }
                    case "icelock": {
                        if (WorldData.hasIceLock) {
                            player.setEquipIceLock(!player.isEquipIceLock());
                        }
                        break;
                    }
                    case "blackhole": {
                        if (WorldData.hasBlackHole) {
                            player.setEquipBlackHole(!player.isEquipBlackHole());
                        }
                        break;
                    }
                    case "lantern": {
                        if (WorldData.hasLantern) {
                            player.setEquipLantern(!player.isEquipLantern());
                        }
                        break;
                    }
                    case "laser": {
                        if (WorldData.hasLaser) {
                            player.setEquipLaser(!player.isEquipLaser());
                        }
                        break;
                    }
                    case "portalgun": {
                        if (WorldData.hasPortalGun) {
                            player.setEquipPortalGun(!player.isEquipPortalGun());
                        }
                        break;
                    }
                    default: {
                    }
                }
                ((PlayWorld)getWorld()).getTouchManager().getItemButton().changeItem();
                player.setToggledInventory(false);

            }
        }
    }
    
    private void makeOverlay() {
    	if(WorldData.hasSword) {
    		Picture p=new Picture(StraferLiberator.assetManager.get(path+"Sword.png",GreenfootImage.class));
    		
    		thingsToRemove.add(p);
    		getWorld().addObject(p, x, y);
    	}
    	if(WorldData.hasIceLock) {
    		Picture p=new Picture(StraferLiberator.assetManager.get(path+"IceLock.png",GreenfootImage.class));
    		thingsToRemove.add(p);
    		getWorld().addObject(p, x, y);
    	}
    	if(WorldData.hasBlackHole) {
    		Picture p=new Picture(StraferLiberator.assetManager.get(path+"BlackHole.png",GreenfootImage.class));
    		thingsToRemove.add(p);
    		getWorld().addObject(p, x, y);
    	}
    	if(WorldData.hasLantern) {
    		Picture p=new Picture(StraferLiberator.assetManager.get(path+"Lantern.png",GreenfootImage.class));
    		thingsToRemove.add(p);
    		getWorld().addObject(p, x, y);
    	}
    	if(WorldData.hasLaser) {
    		Picture p=new Picture(StraferLiberator.assetManager.get(path+"Laser.png",GreenfootImage.class));
    		thingsToRemove.add(p);
    		getWorld().addObject(p, x, y);
    	}
    	if(WorldData.hasPortalGun) {
    		Picture p=new Picture(StraferLiberator.assetManager.get(path+"PortalGun.png",GreenfootImage.class));
    		thingsToRemove.add(p);
    		getWorld().addObject(p, x, y);
    	}
    }

    public void act() {
    	if(!added) {
    		makeOverlay();
    		added=true;
    	}
        select();
        setLocation(x,y);
        if (!player.isToggledInventory()) {
        	getWorld().removeObjects(thingsToRemove);
            getWorld().removeObject(this);
        }

    }
}
