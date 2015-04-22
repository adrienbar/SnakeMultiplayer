package com.snakeindustry.snakemultiplayer.Snake.model.state;

import com.snakeindustry.snakemultiplayer.Snake.model.SnakeCell;

import java.util.LinkedList;

/**
 * Created by Maxime on 13/04/2015.
 */
public class ReverseState extends State{

    public ReverseState (LinkedList<SnakeCell> body,double width, double length){
        super(body,width,length);
    }

    public ReverseState (double width, double length){

        super(width,length);

    }

    public void turnRight(){

        super.turnLeft();
    }


    public void turnLeft( ) {
        super.turnRight();
    }


    public void moveUp(){
        super.moveDown();
    }

    public void moveDown(){
        super.moveUp();
    }

    public void moveRight(){
        super.moveLeft();
    }

    public  void moveLeft(){
        super.moveRight();
    }

}
