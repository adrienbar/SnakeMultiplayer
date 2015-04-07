package com.snakeindustry.snakemultiplayer.Snake.model.state;

import com.snakeindustry.snakemultiplayer.Snake.model.Snake;
import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.SnakeBonus;

import java.util.List;

/**
 * Created by Adrien on 28/03/15.
 */
public abstract class State {

    //Returns false if collision, true if not
    public boolean collisionManagement( List<Snake> snakes){


        return false;
    }
}
