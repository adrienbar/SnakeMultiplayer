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

    public GameStatsI(SimpleStatsI playedTime, SimpleStatsI nbPlay, SimpleStatsI bestScore, HashMap<String, Integer> friends) {
        this.playedTime = playedTime;
        this.nbPlay = nbPlay;
        this.bestScore = bestScore;
        this.friends = friends;
    }

    public GameStatsI() {
        this(new SimpleStatsI("Played time",0,"h",null),
                new SimpleStatsI("Nb of plays",0,"play(s)",null),
                new SimpleStatsI("Best Score", 0,"",null),
                new HashMap<String, Integer>());

    }





    @Override
    public String getBestScoreAsAString() {
        return ""+this.getBestScore().getValue()+ this.getBestScore().getUnit();
    }

    @Override
    public List<SimpleStats> getFriendsList() {
        List<SimpleStats> list = new ArrayList<>();
        for (String s : this.getFriends().keySet()) {
            String play="play";
            double value=this.getFriends().get(s);
            if(value>1) {
                play=play+s;
            }
            SimpleStats nbPlayWithFriend=new SimpleStatsI(s,value,play,null);

            list.add(nbPlayWithFriend);
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
    public List<SimpleStats> getStatsFriends() {
         LinkedList<SimpleStats> list=new LinkedList<>();
        for(String friendName : this.getFriends().keySet())
        {
            list.add(new SimpleStatsI(friendName,this.getFriends().get(friendName),"play(s)",null));
        }

        return list;
    }

    @Override
    public List<SimpleStats> getSpecificStats() {
        return new LinkedList<>();
    }

    @Override
    public List<SimpleStats> getAllStats() {
        List<SimpleStats> list = new ArrayList<>();

        list.add(this.getBestScore());
        list.add(this.getPlayedTime());
        list.add(this.getNbPlay());

      //  list.addAll(this.getSpecificStats());

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



}
