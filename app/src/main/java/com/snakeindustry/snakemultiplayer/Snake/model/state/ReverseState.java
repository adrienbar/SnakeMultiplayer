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

        if (currentDirection == direction.left) {
            currentDirection = direction.down;
        }
        else if(currentDirection == direction.up){
            currentDirection = direction.left;
        }
        else if(currentDirection == direction.right){
            currentDirection = direction.up;
        }
        else if(currentDirection == direction.down){
            currentDirection = direction.right;
        }

        System.out.println("Snake turns Right");
    }

    public void turnLeft( ){
        if (currentDirection == direction.left) { // it is not possible to move
            // in the opposite direction
            currentDirection = direction.up;
        }
        else if(currentDirection == direction.down){
            currentDirection = direction.left;
        }
        else if(currentDirection == direction.right){
            currentDirection = direction.down;
        }
        else if(currentDirection == direction.up){
            currentDirection = direction.right;
        }
        System.out.println("Snake turns Left");
    }

    public void swipeTurn(int turndirection)
    {
        //Assume 0=Up,1=Right,2=Down,3=Left

        switch (turndirection){
            case 0:
                moveDown();
            case 1:
                moveLeft();
            case 2:
                moveUp();
            case 3:
                moveRight();
        }
    }
}
