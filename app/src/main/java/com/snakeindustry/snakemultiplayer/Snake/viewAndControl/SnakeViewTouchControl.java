package com.snakeindustry.snakemultiplayer.Snake.viewAndControl;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.snakeindustry.snakemultiplayer.Snake.model.SnakeGameState;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;

/**
 * Created by Adrien on 28/03/15.
 */
public class SnakeViewTouchControl extends SnakeView {


    public SnakeViewTouchControl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * send to through the client to the server the command related to where the user has touched the screen
     * @param event
     * @return
     */
    public boolean onTouchEvent(MotionEvent event) {
        //screen's dimensions
        //int h =this.getHeight();
        int w = this.getWidth();

        float x;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x=event.getX();
                //Left Side of the Screen
                if(x<(w/2)) {

                    this.getClient().sendCommand(SnakeGameState.SNAKE_TURN_LEFT);
                    Log.d("Game2View", "Left side of the screen touched");
                }

                //Left Side of the Screen
                if(x>(w/2)) {
                    this.getClient().sendCommand(SnakeGameState.SNAKE_TURN_RIGHT);
                    Log.d("Game2View", "Right side of the screen touched");
                }
                break;
            default:

        }





        //event's position
        ///int x =(int) event.getX();
        //int y = (int) event.getY();

        //area are Rectangles




        return true;
    }

}
