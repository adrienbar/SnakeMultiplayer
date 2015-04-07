package com.snakeindustry.snakemultiplayer.Snake.model;

import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.Food;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.SnakeBonus;
import  com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.SnakeBonus.target;
import java.util.List;

/**
 * Created by Adrien on 28/03/15.
 */
public class SnakeGameState extends GameState {

    List<SnakeBonus> spawnedBonuses, activeBonuses;
    List<Food> spawnedFood;
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

    //Checks if any snake is on food, if yes make it grow
    public void checkFood() {
        for (Snake s : snakes) {

            for (Food f : spawnedFood) {

                if (s.getBody().getFirst().getX() == f.getX() && s.getBody().getFirst().getY() == f.getY()) {

                    s.grow(1);
                    spawnedFood.remove(f);
                }
            }
        }

    }

    //Checks if any snake is on a bonus, if yes apply it
    public void checkBonuses() {

        for (Snake s : snakes) {

            for (SnakeBonus sb : spawnedBonuses) {

                if (s.getBody().getFirst().getX() == sb.getX() && s.getBody().getFirst().getY() == sb.getY()) {

                    if(sb.getTarget()== target.self){
                        s.setState(sb.getState());
                    }
                    else if(sb.getTarget()== target.all){

                        for (Snake targeted : snakes) {
                              targeted.setState(sb.getState());
                        }
                     }
                    else if(sb.getTarget()== target.others){
                        for (Snake targeted : snakes) {
                            if(!targeted.equals(s)) {
                                targeted.setState(sb.getState());
                            }
                        }
                    }


                  }

              }
         }

      }


 }

