package com.snakeindustry.snakemultiplayer.generalApp.player;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrien on 31/03/15.
 */
public class StatsForOneGame {

    private int nbPlay;
    private double PlayedTime;
    private HashMap<Player,Integer> friends;


    public StatsForOneGame(int nbPlay, double playedTime, HashMap<Player, Integer> friends) {
        this.nbPlay = nbPlay;
        PlayedTime = playedTime;
        this.friends = friends;
    }

    public void addPlayedTime(double hour) {
        this.setPlayedTime(this.getPlayedTime()+hour);
    }

    public void addAPlay() {
        this.setNbPlay(this.getNbPlay()+1);
    }

    public void addAGameWithFriend(Player friend) {
        Integer tmp=this.getFriends().get(friend);
        if (tmp!=null) {
            this.getFriends().put(friend,tmp+1);
        }
        else{
            this.getFriends().put(friend,1);
        }
    }





    public int getNbPlay() {
        return nbPlay;
    }

    public double getPlayedTime() {
        return PlayedTime;
    }

    public HashMap<Player, Integer> getFriends() {
        return friends;
    }

    public void setNbPlay(int nbPlay) {
        this.nbPlay = nbPlay;
    }

    public void setPlayedTime(double playedTime) {
        PlayedTime = playedTime;
    }

    public void setFriends(HashMap<Player, Integer> friends) {
        this.friends = friends;
    }
}
