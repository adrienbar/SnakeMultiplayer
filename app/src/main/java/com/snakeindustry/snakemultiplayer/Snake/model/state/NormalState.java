package com.snakeindustry.snakemultiplayer.Snake.model.state;

import com.snakeindustry.snakemultiplayer.Snake.model.Snake;
import com.snakeindustry.snakemultiplayer.Snake.model.SnakeCell;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adrien on 28/03/15.
 */
public class NormalState extends State {

    public NormalState(LinkedList<SnakeCell> body,double width, double length)
    {

        super(body,width,length);
    }



}
