package com.port.system.event;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.port.world.PlayWorld;


/**
 * This class contains all the quests relevant for the game
 * 
 * @author razva
 *
 */
public class MainStoryline extends Storyline{
    /**
     * The event queue represents all the quests that need to be completed to
     * finalize this storyline
     */
    Queue<Quest> questQueue = new LinkedList<>();

    public MainStoryline() {
        try (Stream<Path> walk = Files.walk(Paths.get("quests/mainQuest"))) {
            // We want to find only regular files
            List<Object> result = walk.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());

            for (Object string : result) {
                Quest toAdd = new Quest();
                toAdd.load(new File((String) string));
                //System.out.println(toAdd.questName);
                questQueue.add(toAdd);
            }
            result.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method checks whether the event is relevant
     * This method should be called by an actor that is in the world so that a reference to the world can be passed
     */
    public void isRelevantEvent(Event event,PlayWorld world) {
        if (questQueue.peek().isRelevantEvent(event,world)) {
            if (questQueue.peek().isFinished()) {
                questQueue.poll();
            }
        }
    }

    public void act() {
        //aici vor venii diferite eventuri
        Quest questCurent=questQueue.peek();
        
        String currentToDo=questCurent.nextToDo();
    }
}
