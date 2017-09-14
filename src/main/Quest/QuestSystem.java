package main.Quest;


import main.Quest.Types.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import java.util.ArrayList;


public class QuestSystem implements CommandExecutor{

    ArrayList<Quest> quests = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            //Erstelle eine Quest
            if(cmd.getName().equalsIgnoreCase("createQuest")){

                if(args.length == 3) {
                    String type = "";
                    if (args[0].equalsIgnoreCase("assasination")) {
                        type = "assasination";

                    } else if (args[0].equalsIgnoreCase("collection")) {
                        type = "collection";


                    } else if (args[0].equalsIgnoreCase("delivering")) {
                        type = "delivering";


                    } else if (args[0].equalsIgnoreCase("escort")) {
                        type = "escort";

                    } else if (args[0].equalsIgnoreCase("killing")) {
                        type = "killing";


                    } else {
                        p.sendMessage(args[0] + " ist nicht unterstützt oder falsch geschrieben!");
                        p.sendMessage("Erstellbare Quests: collection,delivering,escort,killing,assasination");
                    }
                    int lvl;
                    try {
                        lvl = Integer.parseInt(args[1]);
                    } catch (NumberFormatException nfe){
                        p.sendMessage(args[1] + " ist keine valide Nummer!");
                        return false;
                    }

                    String name = args[2];

                    switch (type){
                        case "assasination":
                            quests.add(new AssasinationQuest(quests.size()+1,lvl,name));
                            break;
                        case "collection":
                            quests.add(new CollectionQuest(quests.size()+1,lvl,name));
                            break;
                        case "delivering":
                            quests.add(new DeliveringQuest(quests.size()+1,lvl,name));
                            break;
                        case "escort":
                            quests.add(new EscortQuest(quests.size()+1,lvl,name));
                            break;
                        case "killing":
                            quests.add(new KillingQuest(quests.size()+1,lvl,name));
                            break;
                    }

                    p.sendMessage(quests.get(quests.size()-1).toString());

                    return true;
                } else{
                    p.sendMessage("Nicht genügend oder zuviele Argumente, benötigt wird: Quest-Type, Mindestlevel und QuestName");
                    return false;
                }

            }


            //Fügt er Quest eine Description zu
            if(cmd.getName().equalsIgnoreCase("addDescription")){
                if(args.length > 1) {
                    //erstes Argument ist die Quest ID
                    int questID;
                    try {
                        questID = Integer.parseInt(args[0]);
                    } catch (NumberFormatException nfe){
                        p.sendMessage(args[0] + " ist keine valide Questnummer!");
                        return false;
                    }
                    if(questID > quests.size()){
                        p.sendMessage(questID + " ist keine gültige Quest!");
                        return false;
                    }
                    //Alles danach ist die Description

                    StringBuilder description = new StringBuilder();
                    for (int i = 1; i < args.length;i++){
                        description.append(args[i]).append(" ");
                    }

                    Quest quest = quests.get(questID-1);
                    quest.setDescription(description.toString());
                    quests.set(questID-1,quest);

                    return true;
                } else {

                    return false;
                }


            }

            if(cmd.getName().equalsIgnoreCase("printQuest")){
                if(args.length == 1){
                    int questID;
                    try {
                        questID = Integer.parseInt(args[0]);
                    } catch (NumberFormatException nfe){
                        p.sendMessage(args[0] + " ist keine valide Questnummer!");
                        return false;
                    }
                    if(questID > quests.size()){
                        p.sendMessage(questID + " ist keine gültige Quest!");
                        return false;
                    }

                    p.sendMessage(quests.get(questID-1).toString());


                }
            }


            if(cmd.getName().equalsIgnoreCase("addTargetMission")){
                p.sendMessage("Not implemented yet");

            }

            if(cmd.getName().equalsIgnoreCase("editTargetMission")){
                p.sendMessage("Not implemented yet");

            }

            if(cmd.getName().equalsIgnoreCase("deleteTargetMission")){
                p.sendMessage("Not implemented yet");

            }

            //Spawnt einen Quest NPC
            if(cmd.getName().equalsIgnoreCase("spawnQuestNPC")){

                p.sendMessage("Not implemented yet");

            }

            //Quest Binding
            if(cmd.getName().equalsIgnoreCase("bindQuest")){
                p.sendMessage("Not implemented yet");



            }

        } else {
            System.out.println("Sie sind kein Spieler");
        }
        return false;
    }
}
