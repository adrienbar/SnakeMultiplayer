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
        speed=0.2;
        growing=0;


    }

    public State(LinkedList<SnakeCell> body,double width, double length){

        this.body=body;
        this.width=width;
        this.length=length;
        growing=0;
        speed=0.2;

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


                    Iterator<SnakeCell> iter = temp.getState().getBody().iterator();
                    //Check all cells of the snake body
                       if(temp.getState().getBody().getFirst().getX()==this.getBody().getFirst().getX() &&temp.getState().getBody().getFirst().getY()==this.getBody().getFirst().getY() ){
                           //If it is our own body, jump head and first 2 cells that stacks during the turns:
                           iter.next();
                           iter.next();
                           iter.next();
                       }
                    while(iter.hasNext()){
                        SnakeCell tempcell = iter.next();

                        if(tempcell.getX()-width/2 <= this.getBody().getFirst().getX()+width/2 &&
                           tempcell.getX()+width/2 >= this.getBody().getFirst().getX()-width/2 &&
                           tempcell.getY()+length/2 >= this.getBody().getFirst().getY()-length/2 &&
                           tempcell.getY()-length/2 <= this.getBody().getFirst().getY()+length/2) {

                            return true;
                        }
                     }

            }
            if(this.getBody().getFirst().getX() > 1 || this.getBody().getFirst().getY()>1
               || this.getBody().getFirst().getY()<0 ||this.getBody().getFirst().getX()<0 ){
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

    public void moveCurrentDirectionSpeed(){
        Iterator<SnakeCell> iter = body.iterator();
        direction cellDirection=currentDirection;
        SnakeCell prev = null;
       // SnakeCell prevPostChanges = body.getFirst();
        if(speed !=0.2 ){
            System.out.println("Test");
        }
        while(iter.hasNext()){
            SnakeCell temp = iter.next();
            double newX =  temp.getX();
            double newY =  temp.getY();
            if(prev!=null) {
                if (!(Math.abs(prev.getY() - temp.getY()) < 0.005) && cellDirection == direction.left || !(Math.abs(prev.getY() - temp.getY()) < 0.005) && cellDirection == direction.right) {
                    if ( prev.getY() > temp.getY()) {
                        cellDirection = direction.down;
                    } else{
                        cellDirection = direction.up;
                    }
                } else if ( !(Math.abs(prev.getX() - temp.getX()) < 0.005) && cellDirection == direction.down || !(Math.abs(prev.getX() - temp.getX()) < 0.005) && cellDirection == direction.up) {
                    if ( prev.getX() > temp.getX()) {
                        cellDirection = direction.right;
                    } else{
                        cellDirection = direction.left;
                    }
                }
            }

            switch (cellDirection) {
                case up:
                    //If cells are not aligned bacause of speed difference
                    if(prev !=null && (newY-speed*length)<prev.getY()){
                      //Line it back
                      newY=prev.getY();
                      //Prevent gap between two cells from appearing
                      if(prev.getX()>temp.getX()){
                          newX=prev.getX()-length;
                      }
                        else{
                          newX=prev.getX()+length;
                      }
                    }else {
                        newY-=speed*length;
                    }
                    break;
                case down:

                   if(prev !=null && newY+(speed*length)>prev.getY()){
                        newY=prev.getY();
                       if(prev.getX()>temp.getX()){
                           newX=prev.getX()-length;
                       }
                       else{
                           newX=prev.getX()+length;
                       }
                    }else{
                        newY+=speed*length;
                    }
                    break;
                case left:

                    if(prev !=null && (newX-speed*length)<prev.getX()){
                        newX=prev.getX();
                        if(prev.getY()>temp.getY()){
                            newY=prev.getY()-length;
                        }
                        else{
                            newY=prev.getY()+length;
                        }
                    }else{
                        newX-=speed*width;
                    }
                    break;
                case right:

                    if(prev !=null && (newX+speed*length)>prev.getX()){
                        newX=prev.getX();
                        if(prev.getY()>temp.getY()){
                            newY=prev.getY()-length;
                        }
                        else{
                            newY=prev.getY()+length;
                        }
                    }else {
                        newX+=speed*width;
                    }
                    break;
                }
            prev=temp;
            temp.setX(newX);
            temp.setY(newY);
          

        }

        if(growing>0){

            switch (cellDirection) {
                case up:
                    body.addLast(new SnakeCell(body.getLast().getX(),body.getLast().getY()+length));
                    break;
                case down:
                    body.addLast(new SnakeCell(body.getLast().getX(),body.getLast().getY()-length));
                    break;
                case left:
                    body.addLast(new SnakeCell(body.getLast().getX()+width,body.getLast().getY()));
                    break;
                case right:
                    body.addLast(new SnakeCell(body.getLast().getX()-width,body.getLast().getY()));
                    break;
            }
            growing--;

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

    public void goTo(int turndirection)
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
