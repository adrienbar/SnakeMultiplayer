package com.snakeindustry.snakemultiplayer.generalApp.game;

import android.os.SystemClock;

import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.GameStats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrien on 28/03/15.
 */
public abstract class GameState implements Serializable {


    private List<String> players;
    private HashMap<String, Double> playersScores;
    private long startTime;


    protected GameState(List<String> players, HashMap<String, Double> playersScores, HashMap<String, GameStats> playerStats) {
        this.players = players;
        this.playersScores = playersScores;
        this.startTime = SystemClock.elapsedRealtime();
    }

    protected GameState() {
        this(new ArrayList<String>(), new HashMap<String, Double>(), new HashMap<String, GameStats>());
    }

    public abstract boolean isGameOver();


    public abstract void nextStep(HashMap<String, String> playerCommand, long threadTime);

    public abstract void gameOverAction();


    public void configure(List<String> playersNames) {
        players = new ArrayList<>(playersNames);
        playersScores = new HashMap<>();
        for (String playerName : players) {
            playersScores.put(playerName, 0.0);
        }
    }

    protected void setScore(String playerName, Double score) {
        playersScores.put(playerName, score);
    }

    public List<String> getPlayers() {
        return players;
    }


    public double getScore(String playerName) {
        Double score = playersScores.get(playerName);
        if (score == null) {
            score = 0.0;
        }
        return score;
    }

    public void collectStats() {
        //updateStats
        String currentPlayer = AppSingleton.getInstance().getPlayer().getName();
        Game currentGame = AppSingleton.getInstance().getCurrentGame();
        GameStats myGameStats = AppSingleton.getInstance().getPlayer().getStats().getStatsForOneGame(currentGame);

        myGameStats.addAPlay();
        myGameStats.addPlayedTime((SystemClock.elapsedRealtime() - startTime) / 60000);
        for (String playerName : this.getPlayers()) {
            if (!playerName.equals(currentPlayer)) {
                myGameStats.addAPlayWithAFriend(playerName);
            }
            myGameStats.addScore(getScore(currentPlayer));

        }

    }
}
