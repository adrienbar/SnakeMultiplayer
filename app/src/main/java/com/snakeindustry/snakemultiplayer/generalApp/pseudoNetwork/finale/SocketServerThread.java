package com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.finale;

import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Adrien on 21/04/15.
 */
public class SocketServerThread extends Thread {

    public static final int SocketServerPORT = 8080;
    private ServerSocket serverSocket;
    private boolean running;

    private String serverLog="";
    private String name="";
    ServerRoomActivity parentActivity;

    private PrintWriter out;
    private BufferedReader in;

    private boolean roomOpen;
    private boolean startGame;

    public SocketServerThread(ServerRoomActivity parentActivity){
        super();
        this.parentActivity=parentActivity;
        roomOpen=true;
        startGame=false;
        running=false;


    }

    @Override
    public void run() {

        this.setRunning(true);

        while (running){

            try {
                serverSocket = new ServerSocket(SocketServerPORT);

                while (!startGame&& AppSingleton.getInstance().isServer()) {
                    Socket socket = serverSocket.accept();

                    AppSingleton.getInstance().getRoomServer().removeClosedSocket();

                    out = new PrintWriter(socket.getOutputStream());
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    name=in.readLine();
                    String game=in.readLine();
                    System.out.println("SERVERTHREAD Read");


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

                    System.out.println("SERVERTHREAD ready to send "+responseToClient);

                    out.println(responseToClient);
                    out.flush();
                    System.out.println("SERVERTHREAD flushed");


                    //retrieveInformationFromSocket(socket);
                    this.run();
                    updateParentActivity();
                    // respondToClient(socket);

                }


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }




    private void updateParentActivity() {
        parentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                parentActivity.getMsg().setText(serverLog);
                parentActivity.notifyAdapter();
            }
        });

    }


    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
}
