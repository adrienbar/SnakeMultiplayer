package com.snakeindustry.snakemultiplayer.Snake.model.state;

import com.snakeindustry.snakemultiplayer.Snake.model.Snake;
import com.snakeindustry.snakemultiplayer.Snake.model.SnakeCell;
import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.SnakeBonus;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adrien on 28/03/15.
 */
public abstract class State {

    protected double width; //Cell width
    protected double length; // Cell length
    protected double speed;
    protected LinkedList<SnakeCell> body;
    protected int growing;//Will be used to count the number of cells that have to grow
    public enum direction {
        down, left, right, up, none
    }
    protected direction currentDirection;

    public State(double width, double length){

        this.body=new LinkedList<SnakeCell>();
        this.width=width;
        this.length=length;
        speed=1;
        growing=0;


    }

    public State(LinkedList<SnakeCell> body,double width, double length){

        this.body=body;
        this.width=width;
        this.length=length;
        growing=0;

    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public LinkedList<SnakeCell> getBody() {
        return body;
    }

    public void setBody(LinkedList<SnakeCell> body) {
        this.body = body;
    }

    public int getGrowing() {
        return growing;
    }

    public void setGrowing(int growing) {
        this.growing = growing;
    }

    public direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double  width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    //Returns false if collision, true if not
    public boolean collisionManagement( List<Snake> snakes){
            //Check collision with other snakes
            for(Snake temp : snakes) {
                //Ignore our own body by checking head coordinates
                if(temp.getState().getBody().getFirst().getX()!=this.getBody().getFirst().getX() && temp.getState().getBody().getFirst().getY()!=this.getBody().getFirst().getY()) {

                    Iterator<SnakeCell> iter = temp.getState().getBody().iterator();
                    //Check all cells of the snake body
                    while(iter.hasNext()){
                        if(iter.next().getX()==this.getBody().getFirst().getX() && iter.next().getY()==this.getBody().getFirst().getY())
                        {
                            return true;
                        }
                }
            }


        }
        if(this.getBody().getFirst().getX() > 1 || this.getBody().getFirst().getY()>1){
            return true;
        }


        return false;
    }

    public void moveCurrentDirection()
    {
        addSquareToHead();
        if (body.size() > 1) {
            if(growing==0){
                body.removeLast();
            }
            else{
                growing--;
            }
        }

    }

    /**
     * adds a square in front of the current head of the snake (in the direction
     * specified in currentDirection).
     */
    protected void addSquareToHead() {
        SnakeCell head = body.getFirst();
        double newX =  head.getX();
        double newY =  head.getY();
        switch (currentDirection) {
            case up:
                newY-=speed*length;
                break;
            case down:
                newY+=speed*length;
                break;
            case left:
                newX-=speed*width;
                break;
            case right:
                newX+=speed*width;
                break;
            default:
                return;
        }
        body.addFirst( new SnakeCell(newX, newY));
    }

    /**
     * changes the current direction of the snake. On next
     * moveInCurrentDirection() the snake will move in this direction.
     */
    public void moveUp() {
        if (currentDirection != direction.down) { // it is not possible to move
            // in the opposite direction
            currentDirection = direction.up;
        }
    }

    /**
     * changes the current direction of the snake. On next
     * moveInCurrentDirection() the snake will move in this direction.
     */

    public void moveDown() {
        if (currentDirection != direction.up) { // it is not possible to move in
            // the opposite direction
            currentDirection = direction.down;
        }
    }

    /**
     * changes the current direction of the snake. On next
     * moveInCurrentDirection() the snake will move in this direction.
     */

    public void moveLeft() {
        if (currentDirection != direction.right) { // it is not possible to move
            // in the opposite direction
            currentDirection = direction.left;
        }
    }

    /**
     * changes the current direction of the snake. On next
     * moveInCurrentDirection() the snake will move in this direction.
     */
    public void moveRight() {
        if (currentDirection != direction.left) { // it is not possible to move
            // in the opposite direction
            currentDirection = direction.right;
        }
    }




    public void grow(int amount){
        growing+=amount;
    }

    public void turnRight(){

        if (currentDirection == direction.left) {
            currentDirection = direction.up;
        }
        else if(currentDirection == direction.up){
            currentDirection = direction.right;
        }
        else if(currentDirection == direction.right){
            currentDirection = direction.down;
        }
        else if(currentDirection == direction.down){
            currentDirection = direction.left;
        }

        System.out.println("Snake turns Right");
    }

    public void turnLeft( ){
        if (currentDirection == direction.left) { // it is not possible to move
            // in the opposite direction
            currentDirection = direction.down;
        }
        else if(currentDirection == direction.down){
            currentDirection = direction.right;
        }
        else if(currentDirection == direction.right){
            currentDirection = direction.up;
        }
        else if(currentDirection == direction.up){
            currentDirection = direction.left;
        }
        System.out.println("Snake turns Left");
    }

    public void swipeTurn(int turndirection)
    {
        //Assume 0=Up,1=Right,2=Down,3=Left

        switch (turndirection){
            case 0:
                moveUp();
            case 1:
                moveRight();
            case 2:
                moveDown();
            case 3:
                moveLeft();
        }
    }





}
