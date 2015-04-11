package com.snakeindustry.snakemultiplayer.generalApp.game;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.snakeindustry.snakemultiplayer.generalApp.AppSingleton;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.Client;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.LocalClient;
import com.snakeindustry.snakemultiplayer.generalApp.pseudoNetwork.LocalClientI;

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

        //this as callback
        this.getHolder().addCallback(this);

        // make the GamePanel focusable
        this.setFocusable(true);
        //listeners
        SurfaceHolder holder = this.getHolder();
        holder.addCallback(this);


        //SET CLIENT ACCORDING TO WHERE IS THE SERVER
        if(AppSingleton.getInstance().isServer()) {
            LocalClient localClient = new LocalClient(AppSingleton.getInstance().getCurrenGameTread(),this);
            this.client = localClient;
            AppSingleton.getInstance().getCurrenGameTread().getServer().setLocalClientI(localClient);
        }
        else {
            //distant client no implemented yet
        }

    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(this.getClient() instanceof LocalClientI) {
            LocalClientI localClient=(LocalClientI) this.getClient();
            localClient.getGameThread().start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        //close Thread properly

         if(this.getClient() instanceof LocalClientI) {
            LocalClientI localClient=(LocalClientI) this.getClient();
            localClient.getGameThread().setRunning(false);
            boolean retry = true;
            while (retry) {
                try {
                    localClient.getGameThread().join();
                    retry= false;
                }
                catch  (InterruptedException e){
                }
        }

        }
    }





    //GETTERS AND SETTERS


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
