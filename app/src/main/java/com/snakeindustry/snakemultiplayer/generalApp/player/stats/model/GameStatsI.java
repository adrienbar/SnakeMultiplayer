package com.snakeindustry.snakemultiplayer.generalApp.player.stats.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Adrien on 06/04/15.
 */
public class GameStatsI implements GameStats {
    private SimpleStatsI playedTime,nbPlay,bestScore;
    private HashMap<String,Integer> friends;
    private List<Double> scoreValues;

    public GameStatsI(SimpleStatsI playedTime, SimpleStatsI nbPlay, SimpleStatsI bestScore, HashMap<String, Integer> friends,List<Double> scoreValues) {
        this.playedTime = playedTime;
        this.nbPlay = nbPlay;
        this.bestScore = bestScore;
        this.friends = friends;
        this.scoreValues=scoreValues;

    }

    public GameStatsI() {
        this(new SimpleStatsI("Played time",0,"h",null),
                new SimpleStatsI("Nb of plays",0,"play(s)",null),
                new SimpleStatsI("Best Score", 0,"",null),
                new HashMap<String, Integer>(),
                new ArrayList<Double>());


    }





    @Override
    public List<SimpleStats> getStatsFriends() {
        LinkedList<SimpleStats> list=new LinkedList<>();
        for(String friendName : this.getFriends().keySet())
        {
            list.add(new SimpleStatsI(friendName,this.getFriends().get(friendName),"play(s)",null));
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

    @Override
    public void addAPlayWithAFriend(String friend) {
            Integer tmp=this.getFriends().get(friend);
            if (tmp!=null) {
                this.getFriends().put(friend,tmp+1);
            }
            else{
                this.getFriends().put(friend,1);
            }

    }




    @Override
    public List<SimpleStats> getStatsAsList() {
        List<SimpleStats> list = new ArrayList<>();
        list.add(this.getBestScore());
        list.add(this.getPlayedTime());
        list.add(this.getNbPlay());
        return list;
    }

    @Override
    public void reset() {
        this.getNbPlay().reset();
        this.getPlayedTime().reset();
        this.getFriends().clear();
        this.getBestScore().reset();
    }


    //GETTERS and SETTERS
    public HashMap<String, Integer> getFriends() {
        return friends;
    }

    @Override
    public SimpleStatsI getPlayedTime() {
        return playedTime;
    }

    @Override
    public SimpleStatsI getNbPlay() {
        return nbPlay;
    }

    public SimpleStatsI getBestScore() {
        return bestScore;
    }

    @Override
    public void addScore(double score) {
        this.getScoreValues().add(score);
        if(this.getBestScore().getValue()<score) {
            this.getBestScore().setValue(score);
        }
    }

    public void setPlayedTime(SimpleStatsI playedTime) {
        this.playedTime = playedTime;
    }

    public void setNbPlay(SimpleStatsI nbPlay) {
        this.nbPlay = nbPlay;
    }

    public void setBestScore(SimpleStatsI bestScore) {
        this.bestScore = bestScore;
    }

    public void setFriends(HashMap<String, Integer> friends) {
        this.friends = friends;
    }

    public List<Double> getScoreValues() {
        return scoreValues;
    }

    public void setScoreValues(List<Double> scoreValues) {
        this.scoreValues = scoreValues;
    }
}
