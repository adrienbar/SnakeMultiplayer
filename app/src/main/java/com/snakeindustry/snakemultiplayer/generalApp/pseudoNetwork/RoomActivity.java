package com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork;

import android.content.Intent;
import android.support.annotation.LayoutRes;
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
import com.snakeindustry.snakemultiplayer.Snake.viewAndControl.GamePlayActivity;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.mainActivity.GameListAdaptateur;
import com.snakeindustry.snakemultiplayer.generalApp.mainActivity.GameListListener;
import com.snakeindustry.snakemultiplayer.generalApp.player.Player;

import java.util.List;

public class RoomActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        TextView gameTitle = (TextView) findViewById(R.id.titlenetworkandsettings);
        gameTitle.setText(AppSingleton.getInstance().getCurrentGame().getName());

        ImageView gameIcon =  (ImageView) findViewById((R.id.gameiconnetworkandsettings));
        gameIcon.setImageResource(AppSingleton.getInstance().getCurrentGame().getIdIcon());

        //list of available games

        ListView listView = (ListView) findViewById(R.id.players);

        List<String> playersName =AppSingleton.getInstance().getCurrenGameTread().getServer().getRoom().getPlayersName();
       int i= playersName.indexOf(AppSingleton.getInstance().getPlayer().getName());
        playersName.set(i,playersName.get(i) + " (Me!)");


        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,playersName);

        // Assign adapter to ListView
        listView.setAdapter(adapter);
        //listView.setOnItemClickListener();

        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppSingleton.getInstance().getCurrenGameTread().getServer().getRoom().startTheGame();
                v.getContext().startActivity(new Intent(v.getContext(), GamePlayActivity.class));
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_room, menu);
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
