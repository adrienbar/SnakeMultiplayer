package com.snakeindustry.snakemultiplayer.generalApp;


import android.content.Context;

import com.snakeindustry.snakemultiplayer.Snake.GameSnake;
import com.snakeindustry.snakemultiplayer.dummyGame.PacMan;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameThread;
import com.snakeindustry.snakemultiplayer.generalApp.player.DefaultPlayer;
import com.snakeindustry.snakemultiplayer.generalApp.player.Player;
import com.snakeindustry.snakemultiplayer.generalApp.network.finale.server.SocketServerThread;
import com.snakeindustry.snakemultiplayer.generalApp.network.finale.client.Client;
import com.snakeindustry.snakemultiplayer.generalApp.network.finale.server.RoomServer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

//import com.snakeindustry.snakemultiplayer.generalApp.database.PlayerDAO;

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
    private Client client;
    private RoomServer roomServer;
    private SocketServerThread socketServerThread;

    private String lasteIP="";
  //  private PlayerDAO db;

    //APPLICATION'S PARAMETERS
    private AppSingleton() {
        System.out.println("INITIALISATION");
        this.player=new DefaultPlayer();
        this.availabeGames=new ArrayList<Game>();
        this.availabeGames.add(GameSnake.getInstance());
        this.availabeGames.add(PacMan.getInstance());
        this.isServer=false;
        this.currenGameTread=null;
        this.client=null;
        this.roomServer=null;
        this.socketServerThread=null;

        System.out.println("INITIALISATION ok");
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

    //GETTERS AND SETTERS
    public static AppSingleton getInstance() {
        return INSTANCE;
    }

    /**
     * Create Stats for new games
     */
    public void checkStats() {
        for (Game game : this.getAvailabeGames()) {
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

    //METHODES

    public void checkSettings() {
        for (Game game : this.getAvailabeGames()) {
            this.getPlayer().getSettings().createSettingsIfNothing(game.getName());
        }
    }

    /**
     * retrieve the profile from the database
     */
    public void loadName(Context context) {

        FileInputStream intput;
        InputStreamReader isr;
        String playerName;
        BufferedReader reader;

        try {
            intput = context.openFileInput("Player.dat");
            isr = new InputStreamReader(intput);
            reader = new BufferedReader(isr);
            playerName = reader.readLine();
            player.setName(playerName);
            if (intput != null)
                intput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * save the profile in the database
     */

    public void saveName(Context context) {
        FileOutputStream output;
        OutputStreamWriter osw;

        try {
            output = context.openFileOutput("Player.dat", Context.MODE_PRIVATE);
            osw = new OutputStreamWriter(output);
            osw.write(player.getName() + "\n");
            osw.flush();
            if (output != null)
                output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //SAVE SETTINGS
    public void saveSettings(Context context) {
        for (Game game : this.getAvailabeGames()) {
            getPlayer().getSettings().getSettingsForOneGame(game.getName()).saveSettings(context);
        }
    }

    public void loadSettings(Context context) {
        for (Game game : this.getAvailabeGames()) {
            getPlayer().getSettings().getSettingsForOneGame(game.getName()).loadSettings(context);
        }
    }

    //SAVE STATS
    public void saveStats(Context context) {
        for (Game game : this.getAvailabeGames()) {
            getPlayer().getStats().getStatsForOneGame(game).saveStats(context, game.getName() + ".dat");
        }
    }

    public void loadStats(Context context) {
        for (Game game : this.getAvailabeGames()) {
            getPlayer().getStats().getStatsForOneGame(game).loadStats(context, game.getName() + ".dat");
        }
    }

    public void saveProfile(Context context) {
        saveName(context);
        saveStats(context);
        saveSettings(context);
    }

    public void loadProfile(Context context) {
        loadName(context);
        loadStats(context);
        loadSettings(context);
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

    public void setServer(boolean isServer) {
        this.isServer = isServer;
    }

    public GameThread getCurrenGameTread() {
        return currenGameTread;
    }

    public void setCurrenGameTread(GameThread currenGameTread) {
        this.currenGameTread = currenGameTread;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public RoomServer getRoomServer() {
        return roomServer;
    }

    public void setRoomServer(RoomServer roomServer) {
        this.roomServer = roomServer;
    }


    public void closeServerAndConnections() {
        if(socketServerThread!=null){

            boolean retry=true;
            socketServerThread.setRunning(false);
            while (retry){
                try {
                    socketServerThread.join();
                    retry=false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //close sockets
            this.getRoomServer().clean();

            //serverSocket
            retry=true;
            while (retry){
                try {
                    socketServerThread.getServerSocket().close();
                    retry=false;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }


    }

    public String getLasteIP() {
        return lasteIP;
    }

    public void setLasteIP(String lasteIP) {
        this.lasteIP = lasteIP;
    }
}
