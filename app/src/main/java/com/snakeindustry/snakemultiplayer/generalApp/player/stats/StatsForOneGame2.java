package com.snakeindustry.snakemultiplayer.generalApp.player.stats;

import java.util.ArrayList;
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
    public double getPlayedTimeValue() {
     return this.getPlayedTime().getValue();
    }

    @Override
    public int getNbPlayValue() {
        return (int) this.getNbPlay().getValue();
    }

    @Override
    public String getBestScoreAsAString() {
        return ""+this.getBestScore();
    }

    @Override
    public List<String> getFriendsList() {
        List<String> list = new ArrayList<>();
        for (String s : this.getFriends().keySet()) {
            list.add(s);
        }
        return list;
    }

    @Override
    public void addPlayedTime(double hour) {
        this.getPlayedTime().add(hour);
    }

    @Override
    public void addAPlay() {
        this.getNbPlay().add(1);
    }


    //GETTERS and SETTERS





    public HashMap<String, Integer> getFriends() {
        return friends;
    }

    public OneStats getPlayedTime() {
        return playedTime;
    }

    public OneStats getNbPlay() {
        return nbPlay;
    }

    public double getBestScore() {
        return bestScore;
    }

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
