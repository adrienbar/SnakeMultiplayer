package com.snakeindustry.snakemultiplayer.generalApp.game;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.Snake.SnakeSettings;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameView;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameViewAC;

public class GamePlayActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameViewAC gameView = (GameViewAC) AppSingleton.getInstance().getCurrentGame().getGameView(this, null);
        setContentView(gameView);
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




    @Override
    public void onPause(){
       super.onPause();

        System.out.println("------"+AppSingleton.getInstance().getCurrentGame().getName());
        boolean retry=true;
        AppSingleton.getInstance().getCurrenGameTread().setRunning(false);
        while (retry)
        try {
            AppSingleton.getInstance().getCurrenGameTread().join();
            retry=false;
        } catch (InterruptedException e) {
            e.printStackTrace();

        }

        //moveTaskToBack(true);

        //this.finish();
        //System.exit(0);

    }

}