package com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork;

import android.content.Intent;

import com.snakeindustry.snakemultiplayer.Snake.viewAndControl.GamePlayActivity;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrien on 11/04/15.
 */
public class PseudoRoomC implements PseudoRoom {


    private HashMap<String,Integer> playersCommand;
    private boolean joignable;
    private LocalClientI localClient;

    public PseudoRoomC(LocalClientI localClient) {
        this.localClient = localClient;
        this.joignable=true;
        this.playersCommand=new HashMap<String,Integer>();
        playersCommand.put(AppSingleton.getInstance().getPlayer().getName(),null);
    }

    @Override
    public synchronized List<String> getPlayersName() {
        ArrayList<String> playersName=new ArrayList<>();
        playersName.addAll(playersCommand.keySet());
        return playersName;
    }

    @Override
    public synchronized void add(String playerName) {
        if(joignable) {
            if(!playersCommand.keySet().contains(playerName)) {
                playersCommand.put(playerName,null);
            }
            System.out.println("playername Already taken");
            //send command to client
        }
        System.out.println("room no longer joignable");
        //send command to client

    }

    @Override
    public void kick(String playerName) {
        if (playersCommand.containsKey(playerName)){
            playersCommand.remove(playerName);
        }
    }

    @Override
    public void set(boolean joingnable) {
        this.joignable=joingnable;
    }

    @Override
    public void sendToAllClients(GameState gameStateToDraw) {
        localClient.receive(gameStateToDraw);

    }

    @Override
    public synchronized void setPlayerCommand(String player, int command) {
        playersCommand.put(player, command);
       // System.out.println("PSEUDO ROOM setPLayerCommand : player : "+ player+" command : " + command);
    }

    @Override
    public synchronized HashMap<String, Integer> getAndResetPlayersCommand() {
        HashMap<String,Integer> result=new HashMap<>();
        result.putAll(playersCommand);
        playersCommand.clear();
        //System.out.println("PSEUDO ROOM getAndResetPlayersCommand : "+ result.keySet() + "  "+result.values());
        return result;

    }

    @Override
    public void startTheGame() {
        this.joignable=false;
        //send a command to distant client to switch to the GamePlayActivity

        //local
        AppSingleton.getInstance().getCurrentGame().getGameState().configure(getPlayersName());
        AppSingleton.getInstance().setCurrenGameTread(new GameThread());
    }

    @Override
    public void setlocalClient(LocalClientI localClient) {
        this.localClient=localClient;
    }

}
