package com.snakeindustry.snakemultiplayer.generalApp.player.stats.model;

import android.content.Context;

import java.util.List;

/**
 * Created by Adrien on 06/04/15.
 */
public interface GameStats {


    //Played time
    public SimpleStats getPlayedTime();

    public void addPlayedTime(double hour);

    //nb of plays
    public SimpleStats getNbPlay();

    public void addAPlay();

    //friends
    public void addAPlayWithAFriend(String friend);

    public List<SimpleStats> getStatsFriends();

    //This may have returned an in or a double but a string is enough, because comparing the score between different games does not make much sens.
    //and a string allows different format of score
    public SimpleStats getBestScore();

    public void addScore(double score);


    public List<SimpleStats> getStatsAsList();

    public void saveStats(Context context, String file);

    public void loadStats(Context context, String file);

    public void reset();
}
