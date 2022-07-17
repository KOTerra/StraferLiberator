package com.port.UI.menu;


import greenfoot.*;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.game.straferliberator.StraferLiberator;
import com.port.UI.button.Button;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.Picture;
import com.port.utils.graphics.Text;
import com.port.world.PlayWorld;
import com.port.world.WorldData;

public class MapMenu extends Pause {

    GreenfootImage img =StraferLiberator.assetManager.get("images/UI/mapMenu/mapMenu.png",GreenfootImage.class);
    boolean butoanead = false;
    PlayWorld playWorld;
    Player player;

    Picture markerPlayer = new Picture(StraferLiberator.assetManager.get("images/UI/mapMenu/marker.png",GreenfootImage.class));
    Picture markerObjective = new Picture(StraferLiberator.assetManager.get("images/UI/mapMenu/markerObjective.png",GreenfootImage.class));
    Text objectiveText;

    ArrayList<Actor> thingsToClear = new ArrayList<Actor>();

    int distx=(int) (WorldData.WIDTH/2-1024/2);
    int disty=(int) (WorldData.HEIGHT/2-576/2);
    
    ///worldsize/mapmenu size=33.3 (3% zoom)
    public MapMenu(PlayWorld playWorldref) {
        setImage(img);
        playWorld = playWorldref;
        player = playWorld.getPlayer();

    }

    private void addButoane() {
        Button buton = new Button("X", this);
        playWorld.addObject(buton, distx+1000, disty+20);
    }

    private void addMarkers() {
        int ws = playWorld.getWorldListener().getWorldSection();
        
        playWorld.addObject(markerPlayer,distx+ getMapMenuCoordinateX(ws), disty+getMapMenuCoordinateY(ws) - 20);//adauga marker acolo unde e playerul pe mapa
        thingsToClear.add(markerPlayer);

        playWorld.addObject(markerObjective, distx+ getMapMenuObjectiveX(WorldData.objectiveWS), disty+getMapMenuObjectiveY(WorldData.objectiveWS) - 20); //adauga marker acolo unde e obiectivul pe map
        thingsToClear.add(markerObjective);

    }

    private void addText() {

        objectiveText = new Text("\n"+WorldData.objective, 30);
        playWorld.addObject(objectiveText,distx+810,disty+130);
        thingsToClear.add(objectiveText);

    }

    private void addMapSections() {
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 3; j++) {
                int ws = i * 10 + j;
                if (WorldData.visitedWorldSections[i][j] == true) {
                    Picture pic = new Picture(StraferLiberator.assetManager.get("images/UI/mapMenu/mapMenu" + ws + ".png",GreenfootImage.class));
                    playWorld.addObject(pic,distx+ 247 * j - 84, disty+247 * i - 84);
                    thingsToClear.add(pic);
                }
            }
        }
        //playWorld.addObject(new Picture("UI/mapMenu/mapMenu"+11+".png"),246*3-122+39,246*2-122+39);
    }

    public void clear() {
        playWorld.removeObjects(thingsToClear);
    }

    public void act() {
        if (!butoanead) {
            addButoane();
            addMapSections();
            addMarkers();
            addText();
            butoanead = true;
        }
    }

    private int getMapMenuCoordinateX(int ws) {   //sectiunea si dist globala pe sectiunea aia
        int dist = 0;
        dist += (8196 * (ws % 10 - 1)) + player.getX() + playWorld.getScroller().getScrolledX();

        return dist / 33 + 39;
    }

    private int getMapMenuCoordinateY(int ws) {
        int dist = 0;
        dist += (8196 * (ws / 10 - 1)) + player.getY() + playWorld.getScroller().getScrolledY();

        return dist / 33 + 39;
    }

    private int getMapMenuObjectiveX(int ws) {
        int dist = 0;
        dist += (8196 * (ws % 10 - 1)) + WorldData.objectiveX;

        return dist / 33 + 39;
    }

    private int getMapMenuObjectiveY(int ws) {
        int dist = 0;
        dist += (8196 * (ws / 10 - 1)) + WorldData.objectiveY;

        return dist / 33 + 39;
    }
}
