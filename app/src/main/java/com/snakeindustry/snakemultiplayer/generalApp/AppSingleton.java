package com.snakeindustry.snakemultiplayer.generalApp;


import android.content.Context;

import com.snakeindustry.snakemultiplayer.Snake.GameSnake;
//import com.snakeindustry.snakemultiplayer.generalApp.database.PlayerDAO;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameThread;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.SimpleStats;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.LocalClient;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.Server;
import com.snakeindustry.snakemultiplayer.generalApp.player.DefaultPlayer;
import com.snakeindustry.snakemultiplayer.generalApp.player.Player;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.Stats;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.StatsGlobalHashmap;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
  //  private PlayerDAO db;

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
       // this.getPlayer().setName("Test name");
        /*
        this.setCurrentGame(this.getAvailabeGames().get(0));
        Stats statsForTest = new StatsGlobalHashmap();
        statsForTest.createStatsIfNothing(this.getCurrentGame());
        statsForTest.addAPlay(this.getCurrentGame());
        statsForTest.addPlayedTime(this.getCurrentGame(),5);
        statsForTest.addFriend("tintin", this.getCurrentGame());
        this.getPlayer().setStats(statsForTest);
        */

    }

    public void checkSettings() {
        for(Game game : this.getAvailabeGames()){
            this.getPlayer().getSettings().createSettingsIfNothing(game.getName());
        }
    }

    //METHODES

    /**
     * retrieve the profile from the database
     */
    public void loadProfile(Context context) {

        FileInputStream intput;
        InputStreamReader isr;
        String playerName;
        Double statsValue;
        BufferedReader reader;

        try {
            List<SimpleStats> statsListSnake = GameSnake.getInstance().createStats().getStatsAsList();
            intput = context.openFileInput("Player.dat");
            isr = new InputStreamReader(intput);
            reader = new BufferedReader(isr);
            playerName = reader.readLine();
            player.setName(playerName);
            for (SimpleStats stats : statsListSnake) {
                statsValue = Double.parseDouble(reader.readLine());
                stats.add(statsValue);
            }
            if (intput != null)
                intput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * save the profile in the database
     */

    public void saveProfile(Context context) {
        FileOutputStream output;
        OutputStreamWriter osw;
        List<SimpleStats> statsListSnake = player.getStats().getStatsForOneGame(GameSnake.getInstance()).getStatsAsList();

        try {
            output = context.openFileOutput("Player.dat", context.MODE_PRIVATE);
            osw = new OutputStreamWriter(output);
            osw.write(player.getName() + "\n");
            osw.flush();
            for (SimpleStats stats : statsListSnake) {
                osw.write(stats.getValue() + "\n");
                osw.flush();
            }
            if (output != null)
                output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //SAVE SETTINGS
    public void saveSetting(Context context) {
        for (Game game:this.getAvailabeGames()){
            getPlayer().getSettings().getSettingsForOneGame(game.getName()).saveSettings(context);
        }
    }

    public void loadSettings(Context context){
        for( Game game:this.getAvailabeGames()){
            getPlayer().getSettings().getSettingsForOneGame(game.getName()).loadSettings(context);
        }
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
