package com.snakeindustry.snakemultiplayer.generalApp.network.finale.client;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.GamePlayActivity;
import com.snakeindustry.snakemultiplayer.generalApp.network.finale.server.RoomServer;
import com.snakeindustry.snakemultiplayer.generalApp.network.finale.server.SocketServerThread;

import java.net.Socket;

public class ClientConnectActivity extends ActionBarActivity {

    String dstAddress;
    int dstPort;
    Socket socket;
    String response;


    TextView textResponse;
    EditText editTextAddress;


    Button buttonConnect, buttonClear;

    MyClientTask myClientTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_real);

        editTextAddress = (EditText)findViewById(R.id.address);
        editTextAddress.setText(AppSingleton.getInstance().getLasteIP());
        buttonConnect = (Button)findViewById(R.id.connect);
        buttonClear = (Button)findViewById(R.id.clear);
        textResponse = (TextView)findViewById(R.id.response);

        buttonConnect.setOnClickListener(buttonConnectOnClickListener);

        buttonClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textResponse.setText("");
            }});


        textResponse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals(RoomServer.START_COMMAND)){
                    DistantClientC dc= new DistantClientC(socket);
                    AppSingleton.getInstance().setClient(dc);
                    dc.start();
                    ClientConnectActivity.this.startActivity(new Intent(ClientConnectActivity.this, GamePlayActivity.class));
                }

            }
        });

        TextView gameTitle = (TextView) findViewById(R.id.titlenetworkandsettings);
        gameTitle.setText(AppSingleton.getInstance().getCurrentGame().getName());

        ImageView gameIcon =  (ImageView) findViewById((R.id.gameiconnetworkandsettings));
        gameIcon.setImageResource(AppSingleton.getInstance().getCurrentGame().getIdIcon());

    }

    View.OnClickListener buttonConnectOnClickListener =
            new View.OnClickListener(){

                @Override
                public void onClick(View arg0) {

                    AppSingleton.getInstance().setLasteIP(editTextAddress.getText().toString());

                    myClientTask = new MyClientTask(
                            editTextAddress.getText().toString(),
                            SocketServerThread.SocketServerPORT,
                    ClientConnectActivity.this);
                    myClientTask.execute();

                }};



public void onResume(){
    super.onResume();
    if(AppSingleton.getInstance().getClient() instanceof DistantClientC){
        ((DistantClientC) AppSingleton.getInstance().getClient()).end();
    }

    if(((DistantClientC) AppSingleton.getInstance().getClient()).isAlive()){
        ((DistantClientC) AppSingleton.getInstance().getClient()).end();
    }

}


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
