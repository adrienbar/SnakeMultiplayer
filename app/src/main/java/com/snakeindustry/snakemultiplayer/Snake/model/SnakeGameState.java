package com.snakeindustry.snakemultiplayer.Snake.model;

import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.Food;
import com.snakeindustry.snakemultiplayer.Snake.model.state.NormalState;
import com.snakeindustry.snakemultiplayer.Snake.model.state.State;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.SnakeBonus;
import  com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.SnakeBonus.target;
import com.snakeindustry.snakemultiplayer.generalApp.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Adrien on 28/03/15.
 */
public class SnakeGameState extends GameState {

    public final static int SNAKE_TURN_LEFT=0;
    public final static int SNAKE_TURN_RIGHT=1;

    List<SnakeBonus> spawnedBonuses, activeBonuses;
    List<Food> spawnedFood;
    List<Snake> snakes;
    HashMap<String,Snake> playersSnakes;
    //Array of available bonuses name, with strings to randomly generate them
    String[] availableBonuses;
    int screenWidth,screenLength;
   //Should define the windth and length of all items, ie food/bonuses/snakecells,etc
    int itemWidth,itemLength;

    public SnakeGameState(){
       super();
        this.spawnedBonuses = new LinkedList<SnakeBonus>();
        this.activeBonuses = new LinkedList<SnakeBonus>();
        this.spawnedFood = new LinkedList<Food>();

        availableBonuses=new String[1];
        availableBonuses[0]="Invincible";

        //initialise properly playersSnakes and snakes
        this.configure(new ArrayList<String>());

   }


    @Override
    public boolean isGameOver() {
        return false;
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
        Timer timer;
        for (Snake s : snakes) {

            for (SnakeBonus sb : spawnedBonuses) {

                if (s.getBody().getFirst().getX() == sb.getX() && s.getBody().getFirst().getY() == sb.getY()) {
                    List<String> targets = new ArrayList<>();
                    if(sb.getTarget()== target.self){
                        s.setState(sb.getState());
                        timer=new Timer();

                       targets.add(s.getPlayer());
                       timer.schedule(new CancelBonusTask(targets,sb), sb.getDuration() * 1000);
                    }
                    else if(sb.getTarget()== target.all){

                        for (Snake targeted : snakes) {
                              targeted.setState(sb.getState());
                              targets.add(s.getPlayer());
                        }
                        timer=new Timer();
                        timer.schedule(new CancelBonusTask(targets,sb), sb.getDuration() * 1000);
                     }
                    else if(sb.getTarget()== target.others){
                        for (Snake targeted : snakes) {
                            if(!targeted.equals(s)) {
                                targeted.setState(sb.getState());
                                targets.add(s.getPlayer());
                            }
                        }
                        timer=new Timer();
                        timer.schedule(new CancelBonusTask(targets,sb), sb.getDuration() * 1000);
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
    //Class used for timer cancelling bonus
     class CancelBonusTask extends TimerTask {

        List<String> targets;
         SnakeBonus bonus;
         public CancelBonusTask(List<String> targets,SnakeBonus bonus){
            this.targets=targets;this.bonus=bonus;
        }

        public void run() {
            Iterator<Snake> iter = snakes.iterator();
            while(iter.hasNext()){
               Snake temp = iter.next();
                //Set all affected snakes back to normal
                if(targets.contains(temp.getPlayer())){
                        temp.setState(new NormalState());
                }

                //Remove the bonus from the active bonus list

            }
            Iterator<SnakeBonus> iter2 = spawnedBonuses.iterator();
            while(iter.hasNext()){
                SnakeBonus temp = iter2.next();
                //Set all affected snakes back to normal
                if(temp.getId().equals(bonus.getId())){
                    spawnedBonuses.remove(temp);
                }

                //Remove the bonus from the active bonus list

            }
        }
    }




    /**
     * create a new snake for each player and update ths snakesList
     * @param players in the game
     */
    public void configure(List<String> players) {
        playersSnakes=new HashMap<>();
        snakes=new ArrayList<>();
        //create a new Snake for the local player
        //this.playersSnakes.put(AppSingleton.getInstance().getPlayer().getName(), new Snake());

        //create a new Snake for each distantPlayers
        for (String playerName :players) {
            playersSnakes.put(playerName,new Snake());
        }
        //update the list of snakes
        snakes.addAll(playersSnakes.values());
    }

    @Override
    public void nextStep(HashMap<String, Integer> playerCommand) {


       // System.out.println("GAME STATE  nextStep : "+ playerCommand.keySet()+" command : " + playerCommand.values());
        //update the direction of each snake
        for(String playerName : playerCommand.keySet()){
            performeActionCode(playersSnakes.get(playerName),playerCommand.get(playerName));
        }

        //update positions

        //update collisions


    }




    private void performeActionCode(Snake snake,Integer commande) {
        if(commande!=null) {
            switch (commande) {
                case SNAKE_TURN_LEFT:
                    snake.turnLeft();
                case SNAKE_TURN_RIGHT:
                    snake.turnRight();
                default:
            }
        }

    }



    //GETTERS AND SETTERS

    public List<SnakeBonus> getSpawnedBonuses() {
        return spawnedBonuses;
    }

    public List<Food> getSpawnedFood() {
        return spawnedFood;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

}

