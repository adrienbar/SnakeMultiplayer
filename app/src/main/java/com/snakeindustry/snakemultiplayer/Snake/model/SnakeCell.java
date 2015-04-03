package com.snakeindustry.snakemultiplayer.Snake.model;

/**
 * Created by Maxime on 03/04/2015.
 */
public class SnakeCell {

    private int x,y;

    public SnakeCell(int x,int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
