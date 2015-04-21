package com.snakeindustry.snakemultiplayer.Snake.viewAndControl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.snakeindustry.snakemultiplayer.Snake.model.Snake;
import com.snakeindustry.snakemultiplayer.Snake.model.SnakeGameState;
import com.snakeindustry.snakemultiplayer.Snake.model.state.State;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;

/**
 * Created by Adrien on 28/03/15.
 */
public class SnakeViewSwipeControl extends SnakeView {

    private float xStart,yStart;

    public SnakeViewSwipeControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        xStart=0;
        yStart=0;
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

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                 xStart=event.getX();
                 yStart=event.getY();
               // System.out.println("AAAAAAAA DOWN x0:"+xStart+" y0:"+yStart);
                break;

            case MotionEvent.ACTION_UP:
                float x=event.getX();
                float y= event.getY();

              //  System.out.println("AAAAAAAA UP x0:"+xStart+" y0:"+yStart +" x:"+x+" y:"+y);





                  System.out.println("AAAAAAAAAA"+this.getClient());
                SnakeGameState lastGameState = (SnakeGameState) this.getClient().getLastGameState();
                Snake snake =lastGameState.getPlayersSnakes().get(AppSingleton.getInstance().getPlayer().getName());



                if((snake.getState().getCurrentDirection()== State.direction.up || snake.getState().getCurrentDirection()== State.direction.down)
                        &&Math.abs(x-xStart)>Math.abs(y-yStart)) {

                    if(x>xStart) {
                        getClient().sendCommand(SnakeGameState.SNAKE_GO_RIGHT);
                    }

                    if(x<xStart) {
                        getClient().sendCommand(SnakeGameState.SNAKE_GO_LEFT);
                    }
                }


                if((snake.getState().getCurrentDirection()== State.direction.right || snake.getState().getCurrentDirection()== State.direction.left)
                &&Math.abs(x-xStart)<Math.abs(y-yStart)) {

                    if (y < yStart) {
                        getClient().sendCommand(SnakeGameState.SNAKE_GO_UP);
                    }
                    if (y > yStart) {
                        getClient().sendCommand(SnakeGameState.SNAKE_GO_DOWN);
                    }

                }
                break;
            default:

        }

        return true;
    }
}
