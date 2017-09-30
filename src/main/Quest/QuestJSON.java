package main.Quest;

import main.Quest.Types.Quest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class QuestJSON {
    private int anz;
    private ArrayList<Quest> quests;

    public QuestJSON(){
        anz = 0;
        quests = new ArrayList<>();
    }

    public QuestJSON(ArrayList<Quest> quests){
        setQuests(quests);
    }


    public void writeQuest(){
        JSONArray array = new JSONArray();
        for (int i = 0; i < quests.size(); i++) {
            Quest quest = quests.get(i);
            JSONObject object = new JSONObject();
            object.put("QuestID",quest.getQuestID());
            object.put("QuestName",quest.getQuestName());
            object.put("QuestType",quest.getQuestType());
            object.put("LevelRequirement",quest.getLevelRequirement());
            object.put("Description",quest.getDescription());
            object.put("MissionTargets",quest.getMissionTargets());
            object.put("Reward",quest.getReward());
            array.add(object);
        }

        JSONObject obj = new JSONObject();
        obj.put("Anzahl",anz);
        obj.put("Quests",array);

        try (FileWriter file = new FileWriter("plugins/RPG/Quests/Quests.json")){
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException ioex){
            ioex.printStackTrace();
        }


    }

    public void readQuests(){
        JSONParser parser = new JSONParser();

        try{
            JSONObject obj = (JSONObject)parser.parse(new FileReader("plugins/RPG/Quests/Quests.json"));

            anz = Integer.parseInt((String)obj.get("Anzahl"));

            JSONArray array = (JSONArray)obj.get("Quests");
            Iterator<JSONObject> iterator = array.iterator();
            quests.clear();
            while(iterator.hasNext()){
                JSONObject jobj = iterator.next();
                quests.add(new Quest(
                        Integer.parseInt((String)jobj.get("QuestID")),
                        Integer.parseInt((String)jobj.get("LevelRequirement")),
                        (String)jobj.get("QuestName"),
                        (String)jobj.get("Description"),
                        (HashMap<Integer,String>)jobj.get("MissionTargets"),
                        (String)jobj.get("Reward"),
                        (Quest.QuestTypes)jobj.get("QuestType")
                ));

            }



        } catch (IOException ioex){
            ioex.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void setQuests(ArrayList<Quest> quests) {
        this.quests = quests;
        anz = quests.size();
    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }
}
