package com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork;

import android.content.Context;

import com.snakeindustry.snakemultiplayer.Snake.model.SnakeGameState;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameThread;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameView;

/**
 * Created by Adrien on 10/04/15.
 */
public class LocalClient implements LocalClientI {

    private GameView gameView;
    private GameState lastGameState;


    public LocalClient(GameView gameView, GameState gameState) {
        this.gameView=gameView;
        lastGameState=gameState;
    }


    @Override
    public void receive(GameState gameState) {
        this.lastGameState=gameState;
if (gameView!=null){
    this.gameView.draw(gameState);

}
        else {
    System.out.println("VIEW NULL !!!!");
        }
    }

    @Override
    public void sendCommand(String command) {
        AppSingleton.getInstance().getRoomServer().setLocalPlayerCommand(command);
        //System.out.println("LOCAL CLIENT command sent : " + command);
    }


    @Override
    public GameState getLastGameState() {
        return lastGameState;
    }

    @Override
    public void setView(GameView gameView) {
        this.gameView=gameView;
    }

    @Override
    public void startServer(Context context) {

    }

}
