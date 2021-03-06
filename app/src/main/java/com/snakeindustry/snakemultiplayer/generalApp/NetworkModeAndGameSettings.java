package com.snakeindustry.snakemultiplayer.generalApp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameThread;
import com.snakeindustry.snakemultiplayer.generalApp.mainActivity.ButtonController;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.StatsOneGameActivity;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.SimpleStats;
import com.snakeindustry.snakemultiplayer.generalApp.network.finale.client.LocalClient;
import com.snakeindustry.snakemultiplayer.generalApp.network.finale.server.RoomServer;
import com.snakeindustry.snakemultiplayer.generalApp.network.finale.server.ServerRoomActivity;
import com.snakeindustry.snakemultiplayer.generalApp.network.finale.client.ClientConnectActivity;
import com.snakeindustry.snakemultiplayer.generalApp.network.finale.client.DistantClientC;

public class NetworkModeAndGameSettings extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_mode_and_game_settings);

        TextView gameTitle = (TextView) findViewById(R.id.titlenetworkandsettings);
        gameTitle.setText(AppSingleton.getInstance().getCurrentGame().getName());

        ImageView gameIcon =  (ImageView) findViewById((R.id.gameiconnetworkandsettings));
        gameIcon.setImageResource(AppSingleton.getInstance().getCurrentGame().getIdIcon());

        Button stats= (Button) findViewById(R.id.statsbutton);
        stats.setOnClickListener(new ButtonController(StatsOneGameActivity.class));

        Button settings = (Button) findViewById(R.id.settings);
        settings.setOnClickListener(new ButtonController(AppSingleton.getInstance().getCurrentGame().getSettingsActivity()));

        Button createGame = (Button) findViewById(R.id.createagame);
        createGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppSingleton.getInstance().setServer(true);
                AppSingleton.getInstance().setCurrenGameTread(new GameThread());
                AppSingleton.getInstance().setRoomServer(new RoomServer());
                AppSingleton.getInstance().setClient(new LocalClient(null,AppSingleton.getInstance().getCurrentGame().getGameState()));

                Intent myIntent = new Intent(v.getContext(), ServerRoomActivity.class);
                v.getContext().startActivity(myIntent);
            }
        });

        Button joinAgame = (Button) findViewById(R.id.joinagame);
        joinAgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppSingleton.getInstance().setServer(false);
                AppSingleton.getInstance().setCurrenGameTread(new GameThread());
                AppSingleton.getInstance().setClient(new DistantClientC());
                Intent myIntent = new Intent(v.getContext(), ClientConnectActivity.class);
                v.getContext().startActivity(myIntent);
            }
        });



    }


    @Override
    public void onResume(){
        super.onResume();
        TextView bestScore = (TextView) findViewById(R.id.bestScore);
        SimpleStats bestScore1 = AppSingleton.getInstance().getPlayer().getStats().getStatsForOneGame(AppSingleton.getInstance().getCurrentGame()).getBestScore();
        bestScore.setText(bestScore1.getDescription() + " " + bestScore1.getValue() + " "+ bestScore1.getUnit());
        AppSingleton.getInstance().getCurrentGame().resetGameState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_network_mode_and_game_settings, menu);
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
