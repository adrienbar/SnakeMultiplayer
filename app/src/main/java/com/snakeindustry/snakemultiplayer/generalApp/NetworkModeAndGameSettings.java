package com.snakeindustry.snakemultiplayer.generalApp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.generalApp.mainActivity.ButtonController;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.StatsOneGameActivity;

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
