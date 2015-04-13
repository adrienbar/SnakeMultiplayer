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


}
