package com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork;

import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameThread;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameView;

/**
 * Created by Adrien on 10/04/15.
 */
public class LocalClient implements LocalClientI {

    private GameView gameView;


    public LocalClient(GameView gameView) {
        this.gameView=gameView;
    }


    @Override
    public void receive(GameState gameState) {
        this.gameView.draw(gameState);
    }

    @Override
    public void sendCommand(int command) {
        AppSingleton.getInstance().getCurrenGameTread().getServer().getRoom().setPlayerCommand(AppSingleton.getInstance().getPlayer().getName(),command);
        //System.out.println("LOCAL CLIENT command sent : " + command);
    }

}
