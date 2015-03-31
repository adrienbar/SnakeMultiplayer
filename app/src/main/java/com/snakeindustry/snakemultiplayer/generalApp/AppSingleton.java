package com.snakeindustry.snakemultiplayer.generalApp;

import com.snakeindustry.snakemultiplayer.Snake.GameSnake;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Adrien on 31/03/15.
 */
public class AppSingleton {
    /**
     * List of instanced available games
     */
    private static final AppSingleton INSTANCE = new AppSingleton();

    private List<Game> availabeGames;
    private Game currentGame;


    //APPLICATION'S PARAMETERS
        private AppSingleton() {
        this.availabeGames=new ArrayList<Game>(Arrays.asList(new GameSnake()));


    }



    //GETTERS AND SETTERS
    public static AppSingleton getInstance() {
        return INSTANCE;
    }


    public List<Game> getAvailabeGames() {
        return availabeGames;
    }

    public void setAvailabeGames(List<Game> availabeGames) {
        this.availabeGames = availabeGames;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
}
