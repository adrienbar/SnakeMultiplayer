package com.snakeindustry.snakemultiplayer.generalApp;

import com.snakeindustry.snakemultiplayer.Snake.GameSnake;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameThread;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.LocalClient;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.Server;
import com.snakeindustry.snakemultiplayer.generalApp.player.DefaultPlayer;
import com.snakeindustry.snakemultiplayer.generalApp.player.Player;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.Stats;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.StatsGlobalHashmap;

import java.util.ArrayList;
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

    private boolean isServer;
    private GameThread currenGameTread;
    private LocalClient localClient;


    //APPLICATION'S PARAMETERS
    private AppSingleton() {
        this.player=new DefaultPlayer();
        this.availabeGames=new ArrayList<Game>();
        this.availabeGames.add(GameSnake.getInstance());
        this.isServer=false;
        this.currenGameTread=null;
        this.localClient=null;

        //System.out.println("AAAAAAAA "+this.getAvailabeGames());

    }



    public static Game getGameFromName(String gameName) {
        Game game=null;
        for (Game g : AppSingleton.getInstance().getAvailabeGames()) {
            if(g.getName().equals(gameName)) {
                game=g;
            }
        }
        return game;
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
        Stats statsForTest = new StatsGlobalHashmap();
        statsForTest.createStatsIfNothing(this.getCurrentGame());
        statsForTest.addAPlay(this.getCurrentGame());
        statsForTest.addPlayedTime(this.getCurrentGame(),5);
        statsForTest.addFriend("tintin", this.getCurrentGame());
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

    public boolean isServer() {
        return isServer;
    }

    public GameThread getCurrenGameTread() {
        return currenGameTread;
    }

    public void setCurrenGameTread(GameThread currenGameTread) {
        this.currenGameTread = currenGameTread;
    }

    public void setServer(boolean isServer) {
        this.isServer = isServer;
    }
}
