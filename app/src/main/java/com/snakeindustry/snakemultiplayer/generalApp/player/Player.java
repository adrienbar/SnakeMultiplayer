package com.snakeindustry.snakemultiplayer.generalApp.player;

import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.Stats;
import com.snakeindustry.snakemultiplayer.generalApp.player.settings.model.Settings;
/**
 * Created by Adrien on 28/03/15.
 */
public abstract class Player {
    private Stats stats;
    private String name;
    private Settings settings;

    protected Player(Stats stats, String name, Settings settings) {
        this.stats = stats;
        this.name = name;
        this.settings = settings;
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

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
