package com.snakeindustry.snakemultiplayer.Snake;

import android.content.Context;
import android.util.AttributeSet;

import com.snakeindustry.snakemultiplayer.Snake.viewAndControl.SnakeView;
import com.snakeindustry.snakemultiplayer.Snake.viewAndControl.SnakeViewSwipeControl;
import com.snakeindustry.snakemultiplayer.Snake.viewAndControl.SnakeViewTouchControl;
import com.snakeindustry.snakemultiplayer.generalApp.player.settings.model.GameSettingsC;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

/**
 * Created by Vincent on 16/04/2015.
 */
public class SnakeSettings extends GameSettingsC{

    public static final int SWIPE=0;
    public static final int TOUCH=1;
    private int idPreferredControl;
    private HashMap<String,Integer> nomId;


    public SnakeSettings() {
        super();
        nomId=new HashMap<>();
        nomId.put("Swipe",SWIPE);
        nomId.put("Touch",TOUCH);
        idPreferredControl=SWIPE;
    }

    public SnakeView getPreferredControl(Context context,AttributeSet attributeSet){
        switch (idPreferredControl){
            case SWIPE: return new SnakeViewSwipeControl(context,attributeSet);
            case TOUCH: return new SnakeViewTouchControl(context,attributeSet);
            default:return new SnakeViewTouchControl(context,attributeSet);
        }
    }

    public int getIdPreferredControl() {
        return idPreferredControl;
    }

    public void setIdPreferredControl(int idPreferredControl) {
        this.idPreferredControl = idPreferredControl;
    }

    public HashMap<String, Integer> getNomId() {
        return nomId;
    }

    public void setNomId(HashMap<String, Integer> nomId) {
        this.nomId = nomId;
    }

    @Override
    public void saveSettings(Context context) {
        //save FPS
        super.saveSettings(context);

        //save idPreferred control
        FileOutputStream output;
        OutputStreamWriter osw;

        try {
            output = context.openFileOutput("SettingController.dat", Context.MODE_PRIVATE);
            osw = new OutputStreamWriter(output);
            System.out.println("Controller save");
            osw.write(getIdPreferredControl() + "\n");
            System.out.println(getIdPreferredControl());
            osw.flush();
            if (output != null)
                output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadSettings(Context context) {
        //load FPS
        super.loadSettings(context);

        //load idPreferred controls
        FileInputStream intput;
        InputStreamReader isr;
        BufferedReader reader;

        try {
            intput = context.openFileInput("SettingController.dat");
            isr = new InputStreamReader(intput);
            reader = new BufferedReader(isr);
            System.out.println("Controller load");
            setIdPreferredControl(Integer.parseInt(reader.readLine()));
            System.out.println(getIdPreferredControl());
            if (intput != null)
                intput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
