package com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.finale.client;

import android.content.Context;

import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameView;

/**
 * Created by Adrien on 10/04/15.
 */
public interface Client {
    public void sendCommand(String command);
    public GameState getLastGameState();
    public void setView(GameView gameView);

    public void startServer(Context context);


}
