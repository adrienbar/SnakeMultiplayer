package com.snakeindustry.snakemultiplayer.Snake.model.eatableObject;

import com.snakeindustry.snakemultiplayer.Snake.model.state.State;

import java.util.UUID;

/**
 * Created by Adrien on 28/03/15.
 */

public abstract class SnakeBonus extends EatableObject {

    private State state;
    public enum target{all,self,others};
    private target target;
    private int duration;
    private UUID id;


    public SnakeBonus(double x, double y, double width, double height, State state,String bonus) {
        super(x, y, width, height);
        this.state=state;
        this.id=UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
