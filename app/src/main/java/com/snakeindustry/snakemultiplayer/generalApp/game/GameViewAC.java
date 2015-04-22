package com.snakeindustry.snakemultiplayer.generalApp.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.network.finale.client.Client;

/**
 * Created by Adrien on 10/04/15.
 */
public abstract class GameViewAC extends SurfaceView implements GameView,SurfaceHolder.Callback {

    /**
     * should be another class enabling to communicate with the server's Thread over the network
     */
    private Client client;

    public GameViewAC(Context context, AttributeSet attrs) {
        super(context, attrs);

        client=AppSingleton.getInstance().getClient();
        //this as callback
        this.getHolder().addCallback(this);

        // make the GamePanel focusable
        this.setFocusable(true);
        //listeners
        SurfaceHolder holder = this.getHolder();
        holder.addCallback(this);

        AppSingleton.getInstance().getClient().setView(this);




    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas canvas =this.getHolder().lockCanvas();
        if(canvas!=null){
            canvas.drawColor(Color.WHITE);

            Bitmap bitmap=BitmapFactory.decodeResource(this.getResources(),AppSingleton.getInstance().getCurrentGame().getIdIcon());
            Rect des= new  Rect (this.getWidth()/2-this.getWidth()/4,  this.getHeight()/10, this.getWidth()/2+this.getWidth()/4, this.getHeight()/10+this.getWidth()/2);
            Rect src=new Rect (0,0,bitmap.getWidth(),bitmap.getHeight());

            canvas.drawBitmap(bitmap,src,des,new Paint());

            Paint paint=new Paint();
            paint.setTextSize(25);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("Waiting for server ..",this.getWidth()/3*2,this.getHeight()*2/3,paint);

            this.getHolder().unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //close Thread properly
        /*
         if(AppSingleton.getInstance().isServer()) {
             AppSingleton.getInstance().getCurrenGameTread().setRunning(false);
            boolean retry = true;
            while (retry) {
                try {
                    AppSingleton.getInstance().getCurrenGameTread().join();
                    retry= false;
                }
                catch  (InterruptedException e){
                }
        }

        }
        */
    }

    @Override
    public void draw(GameState gameState) {
        System.out.println("GAMEVIEW DRAW");
        if(gameState.isGameOver()){
            gameOverAction(gameState);
            drawGameOver(gameState);
        }
        else {
            drawGamePlay(gameState);
        }
    }

    public void gameOverAction(GameState gameState) {
        gameState.collectStats();
    }

    public abstract void drawGameOver(GameState gameState);

    public abstract void drawGamePlay(GameState gameState);




    //GETTERS AND SETTERS


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
