package com.snakeindustry.snakemultiplayer.generalApp.game;

import com.snakeindustry.snakemultiplayer.generalApp.player.Player;

import java.util.List;

/**
 * Created by Adrien on 28/03/15.
 */
public abstract class GameState {

    List<Player> playerList;

    public abstract boolean isGameOver();

    public abstract void configure();

    public abstract void nextStep();

    public abstract void gameOverAction();

}
