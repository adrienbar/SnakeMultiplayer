package com.snakeindustry.snakemultiplayer.Snake.viewAndControl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.snakeindustry.snakemultiplayer.Snake.model.Snake;
import com.snakeindustry.snakemultiplayer.Snake.model.SnakeGameState;
import com.snakeindustry.snakemultiplayer.Snake.model.state.State;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;

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
        int h =this.getHeight();
        int w = this.getWidth();

        float x,y;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x=event.getX();
                y=event.getY();
                //Left Side of the Screen


                SnakeGameState lastGameState = (SnakeGameState) this.getClient().getLastGameState();
                Snake snake =lastGameState.getPlayersSnakes().get(AppSingleton.getInstance().getPlayer().getName());

                double xSnake = snake.getState().getBody().getFirst().getX()*w;
                double ySnake = snake.getState().getBody().getFirst().getY()*h;


                if((snake.getState().getCurrentDirection()== State.direction.down||snake.getState().getCurrentDirection()== State.direction.up)
                         ){ //&& 2*Math.abs(x-xSnake)>Math.abs(y-ySnake)

                    if (x<xSnake) {
                        this.getClient().sendCommand(SnakeGameState.SNAKE_GO_LEFT);
                    }

                    if (x>xSnake) {
                        this.getClient().sendCommand(SnakeGameState.SNAKE_GO_RIGHT);
                    }
                }


                if((snake.getState().getCurrentDirection()== State.direction.right||snake.getState().getCurrentDirection()== State.direction.left)
                        ) {//&& Math.abs(x-xSnake)<2*Math.abs(y-ySnake)
                    if (y<snake.getState().getBody().getFirst().getY()*h) {
                        this.getClient().sendCommand(SnakeGameState.SNAKE_GO_UP);
                    }

                    if (y>snake.getState().getBody().getFirst().getY()*h) {
                        this.getClient().sendCommand(SnakeGameState.SNAKE_GO_DOWN);
                    }
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
