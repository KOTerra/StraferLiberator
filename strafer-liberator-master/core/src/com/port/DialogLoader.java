package com.port;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class DialogLoader extends Loader{

    /*
     * The name of the directory where all dialogs are saved
     */
    private static String directoryName = "assets/dialogs";

    /*
     * This method processes the string and returns it's content
     *
     * @param String that needs to be processed
     * @return the content of the item
     * @throws Exception
     */
    private static List<String> getContentString(String str) throws Exception {
        List<String> rez = new ArrayList<>();
        String[] rep = str.split("#");
        for (String s : rep) {
            if(!s.isEmpty() )
            {
                rez.add(s);
            }

        }
        return rez;
    }

    /**
     *
     * @param NPCName name of the NPC whose dialog that we need
     * @param dialogNumber the number of the encounter
     * @return a list of strings with all the phrases spoken by this character
     */
    public static List<String> loadPhrases(String NPCName, int dialogNumber) {
        File director = new File(DialogLoader.directoryName, NPCName);
        File file = new File(director.getAbsoluteFile(), NPCName + dialogNumber + ".txt");

        List<String> phrases = new ArrayList<>();

        try {
            file.createNewFile();
            Scanner fin = new Scanner(file, "UTF-8");
            StringBuilder strB = new StringBuilder();
            while (fin.hasNext()) {
                String str = fin.nextLine();
                strB.append(str+"\n");
            }
            fin.close();
            phrases = getContentString(strB.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return phrases;
    }

}