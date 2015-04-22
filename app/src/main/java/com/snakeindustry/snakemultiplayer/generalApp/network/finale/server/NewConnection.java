package com.snakeindustry.snakemultiplayer.generalApp.network.finale.server;

import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Adrien on 22/04/15.
 */
public class NewConnection extends Thread {

    private PrintWriter out;
    private BufferedReader in;
    private String serverLog="";
    private String name="";
    private ServerRoomActivity parentActivity;
    Socket socket;


    public NewConnection(ServerRoomActivity parentActivity, Socket socket) {
        this.parentActivity=parentActivity;
        this.socket=socket;
    }


    @Override
    public  void run() {

        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            name=in.readLine();
            String game=in.readLine();

            String responseToClient="";
            serverLog=name+"@"+socket.getInetAddress()+" is connected";
            boolean canJoin=true;


            if(AppSingleton.getInstance().getRoomServer().getAllPlayer().contains(name)){
                canJoin=false;
                responseToClient="Sorry, username Already taken";
                serverLog=name+"@"+socket.getInetAddress()+"username already taken";
                if(AppSingleton.getInstance().getRoomServer().getDistantPlayers().contains(name)&&AppSingleton.getInstance().getRoomServer().getPlayersSocket().get(name).getInetAddress().equals(socket.getInetAddress())){
                    responseToClient="You are already in !";
                    serverLog=name+"@"+socket.getInetAddress()+"already in";
                }
            }

            if (!game.equals(AppSingleton.getInstance().getCurrentGame().getName())){
                canJoin=false;
                responseToClient="Sorry, the host wants to play "+AppSingleton.getInstance().getCurrentGame().getName() + " and not "+game;
                serverLog=name+"@"+socket.getInetAddress()+"would like to play"+game;
            }


            if(canJoin){
                if(AppSingleton.getInstance().getRoomServer().addSocket(name,socket)){
                    responseToClient=RoomServer.START_COMMAND;

                }
                else{
                    responseToClient="Sorry, the room is full";
                }
            }

            out.println(responseToClient);
            out.flush();


            parentActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    parentActivity.getMsg().setText(serverLog);
                    parentActivity.notifyAdapter();
                }
            });
        }

        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        }
    }


