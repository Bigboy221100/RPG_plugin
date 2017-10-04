package main.Quest;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.Quest.Types.Quest;

import java.io.*;
import java.util.ArrayList;

public class QuestJSON {


    public static ArrayList<Quest> load(){
        Gson gson = new Gson();
        try(Reader reader = new FileReader("plugins/RPG/Quests/Quests.json")) {
            return gson.fromJson(reader,new TypeToken<ArrayList<Quest>>(){}.getType());
        } catch (FileNotFoundException e) {
            System.out.println(".json File not Found!");
        } catch (IOException e) {
            System.out.println("Irgendwas ist schiefgelaufen und es war nicht meine Schuld!");
        }
        return null;
    }

    public static void writeJSON(){
        Gson gson = new Gson();
        try(FileWriter writer = new FileWriter("plugins/RPG/Quests/Quests.json")){
            gson.toJson(QuestSystem.quests,writer);
        } catch (IOException e) {
            System.out.println("Irgendwas ist schiefgelaufen und es war nicht meine Schuld!");
        }
    }



}
