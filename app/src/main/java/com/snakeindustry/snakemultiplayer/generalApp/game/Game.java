package com.snakeindustry.snakemultiplayer.generalApp.game;

import android.app.Activity;
import android.view.View;

/**
 * Created by Adrien on 28/03/15.
 */
public interface Game {

public String getName();

    //type View should be discussed, and more generally the Settings of a game
public View getSettings();


}
