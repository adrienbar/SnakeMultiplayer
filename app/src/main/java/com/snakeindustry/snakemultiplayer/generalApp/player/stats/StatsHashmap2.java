package com.snakeindustry.snakemultiplayer.generalApp.player.stats;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrien on 06/04/15.
 */
public class StatsHashmap2 implements Stats {

    private HashMap<String,OneGameStats> GamesStatsHashMap;


    public StatsHashmap2(HashMap<String, OneGameStats> gamesStatsHashMap) {
        GamesStatsHashMap = gamesStatsHashMap;
    }

    public StatsHashmap2() {
        this(new HashMap<String, OneGameStats>());
    }





    @Override
    public double getPlayedTime(Game game) {
        OneGameStats tmp = this.getGamesStatsHashMap().get(game.getName());
        if(tmp==null) {
            return 0;
        }
        else {
           return tmp.getPlayedTime().getValue();
        }


    }

    @Override
    public void addPlayedTime(Game game, double hour) {
        OneGameStats tmp = this.getGamesStatsHashMap().get(game.getName());
        if(tmp!=null) {
            tmp.addPlayedTime(hour);
        }
    }

    @Override
    public void addAPlay(Game game) {
        OneGameStats tmp = this.getGamesStatsHashMap().get(game.getName());
        if(tmp!=null) {
            tmp.addAPlay();
        }
    }

    @Override
    public int getNbPlay(Game game) {
        OneGameStats tmp = this.getGamesStatsHashMap().get(game.getName());
        if(tmp!=null) {
            return (int) tmp.getNbPlay().getValue();
        }
        else{
            return 0;
        }
    }

    @Override
    public void addFriend(String friendName, Game game) {
        OneGameStats tmp = this.getGamesStatsHashMap().get(game.getName());
        if(tmp!=null) {
            tmp.addAPlayWithAFriend(friendName);
        }

    }

    @Override
    public List<OneStats> getStatsFriends() {

        HashMap<String,Integer> counter = new HashMap<>();

        for (String gameName : this.getGamesStatsHashMap().keySet()) {

            OneGameStats oneGameStats  = this.getGamesStatsHashMap().get(gameName);

            for (OneStats statsFriend : oneGameStats.getStatsFriends()) {
                if(counter.get(statsFriend.getDescription())!=null){
                    counter.put(statsFriend.getDescription(),(int) (counter.get(statsFriend.getDescription())+statsFriend.getValue()));
                }
                else{
                    counter.put(statsFriend.getDescription(),(int) statsFriend.getValue());
                }

            }
        }
        List<OneStats> list=new ArrayList<OneStats>();
        for(String s :counter.keySet()) {
            OneStats oneStats=new OneStatsClass(s,counter.get(s),"play(s)", null);
            list.add(oneStats);
        }
        return list;
    }

    @Override
    public List<OneStats> GamesStatsNbPlay() {
        List<OneStats> list=new ArrayList<OneStats>();

        for (String gameName :this.getGamesStatsHashMap().keySet()) {

            double value = this.getGamesStatsHashMap().get(gameName).getNbPlay().getValue();
            String unit = this.getGamesStatsHashMap().get(gameName).getNbPlay().getUnit();


            //icon
            Bitmap icon= Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);
            OneStats oneStats=new OneStatsClass(gameName,value,unit,icon);
            list.add(oneStats);
        }
        return list;

    }

    @Override
    public OneGameStats getStatsForOneGame(Game game) {
        return this.getGamesStatsHashMap().get(game.getName());
    }

    @Override
    public void createStatsIfNothing(Game game) {
        OneGameStats statsForOneGame = this.getGamesStatsHashMap().get(game.getName());
        if(statsForOneGame==null) {
            this.getGamesStatsHashMap().put(game.getName(),game.createStats());
        }
    }

    @Override
    public void resetStats(Game game) {
        this.getGamesStatsHashMap().get(game.getName()).reset();
    }


    public HashMap<String, OneGameStats> getGamesStatsHashMap() {
        return GamesStatsHashMap;
    }

    public void setGamesStatsHashMap(HashMap<String, OneGameStats> gamesStatsHashMap) {
        GamesStatsHashMap = gamesStatsHashMap;
    }
}
