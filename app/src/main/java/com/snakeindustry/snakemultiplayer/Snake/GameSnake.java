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

    private static GameSnake instance = null;
    private GameSnake() {
        this.gameView = null;
        this.gameState = new SnakeGameState();

    }

    public static synchronized GameSnake getInstance() {
        if (instance == null) {
            instance = new GameSnake();
        }

        return instance;
    }

  //  public static final String ID_NAME="MULTIPLAYER_SNAKE";

    private final String name = "Multiplayer Snake" ;

    private GameView gameView;
    private GameState gameState;


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
    public void resetGameState() {
        this.setGameState(new SnakeGameState());
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

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
