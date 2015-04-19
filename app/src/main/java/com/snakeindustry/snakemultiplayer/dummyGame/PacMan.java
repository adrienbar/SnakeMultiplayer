package com.snakeindustry.snakemultiplayer.dummyGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.snakeindustry.snakemultiplayer.R;
import com.snakeindustry.snakemultiplayer.generalApp.game.DefaultGame;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameState;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameView;
import com.snakeindustry.snakemultiplayer.generalApp.game.GameViewAC;

/**
 * Created by Adrien on 19/04/15.
 */
public class PacMan extends DefaultGame {


    private static PacMan instance = null;
    private PacMan() {
        super("Moke Pacman", R.drawable.pacman);
    }

    public static synchronized DefaultGame getInstance() {
        if (instance == null) {
            instance = new PacMan();
        }
        return instance;
    }

    @Override
    public GameView getGameView(Context context, AttributeSet attributeSet) {
        return new GameViewAC(context,attributeSet) {
            @Override
            public void draw(GameState gameState) {

                Canvas canvas = this.getHolder().lockCanvas();


                canvas.drawColor(Color.BLUE
                );

                Paint paintScore=new Paint();
                paintScore.setTextAlign(Paint.Align.CENTER);
                paintScore.setTextSize(50);
                paintScore.setColor(Color.BLACK);

                double y0=this.getHeight()*0.25;
                double dy=0.10*this.getHeight();
                int i=1;

                canvas.drawText(getName(),this.getWidth()/2,(float) (y0),paintScore);
                paintScore.setTextSize(40);


                for(String playerName: gameState.getPlayers()){
                    String text=playerName+" "+gameState.getScore(playerName);
                    canvas.drawText(text,this.getWidth()/2,(float) (y0+i*dy),paintScore);
                    i++;
                }

                this.getHolder().unlockCanvasAndPost(canvas);
            }
        };
    }
}
