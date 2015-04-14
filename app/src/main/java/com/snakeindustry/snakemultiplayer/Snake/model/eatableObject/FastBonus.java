package com.snakeindustry.snakemultiplayer.Snake.model.eatableObject;

import com.snakeindustry.snakemultiplayer.Snake.model.state.FastState;
import com.snakeindustry.snakemultiplayer.Snake.model.state.State;

/**
 * Created by Maxime on 14/04/2015.
 */
public class FastBonus extends SnakeBonus {

    public FastBonus(double x, double y, double width, double height, State state){
        super(x, y, width, height,new FastState(width,height));
        this.target=target.self;

    }
}
