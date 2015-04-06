package com.snakeindustry.snakemultiplayer.generalApp.player.stats;

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
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;

public class GameStatsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_stats);


        final Game currentGame=AppSingleton.getInstance().getCurrentGame();

        TextView title =(TextView) findViewById(R.id.titlegamestats);
        title.setText(currentGame.getName());

        ImageView icon= (ImageView) findViewById(R.id.iconcurentgame);
        icon.setImageResource(currentGame.getIdIcon());




        ListView listView = (ListView)findViewById(R.id.listViewDetailedStats);
        final ArrayAdapter<OneStats> adapter = new OneStatsListAdapter(this, AppSingleton.getInstance().getPlayer().getStats().getStatsForOneGame(currentGame).getAllStats());
        listView.setAdapter(adapter);

        ListView topFriends = (ListView)findViewById(R.id.topFriendsList);
        final ArrayAdapter<OneStats> adapter2 = new OneStatsListAdapter(this, AppSingleton.getInstance().getPlayer().getStats().getStatsForOneGame(currentGame).getStatsFriends());
        topFriends.setAdapter(adapter2);

        Button reset = (Button) findViewById(R.id.resetForOneGame);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppSingleton.getInstance().getPlayer().getStats().resetStats(currentGame);
                adapter2.notifyDataSetChanged();
                adapter.notifyDataSetChanged();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_stats, menu);
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
