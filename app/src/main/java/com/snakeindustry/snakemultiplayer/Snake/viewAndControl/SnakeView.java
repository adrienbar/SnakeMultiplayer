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
import com.snakeindustry.snakemultiplayer.Snake.model.Snake;
import com.snakeindustry.snakemultiplayer.Snake.model.SnakeGameState;
import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.EatableObject;
import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.FastBonus;
import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.Food;
import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.InvincibleBonus;
import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.ReverseBonus;
import com.snakeindustry.snakemultiplayer.Snake.model.eatableObject.SnakeBonus;
import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameThread;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameView;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameViewAC;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.LocalClient;

import java.util.ArrayList;

/**
 * Created by Adrien on 28/03/15.
 */
public abstract class SnakeView extends GameViewAC {


    public SnakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * draw the gameState on with the holders of the view as canvas
     * gamestate includes Food, SnakeBonus and Snakes
     * @param gameState
     */
    @Override
    public void draw(GameState gameState) {

        Canvas canvas = this.getHolder().lockCanvas();

        canvas.drawColor(Color.WHITE);

        SnakeGameState snakeGameState=(SnakeGameState) gameState;


        //FOOD
        if(snakeGameState.getSpawnedFood()!=null){
            Paint paintFood=new Paint();
            for(Food food : snakeGameState.getSpawnedFood()){
                paintFood.setColor(Color.GREEN);
                this.drawEatableObject(food,paintFood,canvas,"Food");
            }
        }


        //BONUS
        if(snakeGameState.getSpawnedBonuses()!=null){
            Paint paintBonus=new Paint();
            paintBonus.setColor(Color.GREEN);
            paintBonus.setStyle(Paint.Style.STROKE);

            for(SnakeBonus snakeBonus: snakeGameState.getSpawnedBonuses()){

                this.drawBonus(snakeBonus,canvas,paintBonus);
            }
        }



        //SNAKES


        if(snakeGameState.getSnakes()!=null) {
            Paint paintSnake=new Paint();
            paintSnake.setColor(Color.BLACK);
            paintSnake.setStyle(Paint.Style.FILL);

            for (String playerName : snakeGameState.getPlayersSnakes().keySet()) {

                Snake snake=snakeGameState.getPlayersSnakes().get(playerName);

                if(playerName== AppSingleton.getInstance().getPlayer().getName()) {
                    paintSnake.setColor(Color.RED);
                    playerName="";
                }
                this.drawSnake(snake, paintSnake,canvas,playerName);
                paintSnake.setColor(Color.BLACK);
            }
        }

       // Paint paint=new Paint();
      //  paint.setColor(Color.BLUE);
      //  this.drawEatableObject(new Food(0.1, 0.1, 0.5, 0.5), paint, canvas);
      //  paint.setColor(Color.RED);
      //  paint.setStyle(Paint.Style.FILL);
      //  this.drawSnake(new Snake(""),paint,canvas);

        this.getHolder().unlockCanvasAndPost(canvas);
    }

    /**
     * draw an eatableObject as a Rectangle
     * @param eatable to be drawn
     * @param paint how to draw
     * @param canvas where to draw
     */
    private void drawEatableObject(EatableObject eatable,Paint paint,Canvas canvas,String name) {

        float x = (float) (eatable.getX()*this.getWidth());
        float y = (float) (eatable.getY()*this.getHeight());
        float h = (float) (eatable.getHeight()*this.getHeight());
        float w = (float) (eatable.getWidth()*this.getWidth());


        canvas.drawRect(x-w/2,y-h/2,x+w/2,y+h/2,paint);

        //put Name
        //paint.setColor(Color.BLUE);
        //canvas.drawText(name,x,y,paint);

    }

public void drawBonus(SnakeBonus bonus,Canvas canvas,Paint paint) {

    float x = (float) (bonus.getX()*this.getWidth());
    float y = (float) (bonus.getY()*this.getHeight());
    float h = (float) (bonus.getHeight()*this.getHeight());
    float w = (float) (bonus.getWidth()*this.getWidth());





    int ressourcePictureId=R.drawable.forward;

    if(bonus instanceof ReverseBonus) { ressourcePictureId=R.drawable.reverse;}
    if(bonus instanceof InvincibleBonus) {ressourcePictureId=R.drawable.star;}
    if(bonus instanceof FastBonus) {ressourcePictureId=R.drawable.forward; }
    Bitmap bitmap=BitmapFactory.decodeResource(this.getResources(), ressourcePictureId);
    Rect src=new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
    RectF destination=new RectF(x-w/2,y-h/2,x+w/2,y+h/2);
    canvas.drawBitmap(bitmap,src,destination,paint);
    canvas.drawRect(destination,paint);

}




    /**
     * Draw a snake as lines, with a Green head
     * @param snake  to be draw
     * @param paint  how to draw
     * @param canvas where to draw
     */
    private void drawSnake(Snake snake,Paint paint,Canvas canvas,String name) {

        float x1,x2,y1,y2,h,w;

        h = (float) (snake.getState().getWidth()*this.getHeight());
        w = (float) (snake.getState().getWidth()*this.getWidth());

        x1= (float) (snake.getState().getBody().get(0).getX()*this.getWidth());
        y1 = (float) (snake.getState().getBody().get(0).getY()*this.getHeight());

        canvas.drawRect(x1-(w/2),y1-(h/2),x1+(w/2),y1+(h/2),paint);

        //DRAW A LINES BETWEEN EACH FOLLOWING POINTS OF THE SNAKE
        for(int i=1;i<snake.getState().getBody().size();i++){
            //define points
            x2=x1;
            y2=y1;

            x1 = (float) (snake.getState().getBody().get(i).getX()*this.getWidth());
            y1 = (float) (snake.getState().getBody().get(i).getY()*this.getHeight());


            //accords the Stroke of the Line to the height or width of the cell which could be different
            Paint linePaint=new Paint(paint);
            if(x1==x2) {
                linePaint.setStrokeWidth(w);
            }
            else {
                linePaint.setStrokeWidth(h);
            }


            //DRAW LINE WITH ITS EXTREMITIES ON THE CANVAS
            //draws the line between 2 points
           // canvas.drawLine(x1-(w/2),y1-(h/2),x2+(w/2),y2+(h/2), linePaint);

            //draw the point to avoid bad corners
            canvas.drawRect(x1-(w/2),y1-(h/2),x1+(w/2),y1+(h/2),paint);
        }

        // Make the snake's head green
        x1= (float) (snake.getState().getBody().get(0).getX()*this.getWidth());
        y1 = (float) (snake.getState().getBody().get(0).getY()*this.getHeight());
        paint.setColor(Color.GREEN);
        canvas.drawRect(x1-(w/2),y1-(h/2),x1+(w/2),y1+(h/2),paint);

        //put Name
        paint.setColor(Color.BLUE);
        canvas.drawText(name,x1,y1,paint);

    }



}
