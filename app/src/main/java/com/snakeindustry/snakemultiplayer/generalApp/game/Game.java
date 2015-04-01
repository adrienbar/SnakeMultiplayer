package com.snakeindustry.snakemultiplayer.generalApp.game;

import android.app.Activity;
import android.media.Image;
import android.view.View;

import com.snakeindustry.snakemultiplayer.generalApp.player.StatsForOneGame;

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
