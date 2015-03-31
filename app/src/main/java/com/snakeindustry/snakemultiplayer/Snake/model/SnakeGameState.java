package com.snakeindustry.snakemultiplayer.Snake.model;

import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.SnakeBonus;

import java.util.List;

/**
 * Created by Adrien on 28/03/15.
 */
public class SnakeGameState extends GameState {

    List<SnakeBonus> bonusToBeEaten,activeBonus;
    List<Snake> snakes;

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public void configure() {

    }

    @Override
    public void nextStep() {

    }

    @Override
    public void gameOverAction() {

    }
}
