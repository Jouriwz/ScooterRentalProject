package ewa.server.storage;

import ewa.server.model.Battle;

import java.util.HashMap;
import java.util.Map;

public class BattleStorage {
    private static Map<String, Battle> battles;
    private static BattleStorage instance;

    private BattleStorage(){
        battles = new HashMap<>();
    }

    public static synchronized BattleStorage getInstance(){
        if (instance==null){
            instance = new BattleStorage();
        }
        return instance;
    }

    public Map<String, Battle> getBattles(){
        return battles;
    }

    public void setBattle(Battle battle){
        battles.put(battle.getBattle_id(), battle);
    }
}
