package com.snakeindustry.snakemultiplayer.generalApp.network.old;

import com.snakeindustry.snakemultiplayer.generalApp.network.finale.client.LocalClientI;

/**
 * Created by Adrien on 10/04/15.
 */
public interface Server {

    public PseudoRoom getRoom();

    public void setLocalClientI(LocalClientI localClientI);

}
