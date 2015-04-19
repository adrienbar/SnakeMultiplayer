package com.snakeindustry.snakemultiplayer.generalApp.game;

import com.snakeindustry.snakemultiplayer.Snake.SnakeSettings;
import com.snakeindustry.snakemultiplayer.Snake.model.SnakeGameState;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.player.settings.model.DefaultSettingsActivity;
import com.snakeindustry.snakemultiplayer.generalApp.player.settings.model.GameSettings;
import com.snakeindustry.snakemultiplayer.generalApp.player.settings.model.GameSettingsC;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.GameStats;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.GameStatsC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrien on 19/04/15.
 */
public abstract class DefaultGame implements Game {

    private GameState gameState;
    private final String gameName;
    private final int idIcon;

    public DefaultGame(String gameName,int idIcon){
        this.gameName=gameName;
        this.idIcon=idIcon;
        this.gameState=new GameState() {

            @Override
            public boolean isGameOver() {
                return false;
            }

            @Override
            public void nextStep(HashMap<String, Integer> playerCommand, long threadTime) {

            }

            @Override
            public void gameOverAction() {

            }
        };
    }

    public DefaultGame(String gameName) {
        this(gameName,0);
    }

    public DefaultGame(){
        this("Dummy Game");
    }




    @Override
    public String getName() {
        return this.gameName;
    }

    @Override
    public int getIdIcon() {
        return idIcon;
    }


    @Override
    public GameStats createStats() {
        return new GameStatsC();
    }

    @Override
    public GameState getGameState() {
       return gameState;
    }

    @Override
    public GameSettings createSettings() {
        return new GameSettingsC() {
        };
    }

    @Override
    public Class getSettingsActivity() {
        return DefaultSettingsActivity.class;
    }
}
