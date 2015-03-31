package com.snakeindustry.snakemultiplayer.Snake;

import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.View;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;

/**
 * Created by Adrien on 28/03/15.
 */
public class GameSnake implements Game {

    public static final String ID_NAME="MULTIPLAYER_SNAKE";

    private String name;

    public GameSnake() {
        this.name="Multilayer Snake";
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getIdIcon() {
        return R.drawable.snake_icon;
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
