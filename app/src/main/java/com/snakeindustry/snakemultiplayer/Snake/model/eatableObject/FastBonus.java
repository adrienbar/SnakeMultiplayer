package com.snakeindustry.snakemultiplayer.Snake.model.eatableObject;

import com.snakeindustry.snakemultiplayer.Snake.model.state.FastState;
import com.snakeindustry.snakemultiplayer.Snake.model.state.State;

import java.util.UUID;

/**
 * Created by Maxime on 14/04/2015.
 */
public class FastBonus extends SnakeBonus {

    public FastBonus(double x, double y, double width, double height){
        super(x, y, width, height,new FastState(width,height));
       // this.target=target.self;

    }

    public FastBonus(double x, double y, double width, double height,UUID id, int duration){
        super(x, y, width, height,new FastState(width,height));
        //this.target=target.self;
        this.id=id;
        this.duration=duration;

    }
}
