package com.snakeindustry.snakemultiplayer.generalApp.player.settings.model;

import android.content.Context;

/**
 * Created by Vincent on 16/04/2015.
 */
public abstract class GameSettingsC implements GameSettings{

    private int fps;

    @Override
    public int getfps() {
        return fps;
    }

    public GameSettingsC(int fps) {
        this.fps = fps;
    }

    //default value 30fps
    public GameSettingsC(){
        this(15);
    }

    @Override
    public void setfps(int i) {
        fps = i;
    }


@Override
    public void saveSettings(Context context){
        //save FPS
    }

@Override
    public void loadSettings(Context context){
        //load FPS
    }


}
