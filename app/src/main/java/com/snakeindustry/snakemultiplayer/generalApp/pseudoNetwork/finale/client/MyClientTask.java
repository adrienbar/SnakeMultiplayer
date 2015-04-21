package com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.finale.client;

import android.os.AsyncTask;

import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.finale.RoomServer;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.finale.SocketServerThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Adrien on 21/04/15.
 */
public class MyClientTask extends AsyncTask<Void,Void,Void> {

    String dstAddress;
    int dstPort;
    String response = "";
    ClientConnectActivity clientConnectActivity;
    Socket socket;


    private PrintWriter out;
    private BufferedReader in;

    MyClientTask(String addr, int port,ClientConnectActivity clientConnectActivity){
        dstAddress = addr;
        dstPort = SocketServerThread.SocketServerPORT;
        this.clientConnectActivity = clientConnectActivity;
    }



    @Override
    protected Void doInBackground(Void... arg0) {

        try {
            socket = new Socket(dstAddress, dstPort);

            System.out.println("MY CLIENT TASK "+dstAddress+":"+dstPort);

            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            out.println(AppSingleton.getInstance().getPlayer().getName());
            out.println(AppSingleton.getInstance().getCurrentGame().getName());
            out.flush();

            response=in.readLine();
            System.out.println("MY CLIENT TASK "+response);

            if(response.equals(RoomServer.START_COMMAND)){
                DistantClientC dc= new DistantClientC(socket);
                dc.start();
                AppSingleton.getInstance().setClient(new DistantClientC(socket));
            }



        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        clientConnectActivity.textResponse.setText(response);
        clientConnectActivity.setSocket(socket);
    }
}
