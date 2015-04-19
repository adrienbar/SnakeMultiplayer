package com.snakeindustry.snakemultiplayer.generalApp.game;

import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Adrien on 28/03/15.
 */
public interface GameView extends SurfaceHolder.Callback{
    public void draw(GameState gameState);

    //public void drawGameOver();


}
