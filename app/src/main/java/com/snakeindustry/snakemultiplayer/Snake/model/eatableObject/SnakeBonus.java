package com.snakeindustry.snakemultiplayer.Snake.model.eatableObject;

import com.snakeindustry.snakemultiplayer.Snake.model.state.State;

/**
 * Created by Adrien on 28/03/15.
 */

public abstract class SnakeBonus extends EatableObject {

    private State state;
    public enum target{all,self,others};
    private target target;


    protected SnakeBonus(double x, double y, double width, double height, State state) {
        super(x, y, width, height);
        this.state=state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public target getTarget() {
        return target;
    }

    public void setTarget(target target) {
        this.target = target;
    }
}
