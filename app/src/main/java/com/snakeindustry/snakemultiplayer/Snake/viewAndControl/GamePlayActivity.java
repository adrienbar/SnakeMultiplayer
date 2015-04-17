package com.snakeindustry.snakemultiplayer.Snake.viewAndControl;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.Snake.SnakeSettings;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;

public class GamePlayActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String currentGameName = AppSingleton.getInstance().getCurrentGame().getName();
        SnakeSettings snakeSettings = (SnakeSettings) AppSingleton.getInstance().getPlayer().getSettings().getSettingsForOneGame(currentGameName);
        SnakeView snakeView = snakeSettings.getPreferredControl(this, null);
        setContentView(snakeView);
        //setContentView(R.layout.activity_game_play);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_play, menu);
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
