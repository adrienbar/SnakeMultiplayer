package com.snakeindustry.snakemultiplayer.generalApp.game;

import android.content.Context;
import android.view.View;

import com.snakeindustry.snakemultiplayer.generalApp.player.settings.model.GameSettings;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.GameStats;

/**
 * Created by Adrien on 28/03/15.
 */
public interface Game {

public String getName();
public int getIdIcon();

public GameView getGameView();
public GameStats createStats();
public GameState getGameState();
public void resetGameState();
public GameSettings createSettings();
public Class getSettingsActivity();

    //type View should be discussed, and more generally the Settings of a game
    //public View getSettings();

}
