package com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork;

import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameThread;

/**
 * Created by Adrien on 10/04/15.
 */
public interface LocalClientI extends Client {
    public GameThread getGameThread();

    /**
     * draw the gameState ont the view
     * @param gameState
     */
    public void receive(GameState gameState);
}
