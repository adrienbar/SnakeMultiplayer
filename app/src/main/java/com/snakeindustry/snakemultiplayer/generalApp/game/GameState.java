package com.snakeindustry.snakemultiplayer.generalApp.game;

import com.snakeindustry.snakemultiplayer.generalApp.player.Player;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.Server;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrien on 28/03/15.
 */
public abstract class GameState {


    public abstract boolean isGameOver();

    public abstract void configure(List<String> playersNames);

    public abstract void nextStep(HashMap<String,Integer> playerCommand,long threadTime);

    public abstract void gameOverAction();

}
