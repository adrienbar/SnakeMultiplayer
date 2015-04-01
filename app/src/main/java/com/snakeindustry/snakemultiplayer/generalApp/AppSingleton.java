package com.snakeindustry.snakemultiplayer.generalApp;

import com.snakeindustry.snakemultiplayer.Snake.GameSnake;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.player.DefaultPlayer;
import com.snakeindustry.snakemultiplayer.generalApp.player.Player;
import com.snakeindustry.snakemultiplayer.generalApp.player.Stats;
import com.snakeindustry.snakemultiplayer.generalApp.player.StatsHashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
    private Player player;


    //APPLICATION'S PARAMETERS
    private AppSingleton() {
        this.player=new DefaultPlayer();
        this.availabeGames=new ArrayList<Game>();
        this.availabeGames.add(new GameSnake());

        //System.out.println("AAAAAAAA "+this.getAvailabeGames());

    }

    /**
     * Create Stats for new games
     */
    public void checkStats() {
       for(Game game : this.getAvailabeGames()) {
           this.getPlayer().getStats().createStatsIfNothing(game);
      }

        //TESTS Stats
        this.getPlayer().setName("Test name");
        this.setCurrentGame(this.getAvailabeGames().get(0));
        Stats statsForTest = new StatsHashmap();
        statsForTest.addAPlay(this.getCurrentGame());
        statsForTest.addPlayedTime(this.getCurrentGame(),2);
        statsForTest.addFriend(new DefaultPlayer(new StatsHashmap(), "tintin"),this.getCurrentGame());
        this.getPlayer().setStats(statsForTest);


    }

    //METHODES

    /**
     * retrieve the profile from the database
     */
    public void loadProfile() {

    }

    /**
     * save the profile in the database
     */

    public void saveProfile() {

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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
