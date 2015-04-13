package com.snakeindustry.snakemultiplayer.Snake.model.state;

import com.snakeindustry.snakemultiplayer.Snake.model.Snake;

import java.util.List;

/**
 * Created by Adrien on 28/03/15.
 */
public class NormalState extends State {

    public NormalState(double width, double length)
    {

        super(width,length);
    }

    public boolean collisionManagement( List<Snake> snakes){
        return false;
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
