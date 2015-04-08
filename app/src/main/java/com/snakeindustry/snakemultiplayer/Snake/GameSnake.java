package com.snakeindustry.snakemultiplayer.Snake;

import android.view.View;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.Snake.model.SnakeGameState;
import com.snakeindustry.snakemultiplayer.Snake.viewAndControl.SnakeViewTouchControl;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameView;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.GameStats;

/**
 * Created by Adrien on 28/03/15.
 */
public class GameSnake implements Game {

  //  public static final String ID_NAME="MULTIPLAYER_SNAKE";

    private final String name = "Multiplayer Snake" ;

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
    public GameStats createStats() {
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
