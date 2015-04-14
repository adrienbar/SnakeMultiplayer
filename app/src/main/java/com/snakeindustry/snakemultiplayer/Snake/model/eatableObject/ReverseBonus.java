package com.snakeindustry.snakemultiplayer.Snake.model.eatableObject;

import com.snakeindustry.snakemultiplayer.Snake.model.state.ReverseState;
import com.snakeindustry.snakemultiplayer.Snake.model.state.State;

/**
 * Created by Maxime on 14/04/2015.
 */
public class ReverseBonus extends SnakeBonus {

    public ReverseBonus(double x, double y, double width, double height, State state){
        super(x, y, width, height,new ReverseState(width,height));
        this.target=target.others;

    }

}
