package com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork;

import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrien on 11/04/15.
 */
public class ServerC implements Server {
    private List<DistantClientI> distantClients;
    private LocalClientI localClientI;
    private GameThread gameThread;
    private PseudoRoom room;


    public ServerC(List<DistantClientI> distantClients, LocalClientI localClientI, GameThread gameThread) {
        this.distantClients = distantClients;
        this.localClientI = localClientI;
        this.gameThread = gameThread;
        this.room=new PseudoRoomC(localClientI);
    }

    public ServerC(LocalClientI localClientI, GameThread gameThread){
        this(new ArrayList<DistantClientI>(),localClientI,gameThread);
    }



    public List<DistantClientI> getDistantClients() {
        return distantClients;
    }

    public void setDistantClients(List<DistantClientI> distantClients) {
        this.distantClients = distantClients;
    }

    public LocalClientI getLocalClientI() {
        return localClientI;
    }

    @Override
    public PseudoRoom getRoom() {
        return this.room;
    }

    public void setLocalClientI(LocalClientI localClientI) {
        this.localClientI = localClientI;
        this.getRoom().setlocalClient(localClientI);
    }

    public GameThread getGameThread() {
        return gameThread;
    }

    public void setGameThread(GameThread gameThread) {
        this.gameThread = gameThread;
    }





}
