package com.snakeindustry.snakemultiplayer.generalApp.player.settings.model;

import android.content.Context;

/**
 * Created by Vincent on 16/04/2015.
 */
public interface GameSettings {

    // frame rate of the server
    public int getfps();
    public void setfps(int i);

    public void saveSettings(Context context);
    public void loadSettings(Context context);

}
