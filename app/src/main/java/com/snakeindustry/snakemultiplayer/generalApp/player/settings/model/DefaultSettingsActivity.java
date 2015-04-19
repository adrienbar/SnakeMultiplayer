package com.snakeindustry.snakemultiplayer.generalApp.player.settings.model;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;

public class DefaultSettingsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_settings);

        final Game currentGame= AppSingleton.getInstance().getCurrentGame();

        TextView title =(TextView) findViewById(R.id.titlegamestats);
        title.setText(currentGame.getName());

        ImageView icon= (ImageView) findViewById(R.id.iconcurentgame);
        icon.setImageResource(currentGame.getIdIcon());


        TextView value = (TextView) findViewById(R.id.value);
        System.out.println("--------------------------------");
        System.out.println("--------------------------------");
        System.out.println(""+AppSingleton.getInstance().getPlayer().getSettings());
        System.out.println(""+AppSingleton.getInstance().getPlayer().getSettings().getSettingsForOneGame(currentGame.getName()));
        System.out.println(""+AppSingleton.getInstance().getPlayer().getSettings().getSettingsForOneGame(currentGame.getName()).getfps());

        value.setText(""+AppSingleton.getInstance().getPlayer().getSettings().getSettingsForOneGame(currentGame.getName()).getfps()+" fps");

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(50);
        seekBar.setProgress(AppSingleton.getInstance().getPlayer().getSettings().getSettingsForOneGame(currentGame.getName()).getfps());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView value = (TextView) findViewById(R.id.value);
                value.setText(progress+"");

                if(progress == 0){
                    progress++;
                }

                AppSingleton.getInstance().getPlayer().getSettings().getSettingsForOneGame(currentGame.getName()).setfps(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_default_settings, menu);
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
