package com.snakeindustry.snakemultiplayer.Snake;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.snakeindustry.snakemultiplayer.Snake.viewAndControl.SnakeView;
import com.snakeindustry.snakemultiplayer.Snake.viewAndControl.SnakeViewSwipeControl;
import com.snakeindustry.snakemultiplayer.Snake.viewAndControl.SnakeViewTouchControl;
import com.snakeindustry.snakemultiplayer.generalApp.player.settings.model.GameSettingsC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Vincent on 16/04/2015.
 */
public class SnakeSettings extends GameSettingsC{

    private int idPreferredControl;

    public static final int SWIPE=0;
    public static final int TOUCH=1;

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

}
