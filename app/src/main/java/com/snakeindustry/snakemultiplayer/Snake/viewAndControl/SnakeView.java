package com.snakeindustry.snakemultiplayer.Snake.viewAndControl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.provider.CalendarContract;
import android.util.AttributeSet;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.Snake.SnakeStats;
import com.snakeindustry.snakemultiplayer.Snake.model.Snake;
import com.snakeindustry.snakemultiplayer.Snake.model.SnakeGameState;
import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.*;
import com.snakeindustry.snakemultiplayer.Snake.model.state.FastState;
import com.snakeindustry.snakemultiplayer.Snake.model.state.InvincibleState;
import com.snakeindustry.snakemultiplayer.Snake.model.state.NormalState;
import com.snakeindustry.snakemultiplayer.Snake.model.state.ReverseState;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameThread;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameView;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameViewAC;
import com.snakeindustry.snakemultiplayer.generalApp.player.Player;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.SimpleStats;
import com.snakeindustry.snakemultiplayer.generalApp.player.stats.model.SimpleStatsC;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.LocalClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrien on 28/03/15.
 */
public abstract class SnakeView extends GameViewAC {

    private Bitmap none,invincible,reverse,fast;
    private boolean gameOver;

    public SnakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        none=Bitmap.createBitmap(2,1, Bitmap.Config.ARGB_8888);
        invincible=BitmapFactory.decodeResource(this.getResources(), R.drawable.star);
        reverse=BitmapFactory.decodeResource(this.getResources(), R.drawable.reverse);
        fast=BitmapFactory.decodeResource(this.getResources(), R.drawable.forward);

    }


    @Override
    public void draw(GameState gameState){


        if(!gameState.isGameOver()){
            drawGamePlay(gameState);
        }
        else {
            drawGameOver(gameState);
        }

    }


    private void drawGameOver(GameState gameState) {

        //SHOULD NOT BE THERE, just for testing
        gameOverAction(gameState);
        //

        Canvas canvas = this.getHolder().lockCanvas();


        canvas.drawColor(Color.WHITE);
        Paint paintScore=new Paint();
        paintScore.setTextAlign(Paint.Align.CENTER);
        paintScore.setTextSize(50);
        paintScore.setColor(Color.BLUE);


        double y0=this.getHeight()*0.25;
        double dy=0.10*this.getHeight();
        int i=1;
        canvas.drawText("GAME OVER",this.getWidth()/2,(float) (y0),paintScore);
        paintScore.setTextSize(40);
        SnakeGameState snakeGameState=(SnakeGameState) gameState;
        for(String playerName: snakeGameState.getPlayersSnakes().keySet()){
            String text=playerName+" "+snakeGameState.getScore(playerName);
            canvas.drawText(text,this.getWidth()/2,(float) (y0+i*dy),paintScore);
            i++;
        }

        this.getHolder().unlockCanvasAndPost(canvas);

    }


    /**
     * draw the gameState on with the holders of the view as canvas
     * gamestate includes Food, SnakeBonus and Snakes
     * @param gameState
     */
    public void drawGamePlay(GameState gameState) {

        Canvas canvas = this.getHolder().lockCanvas();
        canvas.drawColor(Color.WHITE);

        SnakeGameState snakeGameState=(SnakeGameState) gameState;

        //FOOD
        if(snakeGameState.getSpawnedFood()!=null){
            for(Food food : snakeGameState.getSpawnedFood()){
                this.drawEatableObject(food,canvas,"Food");
            }
        }

        //BONUS
        if(snakeGameState.getSpawnedBonuses()!=null){
            for(SnakeBonus snakeBonus: snakeGameState.getSpawnedBonuses()){
                this.drawBonus(snakeBonus,canvas);
            }
        }

        //SNAKES
        if(snakeGameState.getSnakes()!=null) {
            for (String playerName : snakeGameState.getPlayersSnakes().keySet()) {
                Snake snake=snakeGameState.getPlayersSnakes().get(playerName);
                if(((SnakeGameState) gameState).getSnakes().contains(snake)){
                    this.drawSnake(snake,canvas,playerName);
                }
            }
        }

        //Score
            Paint paintText = new Paint();
            paintText.setColor(Color.GREEN);
            //paintText.setTextSize(100);
            paintText.setTextAlign(Paint.Align.CENTER);
            paintText.setTextSize(20);
            paintText.setTextScaleX(2);
            String score="Your Score : "+snakeGameState.getScore(AppSingleton.getInstance().getPlayer().getName());
            canvas.drawText(score,this.getWidth()/2,(float) (0.05*this.getHeight()),paintText);


        //Loose

       if(!snakeGameState.getSnakes().contains(snakeGameState.getPlayersSnakes().get(AppSingleton.getInstance().getPlayer().getName()))){
           Paint paintLost = new Paint();
           paintLost.setColor(Color.RED);
           paintLost.setTextSize(50);
           paintLost.setTextAlign(Paint.Align.CENTER);
           //paintLost.setTextScaleX(7);
           String lost="YOU LOST";
           canvas.drawText(lost,this.getWidth()/2,this.getHeight()/2,paintLost);
       }




        this.getHolder().unlockCanvasAndPost(canvas);

    }


    /**
     * draw an eatableObject as a Rectangle
     * @param eatable to be drawn
     * @param canvas where to draw
     */
    private void drawEatableObject(EatableObject eatable,Canvas canvas,String name) {

        float x = (float) (eatable.getX()*this.getWidth());
        float y = (float) (eatable.getY()*this.getHeight());
        float h = (float) (eatable.getHeight()*this.getHeight());
        float w = (float) (eatable.getWidth()*this.getWidth());

        Paint paint =  new Paint();
        canvas.drawRect(x-w/2,y-h/2,x+w/2,y+h/2,paint);

        //put Name
        //paint.setColor(Color.BLUE);
        //canvas.drawText(name,x,y,paint);

    }

