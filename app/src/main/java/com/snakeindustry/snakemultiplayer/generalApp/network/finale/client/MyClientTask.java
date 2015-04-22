package com.snakeindustry.snakemultiplayer.generalApp.network.finale.client;

import android.os.AsyncTask;

import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;

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
        dstPort = port;
        this.clientConnectActivity = clientConnectActivity;
    }



    @Override
    protected Void doInBackground(Void... arg0) {

        boolean hostOk=true;
        try {
            socket = new Socket(dstAddress, dstPort);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            response=e.getMessage();
            hostOk=false;
        } catch (IOException e) {
            e.printStackTrace();
            response=e.getMessage();
            hostOk=false;
        }

        if(hostOk){
            try {
                out = new PrintWriter(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("MY CLIENT TASK in-out");
                out.println(AppSingleton.getInstance().getPlayer().getName());
                out.println(AppSingleton.getInstance().getCurrentGame().getName());
                out.flush();
                System.out.println("MY CLIENT TASK out");

               // clientConnectActivity.textResponse.setText("Request sent, waiting for server response");
                response=in.readLine();
                System.out.println("MY CLIENT TASK "+response);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        return null;
    }



    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        clientConnectActivity.setSocket(socket);
        clientConnectActivity.textResponse.setText(response);


    }
}
