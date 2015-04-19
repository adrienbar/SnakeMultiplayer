package com.snakeindustry.snakemultiplayer.generalApp.player;

import com.snakeindustry.snakemultiplayer.generalApp.player.settings.model.SettingsGlobalHashmap;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.Stats;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.StatsGlobalHashmap;

/**
 * Created by Adrien on 28/03/15.
 */
public class DefaultPlayer extends Player {

    public final static String DEFAULT_NAME="unammed";

    public DefaultPlayer(Stats stats, String name) {
        super(stats, name, new SettingsGlobalHashmap());
    }

    public DefaultPlayer() {
        this(new StatsGlobalHashmap(),DEFAULT_NAME);
    }
}
