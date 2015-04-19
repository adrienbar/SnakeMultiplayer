package com.snakeindustry.snakemultiplayer.generalApp.player.settings.model;

import com.snakeindustry.snakemultiplayer.generalApp.game.Game;

/**
 * Created by Vincent on 16/04/2015.
 */
public interface Settings {

    //Speed
    public int getSpeed(String gameName);
    public void setSpeed(String gameName, int i);

    // public List<Game> getGameWithStats();
    public GameSettings getSettingsForOneGame(String gameName);
    public void setSettingsForOneGame(String gameName, GameSettings gamesettings);

    //delete/reset/create
    public void createSettingsIfNothing(String gameName);
    public void resetSettings(String gameName);
    public void deleteGameSettings(String gameName);
}
