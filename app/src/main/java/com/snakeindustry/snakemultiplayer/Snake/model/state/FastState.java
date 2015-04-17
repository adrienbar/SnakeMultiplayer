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
        speed=0.5;


    }

}
