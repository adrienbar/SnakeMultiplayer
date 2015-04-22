package com.snakeindustry.snakemultiplayer.generalApp.network.old;

import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.generalApp.network.finale.client.LocalClientI;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrien on 11/04/15.
 */
public interface PseudoRoom {

    public List<String> getPlayersName();

    public void add(String playerName);

    public void kick(String playerName);

    public void set(boolean joingnable);

    public void sendToAllClients(GameState gameStateToDraw);

    public void setPlayerCommand(String player, int command);

    public HashMap<String,Integer> getAndResetPlayersCommand();

    public void startTheGame();

    public void setlocalClient(LocalClientI localClient);



}
