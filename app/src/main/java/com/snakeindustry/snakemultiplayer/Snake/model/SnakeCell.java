package com.snakeindustry.snakemultiplayer.Snake.model;

/**
 * Created by Maxime on 03/04/2015.
 */
public class SnakeCell {

    private double x,y;

    public SnakeCell(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
