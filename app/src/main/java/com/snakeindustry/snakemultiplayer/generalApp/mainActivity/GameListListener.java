package com.snakeindustry.snakemultiplayer.generalApp.mainActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.NetworkModeAndGameSettings;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameThread;

/**
 * Created by Adrien on 31/03/15.
 */
public class GameListListener implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Game game = (Game) parent.getItemAtPosition(position);
        AppSingleton.getInstance().setCurrentGame(game);

        Intent myIntent = new Intent(view.getContext(), NetworkModeAndGameSettings.class);
        view.getContext().startActivity(myIntent);
    }
}
