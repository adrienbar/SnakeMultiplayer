package com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.finale.client;

import android.content.Context;

import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameView;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.DistantClientI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by Adrien on 21/04/15.
 */
public class DistantClientC extends Thread implements DistantClientI {
    private GameView gameView;
    private String command;
    private Socket socket;
    private PrintWriter out;
    private ObjectInputStream in;
    private GameState lastGameState;
    private boolean running;
    private MyClientTask connectingTool;




    public DistantClientC(Socket socket){
        this.socket=socket;

        ArrayList a = new ArrayList<String>();
        a.add(AppSingleton.getInstance().getPlayer().getName());


        AppSingleton.getInstance().getCurrentGame().getGameState().configure(a);
        lastGameState=AppSingleton.getInstance().getCurrentGame().getGameState();
        running=true;
    }

    public DistantClientC(){
        this(null);
    }


    @Override
    public void sendCommand(String command) {
        this.command=command;
    }




    @Override
    public void setView(GameView view) {
        this.gameView=view;
    }

    @Override
    public void startServer(Context context) {

    }


    @Override
    public void run() {
        super.run();
        while (running){
            System.out.println("DISTANT SERVER Running");
            try {
                System.out.println("DISTANT SERVER try 1");

                out = new PrintWriter(socket.getOutputStream());

                System.out.println("DISTANT SERVER out");
                in = new ObjectInputStream(socket.getInputStream());

                System.out.println("DISTANT SERVER in,out");
                try {
                    System.out.println("DISTANT SERVER gamestate ready to be read");
                    GameState gameState = (GameState) in.readObject();
                    System.out.println("DISTANT SERVER game state read");
                    if(gameState!=null) {
                        this.setLastGameState(gameState);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println("DISTANT SERVER game Sate received");
                System.out.println("DISTANT SERVER game Sate received "+this.gameView);
                System.out.println("DISTANT SERVER game Sate received");
                if(gameView!=null) {
                    this.gameView.draw(getLastGameState());
                }

                System.out.println("DISTANT SERVER GameStat Drawn");

                out.println(this.getCommand());
                System.out.println("DISTANT SERVER COMMANDE IN out");
                this.setCommand(null);
                System.out.println("DISTANT SERVER COMMAND RESETED");
                out.flush();
                System.out.println("DISTANT SERVER flushed");


            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public synchronized String getCommand() {
        return command;
    }

    public synchronized void setCommand(String command) {
        this.command = command;
    }

    public synchronized Socket getSocket() {
        return socket;
    }

    public synchronized void setSocket(Socket socket) {
        this.socket = socket;
    }

    public synchronized GameState getLastGameState() {
        return lastGameState;
    }


    public void end() {
        try {
            if(getSocket()!=null){
                getSocket().close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void setLastGameState(GameState lastGameState) {
        this.lastGameState = lastGameState;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void connect(String serverAddress) {

    }
}
