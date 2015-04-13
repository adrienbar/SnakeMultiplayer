package com.snakeindustry.snakemultiplayer.Snake.model.state;

import com.snakeindustry.snakemultiplayer.Snake.model.Snake;
import com.snakeindustry.snakemultiplayer.Snake.model.SnakeCell;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Maxime on 13/04/2015.
 */
public class InvincibleState extends State {

    public InvincibleState (LinkedList<SnakeCell> body,double width, double length){

        super(body,width,length);
       }

    //Returns false if collision, true if not
    public boolean collisionManagement( List<Snake> snakes){
        //Only checks if we leave the screen
        if(this.getBody().getFirst().getX() > 1 || this.getBody().getFirst().getY()>1){
            return true;
        }


        return false;
    }
}
