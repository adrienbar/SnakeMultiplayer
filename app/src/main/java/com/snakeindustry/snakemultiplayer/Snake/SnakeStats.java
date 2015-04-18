package com.snakeindustry.snakemultiplayer.Snake;

import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.GameStatsC;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.SimpleStats;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.SimpleStatsC;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrien on 28/03/15.
 */
public class SnakeStats extends GameStatsC {

    private SimpleStats summedLength;


    public SnakeStats() {
        super();
        this.summedLength = new SimpleStatsC("Summed Length");
    }


    public void addLength(int i) {
        summedLength.add(i);
    }

    @Override
    public List<SimpleStats> getStatsAsList(){
        List<SimpleStats> list=super.getStatsAsList();
        list.add(summedLength);
        return list;
    }


}

