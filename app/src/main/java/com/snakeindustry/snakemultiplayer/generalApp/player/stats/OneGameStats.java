package com.snakeindustry.snakemultiplayer.generalApp.player.stats;

import com.snakeindustry.snakemultiplayer.generalApp.player.Player;

import java.util.List;

/**
 * Created by Adrien on 06/04/15.
 */
public interface OneGameStats {

    public double getPlayedTimeValue();
    public int getNbPlayValue();

    //This may have returned an in or a double but a string is enough, because comparing the score between different games does not make much sens.
    //and a string allows different format of scores
    public String getBestScoreAsAString();
    public List<String> getFriendsList();

    public void addPlayedTime(double hour);
    public void addAPlay();

}
