package com.snakeindustry.snakemultiplayer.generalApp.player.stats.old;

import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrien on 31/03/15.
 */
public class StatsHashmap //implements Stats {
{
    /*
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

        System.out.println("AAAAAAA "+ "addPlay");
        StatsForOneGame tmp = this.getStats().get(game.getName());
        if(tmp!=null) {
            System.out.println("AAAAAAA "+ "addPlay IF");
            tmp.addAPlay();
        }
        else {
            this.getStats().put(game.getName(),new StatsForOneGame());
            this.getStats().get(game.getName()).addAPlay();
        }
        AppSingleton.getInstance().saveProfile();
    }

    @Override
    public int getNbPlay(Game game) {
        return this.getStats().get(game.getName()).getNbPlay();
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

    @Override
    public int getBestScore() {
        return 0;
    }

    @Override
    public void createStatsIfNothing(Game game) {
        StatsForOneGame tmp = this.getStats().get(game.getName());
        if(tmp==null) {
            this.getStats().put(game.getName(),game.createStats());
        }
    }

    @Override
    public void resetStats(Game game) {
        this.getStats().put(game.getName(),game.createStats());
    }
    */
}
