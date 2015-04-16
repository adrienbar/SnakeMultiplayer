package com.snakeindustry.snakemultiplayer.generalApp.player.settings.model;

import java.util.HashMap;

/**
 * Created by Vincent on 16/04/2015.
 */
public class SettingsGlobalHashmap implements Settings {

    private HashMap<String, GameSettings> GameSettingsHashMap;

    public SettingsGlobalHashmap(HashMap<String, GameSettings> gameSettingsHashMap){
        GameSettingsHashMap = gameSettingsHashMap;
    }

    public SettingsGlobalHashmap(){
        this(new HashMap<String, GameSettings>());
    }

    @Override
    public double getSpeed() {
        return 0;
    }

    @Override
    public void setSpeed() {

    }

    @Override
    public GameSettings getSettingsForOneGame(String gameName) {
        return null;
    }

    @Override
    public void setSettingsForOneGame(String gameName, GameSettings gamesettings) {

    }

    @Override
    public void resetSettings(String gameName) {

    }

    @Override
    public void deleteGameSettings(String gameName) {

    }
}
