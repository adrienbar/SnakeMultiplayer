package com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork;

import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;

/**
 * Created by Adrien on 10/04/15.
 */
public interface Client {
    public void sendCommand(int command);
    public GameState getLastGameState();
}
