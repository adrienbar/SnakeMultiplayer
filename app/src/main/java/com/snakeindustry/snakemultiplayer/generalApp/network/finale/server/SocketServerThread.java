package com.snakeindustry.snakemultiplayer.generalApp.network.finale.server;

import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Adrien on 21/04/15.
 */
public class SocketServerThread extends Thread {

    public static final int SocketServerPORT = 8080;
    private ServerSocket serverSocket;
    private boolean running;

    ServerRoomActivity parentActivity;



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

                    NewConnection newConnection = new NewConnection(parentActivity,socket);
                    newConnection.run();
                }


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

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
