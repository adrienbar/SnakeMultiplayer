package com.snakeindustry.snakemultiplayer.generalApp.player;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.NetworkModeAndGameSettings;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.GameStatsActivity;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.OneStats;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.OneStatsListAdapter;

public class ProfileActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(AppSingleton.getInstance().getPlayer().getName());

        Button editName = (Button) findViewById((R.id.editname));

        ListView listView = (ListView) findViewById(R.id.liststats);

        //ArrayAdapter<Game> adapter = new StatsListAdaptateur(this,AppSingleton.getInstance().getPlayer().getStats() );

        ArrayAdapter<OneStats> adapter = new OneStatsListAdapter(this,AppSingleton.getInstance().getPlayer().getStats().GamesStatsNbPlay());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                OneStats oneStats = (OneStats) parent.getItemAtPosition(position);
                Game game = AppSingleton.getGameFromName(oneStats.getDescription());
                AppSingleton.getInstance().setCurrentGame(game);


                Intent myIntent = new Intent(view.getContext(), GameStatsActivity.class);
                view.getContext().startActivity(myIntent);
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
