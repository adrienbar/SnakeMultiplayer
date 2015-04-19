package com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.Snake.viewAndControl.GamePlayActivity;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.mainActivity.GameListAdaptateur;
import com.snakeindustry.snakemultiplayer.generalApp.mainActivity.GameListListener;
import com.snakeindustry.snakemultiplayer.generalApp.player.Player;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

public class RoomActivity extends ActionBarActivity {

    TextView ipAndPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        TextView gameTitle = (TextView) findViewById(R.id.titlenetworkandsettings);
        gameTitle.setText(AppSingleton.getInstance().getCurrentGame().getName());

        ImageView gameIcon =  (ImageView) findViewById((R.id.gameiconnetworkandsettings));
        gameIcon.setImageResource(AppSingleton.getInstance().getCurrentGame().getIdIcon());

        ipAndPort = (TextView) findViewById(R.id.ipAndPort);

        //list of available games

        ListView listView = (ListView) findViewById(R.id.players);

        List<String> playersName =AppSingleton.getInstance().getCurrenGameTread().getServer().getRoom().getPlayersName();
        int i= playersName.indexOf(AppSingleton.getInstance().getPlayer().getName());
        playersName.set(i,playersName.get(i) + " (Me!)");


        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,playersName);

        // Assign adapter to ListView
        listView.setAdapter(adapter);
        //listView.setOnItemClickListener();

        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppSingleton.getInstance().getCurrenGameTread().getServer().getRoom().startTheGame();
                v.getContext().startActivity(new Intent(v.getContext(), GamePlayActivity.class));
            }
        });

        Thread socketServerThread = new Thread(new SocketServerThread());
        socketServerThread.start();


    }

    private class SocketServerThread extends Thread {

        static final int SocketServerPORT = 32514;

        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(SocketServerPORT);

                RoomActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        ipAndPort.setText("ip : " + getIpAddress() + " / port : " + SocketServerPORT);
                    }
                });

                while (true) {
                    Socket socket = serverSocket.accept();

                    ByteArrayOutputStream byteArrayOutputStream =
                            new ByteArrayOutputStream(1024);
                    byte[] buffer = new byte[1024];

                    int bytesRead;
                    InputStream inputStream = socket.getInputStream();

                    String name = "";

                    while ((bytesRead = inputStream.read(buffer)) != -1){
                        byteArrayOutputStream.write(buffer, 0, bytesRead);
                        name += byteArrayOutputStream.toString("UTF-8");
                    }

                    OutputStream outputStream = socket.getOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);
                    printStream.print("Successfully add to the room");
                    printStream.close();


                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
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
                        ip += inetAddress.getHostAddress() + "\n";
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_room, menu);
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
}
