package com.snakeindustry.snakemultiplayer.generalApp.player.settings.model;

import com.snakeindustry.snakemultiplayer.Snake.SnakeSettings;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;

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
        return this.getGameSettingsHashMap().get(gameName);
    }

    @Override
    public void setSettingsForOneGame(String gameName, GameSettings gamesettings) {
        HashMap<String,GameSettings> temp = new HashMap<String, GameSettings>();
        temp.put(gameName,gamesettings);
        this.setGameSettingsHashMap(temp);
    }

    public HashMap<String, GameSettings> getGameSettingsHashMap() {
        return GameSettingsHashMap;
    }

    public void setGameSettingsHashMap(HashMap<String, GameSettings> gameSettingsHashMap) {
        GameSettingsHashMap = gameSettingsHashMap;
    }

    @Override
    public void createSettingsIfNothing(String gameName) {
        GameSettings settingsForOneGame = this.getGameSettingsHashMap().get(gameName);
        if (settingsForOneGame==null){
            System.out.println("bbbbbbbbbbb create if nothing "+gameName);
            this.getGameSettingsHashMap().put(gameName, AppSingleton.getGameFromName(gameName).createSettings());
            SnakeSettings snakeSettings = (SnakeSettings) AppSingleton.getInstance().getPlayer().getSettings().getSettingsForOneGame(AppSingleton.getInstance().getCurrentGame().getName());

            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbb"+snakeSettings);
        }
    }

    @Override
    public void resetSettings(String gameName) {

    }

    @Override
    public void deleteGameSettings(String gameName) {
        System.out.println("unimplemented");
    }
}
