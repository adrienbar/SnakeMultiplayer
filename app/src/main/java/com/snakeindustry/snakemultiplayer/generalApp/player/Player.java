package com.snakeindustry.snakemultiplayer.generalApp.player;

import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.Stats;

/**
 * Created by Adrien on 28/03/15.
 */
public abstract class Player {
    private Stats stats;
    private String name;

    protected Player(Stats stats, String name) {
        this.stats = stats;
        this.name = name;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
