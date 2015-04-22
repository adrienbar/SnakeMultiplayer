package com.snakeindustry.snakemultiplayer.generalApp.network.finale.client;

import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;

/**
 * Created by Adrien on 10/04/15.
 */
public interface LocalClientI extends Client {

    /**
     * draw the gameState ont the view
     * @param gameState
     */
    public void receive(GameState gameState);
}
