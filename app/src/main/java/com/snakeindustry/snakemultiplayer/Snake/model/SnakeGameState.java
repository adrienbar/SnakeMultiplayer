package com.snakeindustry.snakemultiplayer.Snake.model;

import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.Food;
import com.snakeindustry.snakemultiplayer.Snake.model.state.State;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.SnakeBonus;
import  com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.SnakeBonus.target;
import java.util.List;
import java.util.Random;

/**
 * Created by Adrien on 28/03/15.
 */
public class SnakeGameState extends GameState {

    List<SnakeBonus> spawnedBonuses, activeBonuses;
    List<Food> spawnedFood;
    List<Snake> snakes;
    //Array of available bonuses name, with strings to randomly generate them
    String[] availableBonuses;
    int screenWidth,screenLength;
   //Should define the windth and length of all items, ie food/bonuses/snakecells,etc
    int itemWidth,itemLength;

   public SnakeGameState(){
       super();
       availableBonuses=new String[1];
       availableBonuses[0]="Invincible";
   }

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

    public void spawnFood(){
        Random randomGenerator = new Random();
        int randomX=randomGenerator.nextInt(screenWidth-itemWidth+1)+itemWidth;
        int randomY=randomGenerator.nextInt(screenLength-itemLength+1)+itemLength;
        Food spawned = new Food(randomX,randomY,itemWidth,itemLength);
        spawnedFood.add(spawned);

    }

    public void spawnBonus() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Random randomGenerator = new Random();
        int randomX=randomGenerator.nextInt(screenWidth-itemWidth+1)+itemWidth;
        int randomY=randomGenerator.nextInt(screenLength-itemLength+1)+itemLength;
        //Chose index in the list of bonuses
        int randomBonus=randomGenerator.nextInt(activeBonuses.size());
        //Instantiate the appropriate class
        SnakeBonus spawnedBonus = (SnakeBonus) (Class.forName(availableBonuses[randomBonus]).newInstance());
        spawnedBonus.setX(randomX);
        spawnedBonus.setY(randomY);
        //Instantiate the appropriate state, state class names should follow the pattern :BehaviorState ( ex: InvincibleState)
        spawnedBonus.setState((State) (Class.forName(availableBonuses[randomBonus]+"State").newInstance()));

        spawnedBonuses.add(spawnedBonus);





    }


 }

