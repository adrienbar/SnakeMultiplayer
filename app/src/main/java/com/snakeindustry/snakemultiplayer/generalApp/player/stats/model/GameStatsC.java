package com.snakeindustry.snakemultiplayer.generalApp.player.stats.model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Adrien on 06/04/15.
 */
public class GameStatsC implements GameStats {
    private SimpleStatsC playedTime,nbPlay,bestScore;
    private HashMap<String,Integer> friends;
    private List<Double> scoreValues;

    public GameStatsC(SimpleStatsC playedTime, SimpleStatsC nbPlay, SimpleStatsC bestScore, HashMap<String, Integer> friends, List<Double> scoreValues) {
        this.playedTime = playedTime;
        this.nbPlay = nbPlay;
        this.bestScore = bestScore;
        this.friends = friends;
        this.scoreValues=scoreValues;

    }

    public GameStatsC() {
        this(new SimpleStatsC("Played time",0,"min",null),
                new SimpleStatsC("Nb of plays",0,"play(s)",null),
                new SimpleStatsC("Best Score", 0,"",null),
                new HashMap<String, Integer>(),
                new ArrayList<Double>());


    }

    @Override
    public List<SimpleStats> getStatsFriends() {
        LinkedList<SimpleStats> list=new LinkedList<>();
        for(String friendName : this.getFriends().keySet())
        {
            list.add(new SimpleStatsC(friendName,this.getFriends().get(friendName),"play(s)",null));
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
    public void saveStats(Context context, String file) {
        FileOutputStream output;
        OutputStreamWriter osw;
        List<SimpleStats> statsList = getStatsAsList();

        try {
            output = context.openFileOutput(file, Context.MODE_PRIVATE);
            osw = new OutputStreamWriter(output);
            System.out.println("Values save");
            for (SimpleStats stats : statsList) {
                System.out.println(stats.getValue());
                osw.write(stats.getValue() + "\n");
                osw.flush();
            }

            if (output != null)
                output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadStats(Context context, String file) {
        FileInputStream intput;
        InputStreamReader isr;
        Double statsValue;
        BufferedReader reader;
        List<SimpleStats> statsList = getStatsAsList();

        try {
            intput = context.openFileInput(file);
            isr = new InputStreamReader(intput);
            reader = new BufferedReader(isr);
            System.out.println("Values load");
            for (SimpleStats stats : statsList) {
                statsValue = Double.parseDouble(reader.readLine());
                System.out.println(statsValue);
                stats.add(statsValue);
            }

            if (intput != null)
                intput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void setFriends(HashMap<String, Integer> friends) {
        this.friends = friends;
    }

    @Override
    public SimpleStatsC getPlayedTime() {
        return playedTime;
    }

    public void setPlayedTime(SimpleStatsC playedTime) {
        this.playedTime = playedTime;
    }

    @Override
    public SimpleStatsC getNbPlay() {
        return nbPlay;
    }

    public void setNbPlay(SimpleStatsC nbPlay) {
        this.nbPlay = nbPlay;
    }

    public SimpleStatsC getBestScore() {
        return bestScore;
    }

    public void setBestScore(SimpleStatsC bestScore) {
        this.bestScore = bestScore;
    }

    @Override
    public void addScore(double score) {
        this.getScoreValues().add(score);
        if (this.getBestScore().getValue() < score) {
            this.getBestScore().setValue(score);
        }
    }

    public List<Double> getScoreValues() {
        return scoreValues;
    }

    public void setScoreValues(List<Double> scoreValues) {
        this.scoreValues = scoreValues;
    }
}