public void drawBonus(SnakeBonus bonus,Canvas canvas) {

    float x = (float) (bonus.getX()*this.getWidth());
    float y = (float) (bonus.getY()*this.getHeight());
    float h = (float) (bonus.getHeight()*this.getHeight());
    float w = (float) (bonus.getWidth()*this.getWidth());

    Bitmap bitmap=none;
    if(bonus instanceof ReverseBonus) { bitmap=reverse;}
    if(bonus instanceof InvincibleBonus) {bitmap=invincible;}
    if(bonus instanceof FastBonus) {bitmap=fast; }

    Paint background=new Paint();
    background.setStyle(Paint.Style.FILL);
    switch (bonus.getTarget()){
        case all: background.setColor(Color.BLUE);break;
        case self:background.setColor(Color.GREEN);break;
        case others:background.setColor(Color.RED);break;
    }

    Rect src=new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
    RectF destination=new RectF(x-w/2,y-h/2,x+w/2,y+h/2);

    Paint paintBorder=new Paint();
    Paint paintBitmap =new Paint();
    canvas.drawRect(destination,background);
    canvas.drawBitmap(bitmap,src,destination,background);
    //canvas.drawRect(destination,paintBorder);

}




    /**
     * Draw a snake as lines, with a Green head
     * @param snake  to be draw
     * @param canvas where to draw
     */
    private void drawSnake(Snake snake,Canvas canvas,String name) {

        Paint paintBody=new Paint();
        paintBody.setColor(Color.MAGENTA);

        Paint paintHead=new Paint();
        paintHead.setColor(Color.GRAY);


        boolean underBonus=!(snake.getState() instanceof NormalState);
        Bitmap bonus=none;
        if(underBonus) {
            if(snake.getState() instanceof ReverseState) { bonus=reverse;}
            if(snake.getState() instanceof InvincibleState) {bonus=invincible;}
            if(snake.getState() instanceof FastState) {bonus=fast; }
        }


        float x1,x2,y1,y2,h,w;
        h = (float) (snake.getState().getWidth()*this.getHeight());
        w = (float) (snake.getState().getWidth()*this.getWidth());

        x1= (float) (snake.getState().getBody().get(0).getX()*this.getWidth());
        y1 = (float) (snake.getState().getBody().get(0).getY()*this.getHeight());


        Rect src=new Rect(0,0,bonus.getWidth(),bonus.getHeight());
        RectF destination=new RectF(x1-w/2,y1-h/2,x1+w/2,y1+h/2);
        canvas.drawRect(destination,paintHead);
        if(underBonus) {
            canvas.drawBitmap(bonus,src,destination,paintHead);
        }


        //canvas.drawRect(x1-(w/2),y1-(h/2),x1+(w/2),y1+(h/2),paintBody);


        //DRAW A LINES BETWEEN EACH FOLLOWING POINTS OF THE SNAKE
        for(int i=1;i<snake.getState().getBody().size();i++){
            //define points
            x2=x1;
            y2=y1;


            if(name== AppSingleton.getInstance().getPlayer().getName()) {
                paintBody.setColor(Color.LTGRAY);
                name="";
            }

            x1 = (float) (snake.getState().getBody().get(i).getX()*this.getWidth());
            y1 = (float) (snake.getState().getBody().get(i).getY()*this.getHeight());

            Paint linePaint=new Paint(paintBody);


            destination=new RectF(x1-w/2,y1-h/2,x1+w/2,y1+h/2);

            canvas.drawRect(destination,paintBody);
            if(underBonus){
                canvas.drawBitmap(bonus,src,destination,paintBody);
            }
        }

        // HEAD
        // x1= (float) (snake.getState().getBody().get(0).getX()*this.getWidth());
        // y1 = (float) (snake.getState().getBody().get(0).getY()*this.getHeight());
        // canvas.drawRect(x1-(w/2),y1-(h/2),x1+(w/2),y1+(h/2),paintHead);


        //bonus time
        //  paint.setColor(Color.GRAY);
        //  canvas.drawRect(x1-(w/2),y1-(h/2),x1+(w/2),y1+(h/2),paint);

        // canvas.drawArc(x1-(w/2),y1-(h/2),x1+(w/2),y1+(h/2),0,180,true,paint);

        //put Name
        Paint paintName=new Paint();
        paintName.setColor(Color.BLUE);
        canvas.drawText(name,x1,y1,paintName);
    }

    public void gameOverAction(GameState gameState){
        SnakeGameState snakeGameState =(SnakeGameState) gameState;
        snakeGameState.gameOverAction();
    }

}
