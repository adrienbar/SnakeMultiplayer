package com.snakeindustry.snakemultiplayer.generalApp.game;

import android.os.SystemClock;
import android.util.Log;

import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.Server;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.ServerC;

import java.util.HashMap;

/**
 * Created by Adrien on 28/03/15.
 */
public class GameThread extends Thread{

    private GameState gameState;
    private int refreshInterval;
    private Server server;
    private boolean running;

    public GameThread() {
        super();
        this.server = new ServerC(null,this);
        this.gameState = AppSingleton.getInstance().getCurrentGame().getGameState();
        this.refreshInterval = 2000; //ms
        this.running=true;
    }


    public void run() {
        //INITIALISATION
        //nothing to do yet





        //GAME LOOP
        while ((!this.getGameState().isGameOver())&this.isRunning()) {

            long startTime = SystemClock.currentThreadTimeMillis();
            long time0=SystemClock.elapsedRealtime();


            //UPDATE
            this.getGameState().nextStep(this.getServer().getRoom().getAndResetPlayersCommand());



            //send GameStatToClients
            //at the reception, clients are supposed to refresh the View
            this.getServer().getRoom().sendToAllClients(this.getGameState());

            // DRAW
            //done by the client


            //SLEEP TIME
            // to have a constant game speed on every kind of phone
            int sleepTime = (int) (this.getRefreshInterval() - (SystemClock.currentThreadTimeMillis() - startTime));
            if (sleepTime > 0) {
                SystemClock.sleep(sleepTime);
            }
            else {
                Log.d("GameThread", "device too slow : refreshTime exceded by " + -sleepTime + " ms");
            }
        }



        //DRAW GAME OVER



    }



    //Getters AND Setters

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(int refreshInterval) {
        this.refreshInterval = refreshInterval;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
