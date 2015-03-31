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
    private HashMap<String,Game> gameFromId;

    //APPLICATION'S PARAMETERS
        private AppSingleton() {
        this.availabeGames=new ArrayList<Game>(Arrays.asList(new GameSnake()));
        this.player=new DefaultPlayer();
        this.gameFromId=new HashMap<>();
            this.gameFromId.put(GameSnake.ID_NAME,new GameSnake());

    }







    //METHODES

    /**
     * retrieve the profile from the database
     */
    public void loadProfile() {

      //  System.out.println("AAAAAAAA" + " load profil");


        //TESTS Stats
        this.getPlayer().setName("Test name");
        this.setCurrentGame(this.getAvailabeGames().get(0));
        Stats statsForTest = new StatsHashmap();
        statsForTest.addAPlay(this.getCurrentGame());
        statsForTest.addPlayedTime(this.getCurrentGame(),2);
        statsForTest.addFriend(new DefaultPlayer(new StatsHashmap(), "tintin"),this.getCurrentGame());

        this.getPlayer().setStats(statsForTest);

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
