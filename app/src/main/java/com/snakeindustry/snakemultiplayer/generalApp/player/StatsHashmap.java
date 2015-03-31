package com.snakeindustry.snakemultiplayer.generalApp.player;

import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrien on 31/03/15.
 */
public class StatsHashmap implements Stats {

    private HashMap<String,StatsForOneGame> stats;


    public HashMap<String, StatsForOneGame> getStats() {
        return stats;
    }

    public StatsHashmap(HashMap<String, StatsForOneGame> stats) {
        this.stats = stats;
    }

    public StatsHashmap() {
        this.stats = new HashMap<String, StatsForOneGame>();
    }


    @Override
    public double getPlayedTime(Game game) {
        return this.getStats().get(game.getName()).getPlayedTime();
    }

    @Override
    public void addPlayedTime(Game game, double time) {
        StatsForOneGame tmp = this.getStats().get(game.getName());
        if(tmp!=null) {
           tmp.addPlayedTime(time);
        }
        else{
            this.getStats().put(game.getName(),new StatsForOneGame(0,time,new HashMap<Player, Integer>()));
        }

        AppSingleton.getInstance().saveProfile();
    }

    @Override
    public void addAPlay(Game game) {

        StatsForOneGame tmp = this.getStats().get(game.getName());
        if(tmp!=null) {
            tmp.addAPlay();
        }
        else {
            this.getStats().put(game.getName(),new StatsForOneGame(1,0,new HashMap<Player, Integer>()));
        }
        AppSingleton.getInstance().saveProfile();
    }

    @Override
    public int getNbPlay(Game game) {
        return this.getNbPlay(game);
    }

    @Override
    public void addFriend(Player player,Game game) {


        StatsForOneGame tmp = this.getStats().get(game.getName());
        if(tmp!=null) {
            tmp.addAGameWithFriend(player);
        }
        else {
            this.getStats().put(game.getName(),new StatsForOneGame(0,0,new HashMap<Player, Integer>()));
            this.getStats().get(game.getName()).addAGameWithFriend(player);
        }
        AppSingleton.getInstance().saveProfile();
    }

    @Override
    public List<Game> getGameWithStats() {

        List<Game> list=new ArrayList<Game>();
        for(String s : this.getStats().keySet()) {
            for (Game g: AppSingleton.getInstance().getAvailabeGames())
            if(s.equals(g.getName())) {
                list.add(g);
            }


        }
        return list;
    }
}
