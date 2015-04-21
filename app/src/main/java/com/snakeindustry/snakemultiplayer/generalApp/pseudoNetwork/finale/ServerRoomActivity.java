package com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.finale;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.GamePlayActivity;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.finale.client.ClientConnectActivity;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;




public class ServerRoomActivity extends ActionBarActivity {

    private TextView info, infoip,msg;
    private String message = "";
    private ServerSocket serverSocket;

    private RoomServer roomServer;
    private ArrayAdapter adapter;
    private ListView playersListVew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_room);

        roomServer=new RoomServer();

        infoip = (TextView) findViewById(R.id.infoip);
        infoip.setText(getIpAddress());


        msg = (TextView) findViewById(R.id.textView);


        playersListVew = (ListView) findViewById(R.id.playersListView);
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,getRoomServer().getDistantPlayers().toArray());
        playersListVew.setAdapter(adapter);

        ListView players= (ListView) findViewById(R.id.playersListView);
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, AppSingleton.getInstance().getRoomServer().getAllPlayer());
        players.setAdapter(adapter);


        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppSingleton.getInstance().getCurrentGame().getGameState().configure(AppSingleton.getInstance().getRoomServer().getAllPlayer());
                Intent myIntent = new Intent(v.getContext(), GamePlayActivity.class);
                v.getContext().startActivity(myIntent);
                AppSingleton.getInstance().getCurrenGameTread().start();
                //AppSingleton.getInstance().getRoomServer().startGame(v.getContext());

            }
        });



        Thread socketServerThread = new Thread(new SocketServerThread(this));
        socketServerThread.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_server_room, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResume(){
        super.onResume();
        ListView players= (ListView) findViewById(R.id.playersListView);
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, AppSingleton.getInstance().getRoomServer().getAllPlayer());
        players.setAdapter(adapter);
    }



    private String getIpAddress() {
        String ip = "";
        try {
            Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (enumNetworkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = enumNetworkInterfaces
                        .nextElement();
                Enumeration<InetAddress> enumInetAddress = networkInterface
                        .getInetAddresses();
                while (enumInetAddress.hasMoreElements()) {
                    InetAddress inetAddress = enumInetAddress.nextElement();

                    if (inetAddress.isSiteLocalAddress()) {
                        ip += "SiteLocalAddress: "
                                + inetAddress.getHostAddress() + "\n";
                    }

                }

            }

        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ip += "Something Wrong! " + e.toString() + "\n";
        }

        return ip;
    }


    public void addPlayer(String name){
        this.getRoomServer().addPlayer(name);
        adapter.notifyDataSetChanged();
    }

    public RoomServer getRoomServer() {
        return roomServer;
    }

    public void setRoomServer(RoomServer roomServer) {
        this.roomServer = roomServer;
    }

    public TextView getInfo() {
        return info;
    }

    public void setInfo(TextView info) {
        this.info = info;
    }

    public TextView getMsg() {
        return msg;
    }

    public void setMsg(TextView msg) {
        this.msg = msg;
    }


    public void notifyAdapter(){
        ListView players= (ListView) findViewById(R.id.playersListView);
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, AppSingleton.getInstance().getRoomServer().getAllPlayer());
        players.setAdapter(adapter);
    }
}
