package com.snakeindustry.snakemultiplayer.generalApp.player.settings;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.Snake.SnakeSettings;
import com.snakeindustry.snakemultiplayer.Snake.viewAndControl.SnakeView;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.player.settings.model.GameSettings;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SettingsOneGameActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_one_game);

        final Game currentGame=AppSingleton.getInstance().getCurrentGame();

        TextView title =(TextView) findViewById(R.id.titlegamestats);
        title.setText(currentGame.getName());

        ImageView icon= (ImageView) findViewById(R.id.iconcurentgame);
        icon.setImageResource(currentGame.getIdIcon());



        SnakeSettings snakeSettings = (SnakeSettings) AppSingleton.getInstance().getPlayer().getSettings().getSettingsForOneGame(AppSingleton.getInstance().getCurrentGame().getName());
        List<String> list = new ArrayList<>();
        list.addAll(snakeSettings.getNomId().keySet());
        System.out.println("list after :" + list);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.controlmodes, android.R.layout.simple_spinner_item);
        System.out.println("adapter test :" + adapter==null );

        Spinner spinner = (Spinner) findViewById(R.id.control_mode_spinner);
        System.out.println(spinner);
        spinner.setAdapter(adapter);

        ListView listView = (ListView) findViewById(R.id.listView2);
        System.out.println("listView test :"+ listView==null);
        //listView.setAdapter(adapter);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setProgress(30);
        System.out.println("bbbbbbbbbbbbbseekBar"+seekBar.getProgress());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView value = (TextView) findViewById(R.id.value);
                value.setText(progress);
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
        getMenuInflater().inflate(R.menu.menu_settings_one_game, menu);
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
