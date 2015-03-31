package com.snakeindustry.snakemultiplayer.generalApp;

import com.snakeindustry.snakemultiplayer.generalApp.game.Game;

/**
 * Created by Adrien on 31/03/15.
 */
public class AppSingleton {
    private static final AppSingleton INSTANCE = new AppSingleton();

    private Game currentGame;

    private AppSingleton() {
    }

    public static AppSingleton getInstance() {
        return INSTANCE;
    }


    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
}
