package com.snakeindustry.snakemultiplayer.Snake.model;

import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.EatableObject;
import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.Food;
import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.SnakeBonus;
import com.snakeindustry.snakemultiplayer.Snake.model.state.NormalState;
import com.snakeindustry.snakemultiplayer.Snake.model.state.State;

import java.util.LinkedList;


/**
 * Created by Adrien on 28/03/15.
 */


public class Snake {

    private State state;

    /*

     */
   // private final double heightScale=0.05;//proportion of the screen height;
   // private final double widthScale = 0.05; //heightScale*10.0/16.0; //most of screen are 16/10

    private String player;//The name of the player the snake belongs to



    public Snake(String player,double width, double length)
    {
        this.state= new NormalState(width,length);

        this.player=player;



    }

    /**
     * Default snake for testing
     */
    public Snake(){
        this.player="";
        this.state= new NormalState(0.05,0.05);
        this.state.setBody(new LinkedList<SnakeCell>());

        //for testing
        this.state.getBody().add(new SnakeCell(0.1,0.1));
        this.state.getBody().add(new SnakeCell(0.1,0.5));
        this.state.getBody().add(new SnakeCell(0.5,0.5));
    }

    public Snake(double x, double y){
        this.player="";
        this.state= new NormalState(0.05,0.05);
        this.state.setBody(new LinkedList<SnakeCell>());
        initializeBody(x,y);
        //for testing


    }

    public void initializeBody(double x, double y){

        this.state.getBody().add(new SnakeCell(x,y));
        if(x<0.5 && y<0.5){
            this.state.getBody().add(new SnakeCell(x-state.getWidth(),y));
            this.state.getBody().add(new SnakeCell(x-2*state.getWidth(),y));
            this.state.setCurrentDirection(State.direction.right);
        }
        else if(x<0.5 && y>0.5){

            this.state.getBody().add(new SnakeCell(x,y+state.getLength()));
            this.state.getBody().add(new SnakeCell(x,y+2*state.getLength()));
            this.state.setCurrentDirection(State.direction.up);

        }
        else if(x>0.5 && y<0.5){
            this.state.getBody().add(new SnakeCell(x,y-state.getLength()));
            this.state.getBody().add(new SnakeCell(x,y-2*state.getLength()));
            this.state.setCurrentDirection(State.direction.down);
        }
        else{
            this.state.getBody().add(new SnakeCell(x+state.getWidth(),y));
            this.state.getBody().add(new SnakeCell(x+2*state.getWidth(),y));
            this.state.setCurrentDirection(State.direction.left);
        }
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }



    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }








}
