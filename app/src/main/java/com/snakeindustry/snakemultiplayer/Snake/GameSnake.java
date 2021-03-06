package com.snakeindustry.snakemultiplayer.Snake;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.Snake.model.SnakeGameState;
import com.snakeindustry.snakemultiplayer.Snake.viewAndControl.SnakeViewTouchControl;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameView;
import com.snakeindustry.snakemultiplayer.generalApp.player.settings.model.GameSettings;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.GameStats;

import java.util.jar.Attributes;

/**
 * Created by Adrien on 28/03/15.
 */
public class GameSnake implements Game {

    private static GameSnake instance = null;
    private GameSnake() {
        this.gameView = null;
        this.gameState = new SnakeGameState();
        this.gameSettings=new SnakeSettings();
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
    private GameSettings gameSettings;


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getIdIcon() {
        return R.drawable.snake_icon;
    }

    @Override
    public GameView getGameView(Context context, AttributeSet attributeSet) {
        SnakeSettings snakeSettings = (SnakeSettings) AppSingleton.getInstance().getPlayer().getSettings().getSettingsForOneGame(this.getName());
        return snakeSettings.getPreferredControl(context,attributeSet);
    }

    @Override
    public GameStats createStats() {
        return new SnakeStats();
    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    //@Override
    public void resetGameState() {
        this.setGameState(new SnakeGameState());
    }

    @Override
    public GameSettings createSettings() {
        return new SnakeSettings();
    }

    @Override
    public Class getSettingsActivity() {
        return SnakeSettingsActivity.class;
    }



    @Override
    public String toString() {
        return getName();
    }



    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
