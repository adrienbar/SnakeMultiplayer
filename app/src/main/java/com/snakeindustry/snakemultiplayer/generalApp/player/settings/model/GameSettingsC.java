package com.snakeindustry.snakemultiplayer.generalApp.player.settings.model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Vincent on 16/04/2015.
 */
public abstract class GameSettingsC implements GameSettings{

    private int fps;

    public GameSettingsC(int fps) {
        this.fps = fps;
    }

    //default value 30fps
    public GameSettingsC() {
        this(15);
    }

    @Override
    public int getfps() {
        return fps;
    }

    @Override
    public void setfps(int i) {
        fps = i;
    }


@Override
    public void saveSettings(Context context){
    FileOutputStream output;
    OutputStreamWriter osw;

    try {
        output = context.openFileOutput("SettingFps.dat", Context.MODE_PRIVATE);
        osw = new OutputStreamWriter(output);
        System.out.println("FPS save");
        osw.write(getfps() + "\n");
        System.out.println(getfps());
        osw.flush();
        if (output != null)
            output.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    }

@Override
    public void loadSettings(Context context){
    FileInputStream intput;
    InputStreamReader isr;
    BufferedReader reader;

    try {
        intput = context.openFileInput("SettingFps.dat");
        isr = new InputStreamReader(intput);
        reader = new BufferedReader(isr);
        System.out.println("FPS load");
        setfps(Integer.parseInt(reader.readLine()));
        System.out.println(getfps());

        if (intput != null)
            intput.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }


}
