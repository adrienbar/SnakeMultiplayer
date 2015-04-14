package com.snakeindustry.snakemultiplayer.Snake.model.eatableObject;

import com.snakeindustry.snakemultiplayer.Snake.model.state.FastState;
import com.snakeindustry.snakemultiplayer.Snake.model.state.InvincibleState;
import com.snakeindustry.snakemultiplayer.Snake.model.state.State;

/**
 * Created by Maxime on 14/04/2015.
 */
public class InvincibleBonus extends SnakeBonus {

    public InvincibleBonus(double x, double y, double width, double height, State state){
        super(x, y, width, height,new InvincibleState(width,height));
        this.target=target.self;

    }
}
