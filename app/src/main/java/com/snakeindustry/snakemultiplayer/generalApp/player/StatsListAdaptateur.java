package com.snakeindustry.snakemultiplayer.generalApp.player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrien on 31/03/15.
 */
public class StatsListAdaptateur extends ArrayAdapter<Game> {
    private Context context;
    private Stats stats;


    public StatsListAdaptateur(Context context, Stats stats) {
        super(context, android.R.layout.simple_list_item_2,stats.getGameWithStats());
        this.context=context;
        this.stats=stats;
    }

    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.statslist, parent, false);

        Game game=this.getStats().getGameWithStats().get(position);

        TextView gameName = (TextView) rowView.findViewById(R.id.gamename);
        gameName.setText(game.getName());

        TextView gameMainStat = (TextView) rowView.findViewById(R.id.gamemainstat);
        gameMainStat.setText(this.getStats().getPlayedTime(game)+" h");

        ImageView gameIcon = (ImageView) rowView.findViewById(R.id.gameicon);
        gameIcon.setImageResource(game.getIdIcon());

        return rowView;
    }

}
