package com.snakeindustry.snakemultiplayer.generalApp.player.settings.model;

/**
 * Created by Vincent on 16/04/2015.
 */
public interface Settings {

    //Speed
    public double getSpeed();
    public void setSpeed();

    // public List<Game> getGameWithStats();
    public GameSettings getSettingsForOneGame(String gameName);
    public void setSettingsForOneGame(String gameName, GameSettings gamesettings);

    //delete/reset
    public void resetSettings(String gameName);
    public void deleteGameSettings(String gameName);
}
