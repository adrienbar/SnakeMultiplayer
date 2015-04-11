package com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork;

import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameThread;

/**
 * Created by Adrien on 10/04/15.
 */
public interface Server {

    public PseudoRoom getRoom();

    public void setLocalClientI(LocalClientI localClientI);

}
