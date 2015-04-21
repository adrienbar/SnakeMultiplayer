package com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.finale.client;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.GamePlayActivity;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.finale.RoomServer;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.finale.SocketServerThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnectActivity extends ActionBarActivity {

    String dstAddress;
    int dstPort;
    Socket socket;
    String response;


    TextView textResponse;
    EditText editTextAddress, editTextPort;


    Button buttonConnect, buttonClear;

    MyClientTask myClientTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_real);

        editTextAddress = (EditText)findViewById(R.id.address);
        editTextAddress.setText("192.168.56.101");
        editTextPort = (EditText)findViewById(R.id.port);
        editTextPort.setText(""+ SocketServerThread.SocketServerPORT);
        buttonConnect = (Button)findViewById(R.id.connect);
        buttonClear = (Button)findViewById(R.id.clear);
        textResponse = (TextView)findViewById(R.id.response);

        buttonConnect.setOnClickListener(buttonConnectOnClickListener);

        buttonClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textResponse.setText("");
            }});




    }

    View.OnClickListener buttonConnectOnClickListener =
            new View.OnClickListener(){

                @Override
                public void onClick(View arg0) {

                    System.out.println("MY CLIENT TASK clik");

                    PrintWriter out;
                    BufferedReader in;

                    try {
                        dstAddress=(String) editTextAddress.getText().toString();
                        dstPort=  Integer.parseInt(editTextPort.getText().toString());

                        System.out.println("MY CLIENT TASK "+dstAddress +":"+dstPort);
                        socket = new Socket(dstAddress, dstPort);
                        System.out.println("MY CLIENT TASK socket ok");
                        out = new PrintWriter(socket.getOutputStream());
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


                        out.println(AppSingleton.getInstance().getPlayer().getName());
                        out.println(AppSingleton.getInstance().getCurrentGame().getName());
                        out.flush();

                        System.out.println("MY CLIENT TASK out ok");


                        response=in.readLine();
                        textResponse.setText(response);
                        System.out.println("MY CLIENT TASK "+response);

                        if (response!=null&&response.equals(RoomServer.START_COMMAND)) {

                            DistantClientC dc= new DistantClientC(socket);
                            AppSingleton.getInstance().setClient(dc);
                            dc.start();
                            ClientConnectActivity.this.startActivity(new Intent(ClientConnectActivity.this, GamePlayActivity.class));
                        }


                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                    /*



                    myClientTask = new MyClientTask(
                            editTextAddress.getText().toString(),
                            Integer.parseInt(editTextPort.getText().toString()),
                    ClientRealActivityCopy.this);
                    myClientTask.execute();
*/
                }};




    public TextView getTextResponse() {
        return textResponse;
    }

    public void setTextResponse(TextView textResponse) {
        this.textResponse = textResponse;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
