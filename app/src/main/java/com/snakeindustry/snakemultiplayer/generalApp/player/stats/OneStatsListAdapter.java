package com.snakeindustry.snakemultiplayer.generalApp.player.stats;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.PictureDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;

import java.util.List;

/**
 * Created by Adrien on 06/04/15.
 */
public class OneStatsListAdapter extends ArrayAdapter<OneStats> {
    private Context context;
    private List<OneStats> statsList;

    public OneStatsListAdapter(Context context, List<OneStats> statsList) {
        super(context, R.layout.onestatslist, statsList);
        this.context=context;
        this.statsList=statsList;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.onestatslist, parent, false);


        ImageView icon = (ImageView) rowView.findViewById(R.id.statsicon);
        TextView statsGameName = (TextView) rowView.findViewById(R.id.statsdescription);
        TextView value = (TextView) rowView.findViewById(R.id.statsvalue);
        TextView unit = (TextView )rowView.findViewById(R.id.statsunit);




        icon.setImageBitmap(this.getStatsList().get(position).getIcon());
        //put icon in case of the description is the name of a game
        Game game = AppSingleton.getGameFromName(this.getStatsList().get(position).getDescription());
        if(game!=null) {
            icon.setImageResource(game.getIdIcon());
        }




        statsGameName.setText(this.getStatsList().get(position).getDescription());

        //display int without the .
        double val= this.getStatsList().get(position).getValue();
        if(val == (int) val) {
            int v=(int) val;
            value.setText(v + "");
        }
        else {
            value.setText(val+"");
        }
        unit.setText(this.getStatsList().get(position).getUnit());

        return rowView;
    }



    //GETTERS AND SETTERS
    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<OneStats> getStatsList() {
        return statsList;
    }

    public void setStatsList(List<OneStats> statsList) {
        this.statsList = statsList;
    }
}
