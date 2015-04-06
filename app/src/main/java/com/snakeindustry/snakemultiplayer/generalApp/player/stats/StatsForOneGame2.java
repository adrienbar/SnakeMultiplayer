package com.snakeindustry.snakemultiplayer.generalApp.player.stats;

import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.player.Player;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.OneStats;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Adrien on 06/04/15.
 */
public class StatsForOneGame2 implements OneGameStats {
    private OneStats playedTime,nbPlay;
    private double bestScore;
    private HashMap<String,Integer> friends;

    public StatsForOneGame2(OneStats playedTime, OneStats nbPlay, double bestScore, HashMap<String, Integer> friends) {
        this.playedTime = playedTime;
        this.nbPlay = nbPlay;
        this.bestScore = bestScore;
        this.friends = friends;
    }

    public StatsForOneGame2() {
        this(new OneStats(),new OneStats(),0,new HashMap<String, Integer>());
    }




    @Override
    public double getPlayedTime() {
     return 0;
    }

    @Override
    public int getNbPlay() {
        return 0;
    }

    @Override
    public String getBestScore() {
        return null;
    }

    @Override
    public List<String> getFriends() {
        return null;
    }



    //GETTERS and SETTERS


    public void setPlayedTime(OneStats playedTime) {
        this.playedTime = playedTime;
    }

    public void setNbPlay(OneStats nbPlay) {
        this.nbPlay = nbPlay;
    }

    public void setBestScore(double bestScore) {
        this.bestScore = bestScore;
    }

    public void setFriends(HashMap<String, Integer> friends) {
        this.friends = friends;
    }
}
