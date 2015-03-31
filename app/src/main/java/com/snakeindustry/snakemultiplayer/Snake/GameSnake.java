package com.snakeindustry.snakemultiplayer.Snake;

import android.view.View;

import com.snakeindustry.snakemultiplayer.generalApp.game.Game;

/**
 * Created by Adrien on 28/03/15.
 */
public class GameSnake implements Game {

    public final static String name ="Multiplayer Snake";








    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    // to be discussed
    @Override
    public View getSettings() {
        return null;
    }
}
