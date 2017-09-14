package main.Quest;


import main.Quest.Types.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;


public class QuestSystem implements CommandExecutor{
    private Plugin pl;
    private ArrayList<Quest> quests = new ArrayList<>();

    public QuestSystem(Plugin pl){
        this.pl = pl;
    }




    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if (sender instanceof Player) {
            Player p = (Player) sender;
            //Erstelle eine Quest
            if (cmd.getName().equalsIgnoreCase("createQuest")) {
                if (args.length == 3) {
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
                    } catch (NumberFormatException nfe) {
                        p.sendMessage(args[1] + " ist keine valide Nummer!");
                        return false;
                    }

                    String name = args[2];

                    switch (type) {
                        case "assasination":
                            quests.add(new AssasinationQuest(quests.size() + 1, lvl, name));
                            break;
                        case "collection":
                            quests.add(new CollectionQuest(quests.size() + 1, lvl, name));
                            break;
                        case "delivering":
                            quests.add(new DeliveringQuest(quests.size() + 1, lvl, name));
                            break;
                        case "escort":
                            quests.add(new EscortQuest(quests.size() + 1, lvl, name));
                            break;
                        case "killing":
                            quests.add(new KillingQuest(quests.size() + 1, lvl, name));
                            break;
                    }

                    p.sendMessage(quests.get(quests.size() - 1).toString());
                    p.sendMessage(quests.size() + "");
                    return true;
                } else {
                    p.sendMessage("Nicht genügend oder zuviele Argumente, benötigt wird: Quest-Type, Mindestlevel und QuestName");
                    return false;
                }


            }


            //Fügt er Quest eine Description zu
            if (cmd.getName().equalsIgnoreCase("addDescription")) {

                if (args.length > 1) {
                    //erstes Argument ist die Quest ID
                    int questID;
                    try {
                        questID = Integer.parseInt(args[0]);
                    } catch (NumberFormatException nfe) {
                        p.sendMessage(args[0] + " ist keine valide Questnummer!");
                        return false;
                    }
                    if (questID > quests.size()) {
                        p.sendMessage(questID + " ist keine gültige Quest!");
                        return false;
                    }
                    //Alles danach ist die Description

                    StringBuilder description = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        description.append(args[i]).append(" ");
                    }

                    Quest quest = quests.get(questID - 1);
                    quest.setDescription(description.toString());
                    quests.set(questID - 1, quest);

                    return true;
                } else {

                    return false;
                }


            }

            if (cmd.getName().equalsIgnoreCase("printQuest")) {
                p.sendMessage(quests.size() + "");

                if (args.length == 1) {

                    if(!checkIfNumber(args[0],p)) return false;

                    int questID = Integer.parseInt(args[0]);
                    if(questID == 0) {
                        String[] output = new String[quests.size()];
                        for (int i = 0; i < output.length; i++) {
                            output[i] = quests.get(i).toString();
                        }
                        p.sendMessage(output);
                    } else {
                        if (!questExists(args[0], p)) return false;
                        p.sendMessage(quests.get(questID - 1).toString());

                        return true;
                    }
                }

            }


            if (cmd.getName().equalsIgnoreCase("addMissionTarget")) {

                if (args.length > 2) {
                    if (!questExists(args[0], p))
                        return false;
                    Quest quest = quests.get(Integer.parseInt(args[0]) - 1);
                    if (!checkIfNumber(args[1], p)) return false;
                    int position = Integer.parseInt(args[1]);

                    StringBuilder description = new StringBuilder();
                    for (int i = 2; i < args.length; i++) {
                        description.append(args[i]).append(" ");
                    }

                    quest.addMissionTarget(position, description.toString());
                    return true;

                } else {
                    p.sendMessage("Nicht genug Argumente. Erwartet wird: questID, targetMissionNumber, targetMissionDescription");
                    return false;
                }

            }

            if (cmd.getName().equalsIgnoreCase("editMissionTarget")) {

                p.sendMessage("Not implemented yet");
                return true;


            }

            if (cmd.getName().equalsIgnoreCase("deleteMissionTarget")) {

                p.sendMessage("Not implemented yet");
                return true;
            }

            //Spawnt einen Quest NPC
            if (cmd.getName().equalsIgnoreCase("spawnQuestNPC")) {

                p.sendMessage("Not implemented yet");
                return true;
            }

            //Quest Binding
            if (cmd.getName().equalsIgnoreCase("bindQuest")) {

                p.sendMessage("Not implemented yet");
                return true;
            }

        } else {
            System.out.println("Sie sind kein Spieler");
        }
        return false;
    }



    private boolean questExists(String arg, Player p){
        if(!checkIfNumber(arg,p)) return false;
        int questID = Integer.parseInt(arg);
        if(questID > quests.size()){
            p.sendMessage(questID + " ist keine gültige Quest!");
            return false;
        }
        return true;
    }


    private boolean checkIfNumber(String arg, Player p){
        try {
            int questID = Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException nfe){
            p.sendMessage(arg + " ist keine valide Questnummer!");
            return false;
        }
    }
}








