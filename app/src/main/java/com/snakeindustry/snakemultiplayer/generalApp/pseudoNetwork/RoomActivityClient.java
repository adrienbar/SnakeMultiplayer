package com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;



public class RoomActivityClient extends ActionBarActivity {

        TextView textResponse;
        EditText editTextAddress, editTextPort;
        Button buttonConnect, buttonClear;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_room_client);

            editTextAddress = (EditText)findViewById(R.id.address);
            editTextPort = (EditText)findViewById(R.id.port);
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
                        MyClientTask myClientTask = new MyClientTask(
                                editTextAddress.getText().toString(),
                                Integer.parseInt(editTextPort.getText().toString()));
                        myClientTask.execute();
                    }};

        public class MyClientTask extends AsyncTask<Void, Void, Void> {

            String dstAddress;
            int dstPort;
            String msg = "";

            MyClientTask(String addr, int port){
                dstAddress = addr;
                dstPort = port;
            }

            @Override
            protected Void doInBackground(Void... arg0) {

                Socket socket = null;

                try {
                    socket = new Socket(dstAddress, dstPort);

                    OutputStream outputStream = socket.getOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);
                    printStream.print(AppSingleton.getInstance().getPlayer().getName());
                    printStream.close();

                    ByteArrayOutputStream byteArrayOutputStream =
                            new ByteArrayOutputStream(1024);
                    byte[] buffer = new byte[1024];

                    int bytesRead;
                    InputStream inputStream = socket.getInputStream();

                    while ((bytesRead = inputStream.read(buffer)) != -1){
                        byteArrayOutputStream.write(buffer, 0, bytesRead);
                        msg += byteArrayOutputStream.toString("UTF-8");
                    }

                } catch (UnknownHostException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    msg = "UnknownHostException: " + e.toString();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    msg = "IOException: " + e.toString();
                }
                finally{
                    if(socket != null){
                        try {
                            socket.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                textResponse.setText(msg);
                super.onPostExecute(result);
            }

        }

    }