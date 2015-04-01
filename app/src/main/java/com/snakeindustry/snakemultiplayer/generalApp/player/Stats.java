package com.snakeindustry.snakemultiplayer.generalApp.player;

import com.snakeindustry.snakemultiplayer.generalApp.game.Game;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrien on 31/03/15.
 */
public interface Stats {

    public double getPlayedTime(Game game);
    public void addPlayedTime(Game game, double hour);
    public void addAPlay(Game game);
    public int getNbPlay(Game game);
    public void addFriend(Player player,Game game);
    public List<Game> getGameWithStats();
    public int getBestScore();

    public void createStatsIfNothing(Game game);
    public void resetStats(Game game);



}
