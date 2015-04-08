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
    private int width; //Cell width
    private int length; // Cell length
    private LinkedList<SnakeCell> body;
    private int growing;//Will be used to count the number of cells that have to grow
    private String player;//The name of the player the snake belongs to
    private enum direction {
        up, down, left, right, none
    }

    private direction currentDirection;

    public Snake(String player,int width, int length)
    {
        this.state= new NormalState();
        this.body=new LinkedList<SnakeCell>();
        this.player=player;
        this.width=width;
        this.length=length;
        growing=0;

    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    private void moveCurrentDirection()
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
    private void addSquareToHead() {
        SnakeCell head = body.getFirst();
        int newX = head.getX();
        int newY = head.getY();
        switch (currentDirection) {
            case up:
                newY-=length;
                break;
            case down:
                newY+=length;
                break;
            case left:
                newX-=width;
                break;
            case right:
                newX+=width;
                break;
            case none:
                return;
            default:
                return;
        }
        body.add(0, new SnakeCell(newX, newY));
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






}
