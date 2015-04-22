package com.snakeindustry.snakemultiplayer.Snake.model;

import android.os.SystemClock;

import com.snakeindustry.snakemultiplayer.Snake.SnakeStats;
import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.Food;
import com.snakeindustry.snakemultiplayer.Snake.model.state.NormalState;
import com.snakeindustry.snakemultiplayer.Snake.model.state.State;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.Game;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.SnakeBonus;
import  com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.SnakeBonus.target;
import com.snakeindustry.snakemultiplayer.generalApp.player.Player;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.GameStats;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.SimpleStats;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.old.StatsForOneGame;

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

    public final static String SNAKE_GO_UP="GO_UP";
    public final static String SNAKE_GO_RIGHT="GO_RIGHT";
    public final static String SNAKE_GO_DOWN="GO_DOWN";
    public final static String SNAKE_GO_LEFT="GO_LEFT";

    public final static String SNAKE_TURN_LEFT="TURN_LEFT";
    public final static String SNAKE_TURN_RIGHT="TURN_RIGHT";


    List<SnakeBonus> spawnedBonuses, activeBonuses;
    List<Food> spawnedFood;
    List<Snake> snakes;
    HashMap<String,Snake> playersSnakes;
    //Array of available bonuses name, with strings to randomly generate them
    String[] availableBonuses;
    int screenWidth,screenLength;
   //Should define the windth and length of all items, ie food/bonuses/snakecells,etc
    private double itemWidth,itemLength;

    //to control time appartion
    private long timeBetweenBonusSpawn, timeBetweenFoodSpawn;
    private long timeOfLastBonusSpawn,timeOfLastFoodSpan;

    public SnakeGameState(){
       super();
        this.spawnedBonuses = new LinkedList<SnakeBonus>();
        this.activeBonuses = new LinkedList<SnakeBonus>();
        this.spawnedFood = new LinkedList<Food>();

        availableBonuses=new String[3];
        availableBonuses[0]="Invincible";
        availableBonuses[1]="Reverse";
        availableBonuses[2]="Fast";

        //initialise properly playersSnakes, snakes and Statistics;
        this.configure(new ArrayList<String>());
        screenWidth=1;
        screenLength=1;
        itemWidth=0.05;
        itemLength=0.05;

        timeBetweenBonusSpawn=10*1000;
        timeBetweenFoodSpawn=5*1000;
        timeOfLastBonusSpawn=0;
        timeOfLastFoodSpan=0;


   }


    @Override
    public boolean isGameOver() {
        return getSnakes().size()==0;
    }





    @Override
    public void gameOverAction() {



    }







    @Override
    public List<String> getPlayers() {
        ArrayList players=new ArrayList();
        players.addAll(playersSnakes.keySet());
        return players;
    }

    //Checks if any snake is on food, if yes make it grow
    public void checkFood() {
        for (Snake s : snakes) {
            Food toRemove=null;
            for (Food f : spawnedFood) {

                if (s.getState().getBody().getFirst().getX()-s.getState().getWidth()/2 <= f.getX()+itemWidth/2 &&
                    s.getState().getBody().getFirst().getX()+s.getState().getWidth()/2 >= f.getX()-itemWidth/2 &&
                    s.getState().getBody().getFirst().getY()+s.getState().getLength()/2 >= f.getY()-itemLength/2 &&
                    s.getState().getBody().getFirst().getY()-s.getState().getLength()/2 <= f.getY()+itemLength/2     ) {
                    toRemove=f;
                    s.getState().grow(1);
                }
            }

            spawnedFood.remove(toRemove);
        }

    }

    //Checks if any snake is on a bonus, if yes apply it
    public void checkBonuses() {
        Timer timer;
        for (Snake s : snakes) {
            SnakeBonus toRemove=null;
            for (SnakeBonus sb : spawnedBonuses) {

                if (s.getState().getBody().getFirst().getX()-s.getState().getWidth()/2 <= sb.getX()+itemWidth/2 &&
                    s.getState().getBody().getFirst().getX()+s.getState().getWidth()/2 >= sb.getX()-itemWidth/2 &&
                    s.getState().getBody().getFirst().getY()+s.getState().getLength()/2 >= sb.getY()-itemLength/2 &&
                    s.getState().getBody().getFirst().getY()-s.getState().getLength()/2 <= sb.getY()+itemLength/2   ) {
                    List<String> targets = new ArrayList<>();
                    if(sb.getTarget()== target.self){
                        sb.getState().setBody(s.getState().getBody());
                        sb.getState().setCurrentDirection(s.getState().getCurrentDirection());
                        s.setState(sb.getState());
                        timer=new Timer();

                       targets.add(s.getPlayer());
                       timer.schedule(new CancelBonusTask(targets,sb), sb.getDuration() * 1000);

                    }
                    else if(sb.getTarget()== target.all){

                        for (Snake targeted : snakes) {
                            sb.getState().setBody(targeted.getState().getBody());
                            sb.getState().setCurrentDirection(targeted.getState().getCurrentDirection());
                            targeted.setState(sb.getState());
                            targets.add(s.getPlayer());
                        }
                        timer=new Timer();
                        timer.schedule(new CancelBonusTask(targets,sb), sb.getDuration() * 1000);
                     }
                    else if(sb.getTarget()== target.others){
                        for (Snake targeted : snakes) {
                            if(!targeted.equals(s)) {
                                sb.getState().setBody(targeted.getState().getBody());
                                sb.getState().setCurrentDirection(targeted.getState().getCurrentDirection());
                                targeted.setState(sb.getState());
                                targets.add(s.getPlayer());
                            }
                        }
                        timer=new Timer();
                        timer.schedule(new CancelBonusTask(targets,sb), sb.getDuration() * 1000);
                    }
                    toRemove=sb;

                  }

              }
            spawnedBonuses.remove(toRemove);
         }

      }

    private void spawnFood(){
        Random randomGenerator = new Random();
        double randomX=itemWidth/2+(screenWidth -itemWidth/2)*randomGenerator.nextDouble();
        double randomY=itemLength/2+(screenLength -itemLength/2)*randomGenerator.nextDouble();
        Food spawned = new Food(randomX,randomY,itemWidth,itemLength);
        spawnedFood.add(spawned);

    }

    private void spawnBonus() {

        Random randomGenerator = new Random();
        double randomX=itemWidth/2+(screenWidth -itemWidth/2)*randomGenerator.nextDouble();
        double randomY=itemLength/2+(screenLength -itemLength/2)*randomGenerator.nextDouble();
        //Chose index in the list of bonuses
        int randomBonus=randomGenerator.nextInt(availableBonuses.length);
        //Instantiate the appropriate class
        //SnakeBonus spawnedBonus = (SnakeBonus) (Class.forName(availableBonuses[randomBonus]+"Bonus").newInstance());
        SnakeBonus spawnedBonus=SnakeBonus.SnakeBonusFactory(availableBonuses[randomBonus],randomX,randomY,itemWidth,itemLength);
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
                        State.direction dir = temp.getState().getCurrentDirection();
                        State newstate=new NormalState(temp.getState().getBody(),temp.getState().getWidth(),temp.getState().getLength(),temp.getState().getGrowing());
                        temp.setState(newstate);
                        temp.getState().setCurrentDirection(dir);
                }



            }
            //Remove the bonus from the active bonus list
            Iterator<SnakeBonus> iter2 = activeBonuses.iterator();
            SnakeBonus toremove=null;
            while(iter.hasNext()){
                SnakeBonus temp = iter2.next();

                if(temp.getId().equals(bonus.getId())){
                    toremove=temp;

                }
            }
            activeBonuses.remove(toremove);

        }
    }




    /**
     * create a new snake for each player and update the snakesList
     * also create StatForOneGame for each player
     * @param players in the game
     */
    public void configure(List<String> players) {
        //initialise player's list and scores
        super.configure(players);

        //initialise snakes
        playersSnakes=new HashMap<>();
        snakes=new ArrayList<>();
        //playerScore=new HashMap<>();
        //create a new Snake for the local player
        //this.playersSnakes.put(AppSingleton.getInstance().getPlayer().getName(), new Snake());

        //create a new Snake for each distantPlayers

        for (String playerName :players) {

            switch (playersSnakes.size()) {
                case 0:
                    playersSnakes.put(playerName, new Snake(0.25, 0.25));
                    break;
                case 1:
                    playersSnakes.put(playerName, new Snake(0.25, 0.75));
                    break;
                case 3:
                    playersSnakes.put(playerName, new Snake(0.75, 0.25));
                    break;
                case 4:
                    playersSnakes.put(playerName, new Snake(0.75, 0.75));
                    break;
                default:
            }

            /*
            if(playersSnakes.size()==0){
            playersSnakes.put(playerName,new Snake(0.25,0.25));
            }
            else if(playersSnakes.size()==0){
                playersSnakes.put(playerName,new Snake(0.25,0.75));
            }
            else if(playersSnakes.size()==0){
                playersSnakes.put(playerName,new Snake(0.75,0.25));
            }
            else{
                playersSnakes.put(playerName,new Snake(0.75,0.75));
            }
            }
        */
        }
        //update the list of snakes
        snakes.addAll(playersSnakes.values());

    }

    @Override
    public void nextStep(HashMap<String, String> playerCommand,long threadTime) {


        performeActionTime(threadTime);


       // System.out.println("GAME STATE  nextStep : "+ playerCommand.keySet()+" command : " + playerCommand.values());
        //update the direction of each snake
        for(String playerName : playerCommand.keySet()){
            performeActionCode(playersSnakes.get(playerName),playerCommand.get(playerName));
        }

        //update positions
        for(Snake s : snakes){
            s.getState().moveCurrentDirectionSpeed();
        }
        //update collisions

        List<Snake> tobeDeleted =new ArrayList<>();


        for(Snake s : snakes){
            if(s.getState().collisionManagement(snakes)==true){

                //updateScore before dying
                for(String playerName: getPlayers()){
                    if(getPlayersSnakes().get(playerName)==s) {
                        setScore(playerName,this.getScore(playerName));
                    }
                }

                tobeDeleted.add(s);
                //snakes.remove(s);

            }
        }
        for(Snake s : tobeDeleted){
            snakes.remove(s);
        }
        checkFood();
        checkBonuses();

    }

    private void performeActionTime(long threadTime) {

        if(threadTime>timeOfLastBonusSpawn+timeBetweenBonusSpawn) {
            this.spawnBonus();
            timeOfLastBonusSpawn=threadTime;
        }
        if(threadTime>timeOfLastFoodSpan+timeBetweenFoodSpawn){
            this.spawnFood();
            timeOfLastFoodSpan=threadTime;
        }
    }



    private void performeActionCode(Snake snake,String commande) {
        if(commande!=null) {
            switch (commande) {
                case SNAKE_GO_DOWN: snake.getState().moveDown();break;
                case SNAKE_GO_UP: snake.getState().moveUp();break;
                case SNAKE_GO_RIGHT: snake.getState().moveRight();break;
                case SNAKE_GO_LEFT: snake.getState().moveLeft();break;

                case SNAKE_TURN_LEFT: snake.getState().turnLeft();break;
                case SNAKE_TURN_RIGHT: snake.getState().turnRight();break;
                default:
            }
        }

    }

@Override
    public double getScore(String playerName){
        Snake snake=playersSnakes.get(playerName);
        //if dead, the value is already saved in the super class
        if(gameOver(playerName)){
            return super.getScore(playerName);
        }

        //sum of lengths this snake + deadSnakes

        else { //if alive
            double score=snake.getState().getBody().size();

            for(String player: getPlayers()) {

                if(gameOver(player) ) {
                    score=score+getScore(player);
                }


            }
            return score;
        }
    }

    public boolean gameOver(String playerName) {
        return !snakes.contains(playersSnakes.get(playerName));
    }




    @Override
    public void collectStats() {
        super.collectStats();

        String currentPlayer = AppSingleton.getInstance().getPlayer().getName();
        Game currentGame = AppSingleton.getInstance().getCurrentGame();
        SnakeStats mySnakeStats = (SnakeStats) AppSingleton.getInstance().getPlayer().getStats().getStatsForOneGame(currentGame);

        mySnakeStats.addLength(playersSnakes.get(currentPlayer).getState().getBody().size());


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

    public HashMap<String, Snake> getPlayersSnakes() {
        return playersSnakes;
    }


}

