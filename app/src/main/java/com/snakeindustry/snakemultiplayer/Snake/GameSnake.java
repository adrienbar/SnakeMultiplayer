package com.snakeindustry.snakemultiplayer.Snake;

import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.View;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.Snake.model.SnakeGameState;
import com.snakeindustry.snakemultiplayer.Snake.viewAndControl.SnakeViewTouchControl;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameView;
import com.snakeindustry.snakemultiplayer.generalApp.player.StatsForOneGame;

/**
 * Created by Adrien on 28/03/15.
 */
public class GameSnake implements Game {

  //  public static final String ID_NAME="MULTIPLAYER_SNAKE";

    private final String name = "Multilayer Snake" ;

    private GameView gameView;
    private GameState gameState;


    public GameSnake(GameView gameView, GameState gameState) {
        this.gameView = gameView;
        this.gameState = gameState;
    }

    public GameSnake() {
        this(new SnakeViewTouchControl(),new SnakeGameState());
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
    public GameView getGameView() {
        return this.gameView;
    }

    @Override
    public StatsForOneGame createStats() {
        return new SnakeStats();
    }

    @Override
    public GameState getGameState() {
        return this.gameState;
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
