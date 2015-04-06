package com.snakeindustry.snakemultiplayer.generalApp.game;

import android.view.View;

import com.snakeindustry.snakemultiplayer.generalApp.player.stats.StatsForOneGame;

/**
 * Created by Adrien on 28/03/15.
 */
public interface Game {

public String getName();
public int getIdIcon();

public GameView getGameView();
public StatsForOneGame createStats();
public GameState getGameState();

    //type View should be discussed, and more generally the Settings of a game
public View getSettings();

}
