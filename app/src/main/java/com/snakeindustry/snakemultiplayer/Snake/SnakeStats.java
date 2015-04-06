package com.snakeindustry.snakemultiplayer.Snake;

import com.snakeindustry.snakemultiplayer.generalApp.player.Player;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.StatsForOneGame;

import java.util.HashMap;

/**
 * Created by Adrien on 28/03/15.
 */
public class SnakeStats extends StatsForOneGame {
    public SnakeStats(int nbPlay, double playedTime, HashMap<Player, Integer> friends) {
        super(nbPlay, playedTime, friends);
    }

    public SnakeStats() {
        super();
    }


}
