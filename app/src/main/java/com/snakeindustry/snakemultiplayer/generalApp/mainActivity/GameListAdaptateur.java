package com.snakeindustry.snakemultiplayer.generalApp.mainActivity;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Adrien on 31/03/15.
 */
public class GameListAdaptateur extends ArrayAdapter<Game> {
    private Context context;
    private List<Game> gameList;

    public GameListAdaptateur(Context context,List<Game> gameList) {
        super(context, R.layout.textgamelist, gameList);
        this.context = context;
        this.gameList = gameList;
    }

    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.textgamelist, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.profile);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        textView.setText(this.getGameList().get(position).getName());

        imageView.setImageResource(this.getGameList().get(position).getIdIcon());

        return rowView;
    }
}


