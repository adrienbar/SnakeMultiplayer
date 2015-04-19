package com.snakeindustry.snakemultiplayer.generalApp.game;

import com.snakeindustry.snakemultiplayer.generalApp.player.Player;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrien on 28/03/15.
 */
public abstract class GameState {


    private List<String> players;
    private HashMap<String,Double> playersScores;

    protected GameState(List<String> players, HashMap<String, Double> playersScores) {
        this.players = players;
        this.playersScores = playersScores;
    }

    protected GameState(){
        this(new ArrayList<String>(),new HashMap<String, Double>());
        System.out.println("GameState intialise null ");
    }

    public abstract boolean isGameOver();



    public abstract void nextStep(HashMap<String,Integer> playerCommand,long threadTime);

    public abstract void gameOverAction();




    public void configure(List<String> playersNames){
        players=new ArrayList<>(playersNames);
        System.out.println("GameState configure "+players);
        playersScores=new HashMap<>();
        for(String playerName : players){
            playersScores.put(playerName,0.0);
        }
    }

    protected void setScore(String playerName,Double score){
        playersScores.put(playerName,score);
    }

    public List<String> getPlayers() {
        return players;
    }


    public double getScore(String playerName){
      Double score=playersScores.get(playerName);
        if(score==null) {
            score=0.0;
        }
        return score;
    }

}
