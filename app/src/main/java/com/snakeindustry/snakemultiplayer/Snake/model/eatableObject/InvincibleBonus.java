package com.snakeindustry.snakemultiplayer.Snake.model.eatableObject;

import com.snakeindustry.snakemultiplayer.Snake.model.state.FastState;
import com.snakeindustry.snakemultiplayer.Snake.model.state.InvincibleState;
import com.snakeindustry.snakemultiplayer.Snake.model.state.State;

import java.util.UUID;

/**
 * Created by Maxime on 14/04/2015.
 */
public class InvincibleBonus extends SnakeBonus {

    public InvincibleBonus(double x, double y, double width, double height){
        super(x, y, width, height,new InvincibleState(width,height));
       this.target=target.self;

    }

    public InvincibleBonus(double x, double y, double width, double height,UUID id, int duration){
        super(x, y, width, height,new InvincibleState(width,height));
        this.target=target.self;
        this.id=id;
        this.duration=duration;

    }
}
