package com.snakeindustry.snakemultiplayer.Snake.model.state;

import com.snakeindustry.snakemultiplayer.Snake.model.SnakeCell;

import java.util.LinkedList;

/**
 * Created by Maxime on 13/04/2015.
 */
public class FastState extends State {

    public FastState (LinkedList<SnakeCell> body,double width, double length){

        super(body,width,length);
        speed=0.6;


    }

    public FastState (double width, double length){

        super(width,length);
        speed=0.6;


    }

    //Test, not sure it will look good on screen to add speed like this
    public void moveCurrentDirection()
    {
        for(int i=0;i<speed;i++){
            addSquareToHead();
        }

        if (body.size() > 1) {
            for(int j=0;j<speed;j++) {
                if (growing == 0) {

                    body.removeLast();
                } else {
                    growing--;
                }
            }
        }

    }


}
