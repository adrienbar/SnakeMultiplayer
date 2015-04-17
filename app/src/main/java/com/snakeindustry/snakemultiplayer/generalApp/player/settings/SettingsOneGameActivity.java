package com.snakeindustry.snakemultiplayer.generalApp.player.settings;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.Snake.SnakeSettings;
import com.snakeindustry.snakemultiplayer.Snake.model.Snake;
import com.snakeindustry.snakemultiplayer.Snake.viewAndControl.SnakeView;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.player.settings.model.GameSettings;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SettingsOneGameActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_one_game);

        final Game currentGame=AppSingleton.getInstance().getCurrentGame();

        TextView title =(TextView) findViewById(R.id.titlegamestats);
        title.setText(currentGame.getName());

        ImageView icon= (ImageView) findViewById(R.id.iconcurentgame);
        icon.setImageResource(currentGame.getIdIcon());





        final SnakeSettings snakeSettings = (SnakeSettings) AppSingleton.getInstance().getPlayer().getSettings().getSettingsForOneGame(AppSingleton.getInstance().getCurrentGame().getName());
        List<String> list = new ArrayList<>();
        list.addAll(snakeSettings.getNomId().keySet());
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.controlmodes, android.R.layout.simple_spinner_item);

        Spinner spinner = (Spinner) findViewById(R.id.control_mode_spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        int id =snakeSettings.getIdPreferredControl();
        String string = "";
        for (String s : snakeSettings.getNomId().keySet()){
            if (snakeSettings.getNomId().get(s) == id ){
                string = s;
            }
        }
        int index = list.indexOf(string);

        spinner.setSelection(index);


        TextView value = (TextView) findViewById(R.id.value);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String currentGameName = AppSingleton.getInstance().getCurrentGame().getName();
        SnakeSettings snakesettings = (SnakeSettings) AppSingleton.getInstance().getPlayer().getSettings().getSettingsForOneGame(currentGameName);
        System.out.println("snake settings : " + snakesettings);
        String currentMode = (String) parent.getSelectedItem();
        System.out.println("currentMode : " + currentMode);
        System.out.println("snake settings Hashmap : " + snakesettings.getNomId());
        System.out.println("entier current mode : "+snakesettings.getNomId().get(currentMode));
        snakesettings.setIdPreferredControl(snakesettings.getNomId().get(currentMode));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
