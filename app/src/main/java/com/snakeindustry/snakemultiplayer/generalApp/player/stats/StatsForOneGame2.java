package com.snakeindustry.snakemultiplayer.generalApp.player.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Adrien on 06/04/15.
 */
public class StatsForOneGame2 implements OneGameStats {
    private OneStatsClass playedTime,nbPlay,bestScore;
    private HashMap<String,Integer> friends;

    public StatsForOneGame2(OneStatsClass playedTime, OneStatsClass nbPlay, OneStatsClass bestScore, HashMap<String, Integer> friends) {
        this.playedTime = playedTime;
        this.nbPlay = nbPlay;
        this.bestScore = bestScore;
        this.friends = friends;
    }

    public StatsForOneGame2() {
        this(new OneStatsClass("Played time",0,"h",null),
                new OneStatsClass("Nb of plays",0,"play(s)",null),
                new OneStatsClass("Best Score", 0,"",null),
                new HashMap<String, Integer>());

    }





    @Override
    public String getBestScoreAsAString() {
        return ""+this.getBestScore().getValue()+ this.getBestScore().getUnit();
    }

    @Override
    public List<OneStats> getFriendsList() {
        List<OneStats> list = new ArrayList<>();
        for (String s : this.getFriends().keySet()) {
            String play="play";
            double value=this.getFriends().get(s);
            if(value>1) {
                play=play+s;
            }
            OneStats nbPlayWithFriend=new OneStatsClass(s,value,play,null);

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
    public List<OneStats> getStatsFriends() {
         LinkedList<OneStats> list=new LinkedList<>();
        for(String friendName : this.getFriends().keySet())
        {
            list.add(new OneStatsClass(friendName,this.getFriends().get(friendName),"play(s)",null));
        }

        return list;
    }

    @Override
    public List<OneStats> getSpecificStats() {
        return new LinkedList<>();
    }

    @Override
    public List<OneStats> getAllStats() {
        List<OneStats> list = new ArrayList<>();

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
    public OneStatsClass getPlayedTime() {
        return playedTime;
    }

    @Override
    public OneStatsClass getNbPlay() {
        return nbPlay;
    }

    public OneStatsClass getBestScore() {
        return bestScore;
    }

    public void setPlayedTime(OneStatsClass playedTime) {
        this.playedTime = playedTime;
    }

    public void setNbPlay(OneStatsClass nbPlay) {
        this.nbPlay = nbPlay;
    }

    public void setBestScore(OneStatsClass bestScore) {
        this.bestScore = bestScore;
    }

    public void setFriends(HashMap<String, Integer> friends) {
        this.friends = friends;
    }



}
