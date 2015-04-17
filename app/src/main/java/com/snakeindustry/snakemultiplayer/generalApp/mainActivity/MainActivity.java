package com.snakeindustry.snakemultiplayer.generalApp.mainActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.database.PlayerDAO;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.player.ProfileActivity;


public class MainActivity extends ActionBarActivity {


    private ListView listView;
    private PlayerDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new PlayerDAO(getApplicationContext());
        //db.createPlayer("Kriss");
        AppSingleton.getInstance().setDb(db);
        AppSingleton.getInstance().loadProfile();
        AppSingleton.getInstance().checkStats();
        AppSingleton.getInstance().checkSettings();

        //Profile Buttons
        LinearLayout profile = (LinearLayout) findViewById(R.id.profile);
        //TextView textView= (TextView) findViewById(R.id.profile);
        profile.setOnClickListener(new ButtonController(ProfileActivity.class));

        //list of available games
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<Game> adapter = new GameListAdaptateur(this, AppSingleton.getInstance().getAvailabeGames());

        // Assign adapter to ListView
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new GameListListener());
    }

    @Override
    public void onResume(){
        super.onResume();
        //welcome
       TextView welcome=(TextView) findViewById(R.id.welcome);
        welcome.setText("Welcome "+ AppSingleton.getInstance().getPlayer().getName());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onStop() {
        super.onStop();
        AppSingleton.getInstance().saveProfile();
        //db.close();
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

        public ListView getListView() {
            return listView;
        }

        public void setListView(ListView listView) {
            this.listView = listView;
        }
}


