package com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.finale;

import android.content.Context;

import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.finale.client.LocalClientI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrien on 21/04/15.
 */
public class RoomServer {


    //private ArrayList<String> players;
    private ArrayList<String> messages;
    //private ServerOC serverOC;
    // private List<Socket> socketList;
    private HashMap<String,Socket> playersSocket;
    private int limitePlayers;
    private String localPlayerCommand;

    public static  final String START_COMMAND="START";



    public RoomServer() {
        // players=new ArrayList<>();
        messages=new ArrayList<>();
        //socketList=new ArrayList<>();
        playersSocket=new HashMap<>();
        limitePlayers=4;

    }

    public void addPlayer(String name){
        this.getDistantPlayers().add(name);
      //  this.getServerOC().getAdapter().notifyDataSetChanged();
    }

    public void clean() {

        this.setMessages(new ArrayList<String>());

        for (Socket s:getPlayersSocket().values()){
            if(s!=null){
                boolean retry=true;
                while (retry&!s.isClosed()){
                    try {
                        s.close();
                        retry=false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }


        this.setPlayersSocket(new HashMap<String, Socket>());
    }

    public boolean alreadyInTheRoom(String name){
        return this.getDistantPlayers().contains(name);
    }


    public synchronized List<String> getDistantPlayers(){

        ArrayList arrayList= new ArrayList();
        arrayList.addAll(this.getPlayersSocket().keySet());
        return arrayList;
    }

    public synchronized List<String> getAllPlayer(){
        ArrayList<String> a =  new ArrayList<>();
        a.addAll(getDistantPlayers());
        a.add(AppSingleton.getInstance().getPlayer().getName());
        return a;
    }



    public synchronized ArrayList<String> getMessages() {
        return messages;
    }

    public synchronized void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }




    public synchronized void addMessage(String message){
        this.getMessages().add(message);
//        this.getServerOC().getAdapter().notifyDataSetChanged();
    }



 //   public synchronized ServerOC getServerOC() {
  //      return serverOC;
   // }

    //public synchronized void setServerOC(ServerOC serverOC) {
    //    this.serverOC = serverOC;
   // }



    public Collection<Socket> getSocketList() {
        return playersSocket.values();
    }



    public synchronized boolean addSocket(String playerName, Socket socket){
        boolean addPosssible=this.getPlayersSocket().size()<limitePlayers;
        if(addPosssible) {
            this.getPlayersSocket().put(playerName,socket);
        }
        return addPosssible;
    }

    public HashMap<String, Socket> getPlayersSocket() {
        return playersSocket;
    }

    public void setPlayersSocket(HashMap<String, Socket> playersSocket) {
        this.playersSocket = playersSocket;
    }

    public void removeClosedSocket(){
        HashMap<String,Socket> hashMap = new HashMap<>();
        for (String player : this.getPlayersSocket().keySet()){
            if(!this.getPlayersSocket().get(player).isClosed()){
                hashMap.put(player,this.getPlayersSocket().get(player));
            }
        }
        setPlayersSocket(hashMap);
    }



    public void sendGameStateToAllClients(GameState gameState){
        // System.out.println("GAMESTATE READY TO BE SENT");
        for(String player : this.getDistantPlayers()){
            //   System.out.println("GAMESTATE READY TO BE SENT to "+player);
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(this.getPlayersSocket().get(player).getOutputStream());
                //   System.out.println("GAMESTATE READY TO BE SENT to "+player+"Soket ok");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.writeObject(gameState);
                //  System.out.println("GAMESTATE READY TO BE SENT to "+player+"Soket ok"+" Written");
                out.flush();
                //   System.out.println("GAMESTATE READY TO BE SENT to "+player+"Soket ok"+" Flush");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ((LocalClientI) AppSingleton.getInstance().getClient()).receive(gameState);
        //System.out.println("GAMESTATE SENT");
    }





    public HashMap<String,String> getAndResetPlayersCommands(){
        HashMap<String,String> playersCommandes = new HashMap<>();
        // System.out.println("COMMANDS READY TO BE RECEIVED");
        for (String player : this.getDistantPlayers()){

            //     System.out.println("COMMANDS READY TO BE RECEIVED from " +player);
            BufferedReader in = null;
            String command="";
            try {
                // in = new ObjectInputStream(this.getPlayersSocket().get(player).getInputStream());

                in = new BufferedReader(new InputStreamReader(this.getPlayersSocket().get(player).getInputStream()));

                //  System.out.println("COMMANDS READY TO BE RECEIVED from " +player+ " inputStream ok");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                // System.out.println("COMMANDS READY TO BE RECEIVED from " +player+ " ready to read");
                command =  in.readLine();
                //  System.out.println("COMMANDS READY TO BE RECEIVED from " +player+ " read !");

            } catch (IOException e) {
                e.printStackTrace();
            }

            playersCommandes.put(player,command);

        }

        playersCommandes.put(AppSingleton.getInstance().getPlayer().getName(),getLocalPlayerCommand());
        //  System.out.println("COMMANDS RECEIVED");
        return playersCommandes;
    }

    public synchronized String getLocalPlayerCommand() {
        return localPlayerCommand;
    }

    public synchronized void setLocalPlayerCommand(String localPlayerCommand) {
        this.localPlayerCommand = localPlayerCommand;
    }



    public void startGame(Context context){

        PrintWriter out;
        for (Socket s :this.getPlayersSocket().values()){
            try {
                out=new PrintWriter(s.getOutputStream());
                out.println(START_COMMAND);
                out.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        AppSingleton.getInstance().getCurrentGame().getGameState().configure(getAllPlayer());
        AppSingleton.getInstance().getClient().startServer(context);
    }


}
