package com.snakeindustry.snakemultiplayer.generalApp.player.stats;

import com.snakeindustry.snakemultiplayer.generalApp.player.Player;

import java.util.List;

/**
 * Created by Adrien on 06/04/15.
 */
public interface OneGameStats {

    public double getPlayedTime();
    public int getNbPlay();
    public String getBestScore();
    public List<String> getFriends();

}
