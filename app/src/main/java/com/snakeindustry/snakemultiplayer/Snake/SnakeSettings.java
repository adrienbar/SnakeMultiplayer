package com.snakeindustry.snakemultiplayer.Snake;

import android.view.View;

import com.snakeindustry.snakemultiplayer.Snake.viewAndControl.SnakeView;
import com.snakeindustry.snakemultiplayer.Snake.viewAndControl.SnakeViewSwipeControl;
import com.snakeindustry.snakemultiplayer.Snake.viewAndControl.SnakeViewTouchControl;
import com.snakeindustry.snakemultiplayer.generalApp.player.settings.model.GameSettingsC;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Vincent on 16/04/2015.
 */
public class SnakeSettings extends GameSettingsC{

    private SnakeView preferedControl;
    private ArrayList<SnakeView> availableControl;

    public SnakeSettings(ArrayList<SnakeView> availableControl) {
        super();
        this.preferedControl = availableControl.get(0);
        this.availableControl = availableControl;
    }

    public SnakeSettings() {
        this(new ArrayList<SnakeView>(Arrays.asList(new SnakeViewSwipeControl(null,null), new SnakeViewTouchControl(null,null))));
    }

    public SnakeView getPreferedControl() {
        return preferedControl;
    }

    public void setPreferedControl(SnakeView preferedControl) {
        this.preferedControl = preferedControl;
    }

    public ArrayList<SnakeView> getAvailableControl() {
        return availableControl;
    }

    public void setAvailableControl(ArrayList<SnakeView> availableControl) {
        this.availableControl = availableControl;
    }
}
