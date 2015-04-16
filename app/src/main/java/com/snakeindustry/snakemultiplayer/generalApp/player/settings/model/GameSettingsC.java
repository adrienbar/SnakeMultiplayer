package com.snakeindustry.snakemultiplayer.generalApp.player.settings.model;

/**
 * Created by Vincent on 16/04/2015.
 */
public class GameSettingsC implements GameSettings{

    private int fps;

    @Override
    public int getfps() {
        return fps;
    }

    @Override
    public void setfps(int i) {
        fps = i;
    }
}
