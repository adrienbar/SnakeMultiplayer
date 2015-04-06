package com.snakeindustry.snakemultiplayer.generalApp.player.stats;

import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.player.Player;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrien on 31/03/15.
 */
public interface Stats {

    //Played Time
    public double getPlayedTime(Game game);
    public void addPlayedTime(Game game, double hour);

    //Nb Plays
    public void addAPlay(Game game);
    public int getNbPlay(Game game);

    //Friends
    public void addFriend(String friendName,Game game);
    public List<OneStats> getStatsFriends();


    //
    // public List<Game> getGameWithStats();
     public List<OneStats> GamesStatsNbPlay();
     public OneGameStats getStatsForOneGame(Game game);

    //Create/delete
    public void createStatsIfNothing(Game game);
    public void resetStats(Game game);



}
