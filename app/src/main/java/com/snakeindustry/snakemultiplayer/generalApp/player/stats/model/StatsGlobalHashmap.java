package com.snakeindustry.snakemultiplayer.generalApp.player.stats.model;

import android.graphics.Bitmap;

import com.snakeindustry.snakemultiplayer.generalApp.game.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrien on 06/04/15.
 */
public class StatsGlobalHashmap implements Stats {

    private HashMap<String,GameStats> GamesStatsHashMap;


    public StatsGlobalHashmap(HashMap<String, GameStats> gamesStatsHashMap) {
        GamesStatsHashMap = gamesStatsHashMap;
    }

    public StatsGlobalHashmap() {
        this(new HashMap<String, GameStats>());
    }




    @Override
    public double getPlayedTime(Game game) {
        GameStats tmp = this.getGamesStatsHashMap().get(game.getName());
        if(tmp==null) {
            return 0;
        }
        else {
           return tmp.getPlayedTime().getValue();
        }


    }

    @Override
    public void addPlayedTime(Game game, double hour) {
        GameStats tmp = this.getGamesStatsHashMap().get(game.getName());
        if(tmp!=null) {
            tmp.addPlayedTime(hour);
        }
    }

    @Override
    public void addAPlay(Game game) {
        GameStats tmp = this.getGamesStatsHashMap().get(game.getName());
        if(tmp!=null) {
            tmp.addAPlay();
        }
    }

    @Override
    public int getNbPlay(Game game) {
        GameStats tmp = this.getGamesStatsHashMap().get(game.getName());
        if(tmp!=null) {
            return (int) tmp.getNbPlay().getValue();
        }
        else{
            return 0;
        }
    }

    @Override
    public void addFriend(String friendName, Game game) {
        GameStats tmp = this.getGamesStatsHashMap().get(game.getName());
        if(tmp!=null) {
            tmp.addAPlayWithAFriend(friendName);
        }

    }

    @Override
    public List<SimpleStats> getStatsFriends() {

        HashMap<String,Integer> counter = new HashMap<>();

        for (String gameName : this.getGamesStatsHashMap().keySet()) {

            GameStats oneGameStats  = this.getGamesStatsHashMap().get(gameName);

            for (SimpleStats statsFriend : oneGameStats.getStatsFriends()) {
                if(counter.get(statsFriend.getDescription())!=null){
                    counter.put(statsFriend.getDescription(),(int) (counter.get(statsFriend.getDescription())+statsFriend.getValue()));
                }
                else{
                    counter.put(statsFriend.getDescription(),(int) statsFriend.getValue());
                }

            }
        }
        List<SimpleStats> list=new ArrayList<SimpleStats>();
        for(String s :counter.keySet()) {
            SimpleStats SimpleStats =new SimpleStatsC(s,counter.get(s),"play(s)", null);
            list.add(SimpleStats);
        }
        return list;
    }

    @Override
    public List<SimpleStats> GamesStatsNbPlay() {
        List<SimpleStats> list=new ArrayList<SimpleStats>();

        for (String gameName :this.getGamesStatsHashMap().keySet()) {

            double value = this.getGamesStatsHashMap().get(gameName).getNbPlay().getValue();
            String unit = this.getGamesStatsHashMap().get(gameName).getNbPlay().getUnit();


            //icon
            Bitmap icon= Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);
            SimpleStats SimpleStats =new SimpleStatsC(gameName,value,unit,icon);
            list.add(SimpleStats);
        }
        return list;

    }

    @Override
    public GameStats getStatsForOneGame(Game game) {
        return this.getGamesStatsHashMap().get(game.getName());
    }

    @Override
    public void createStatsIfNothing(Game game) {
        GameStats statsForOneGame = this.getStatsForOneGame(game);
        if(statsForOneGame==null) {
            this.getGamesStatsHashMap().put(game.getName(),game.createStats());
        }
    }

    @Override
    public void resetStats(Game game) {
        this.getGamesStatsHashMap().get(game.getName()).reset();
    }


    public HashMap<String, GameStats> getGamesStatsHashMap() {
        return GamesStatsHashMap;
    }

    public void setGamesStatsHashMap(HashMap<String, GameStats> gamesStatsHashMap) {
        GamesStatsHashMap = gamesStatsHashMap;
    }
}
