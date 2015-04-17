package com.snakeindustry.snakemultiplayer.generalApp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Kriss on 17/04/2015.
 */
public class PlayerDAO extends DAOBase{

    public PlayerDAO(Context context) {
        super(context);
        open();
    }

    public String loadPlayeName(){
        String name;
        Cursor c = dataBase.rawQuery("select " + DatabaseHandler.PLAYER_NAME + " from " + DatabaseHandler.PLAYER_TABLE + " where "+DatabaseHandler.PLAYER_KEY+" = ?", new String[]{"1"});
        c.moveToFirst();


        try {
            System.out.println(c.getColumnCount());
            System.out.println("t");
            System.out.println(c.getColumnName(0));
            System.out.println("s");
            System.out.println(c.getColumnIndex(DatabaseHandler.PLAYER_NAME));
            System.out.println("In Load");
            System.out.println(c.getString(c.getColumnIndex(DatabaseHandler.PLAYER_NAME)));
            name = c.getString(c.getColumnIndex(DatabaseHandler.PLAYER_NAME));
        }catch (Exception e){
            name = "Error";
        }
        return name;
    }

    public void savePlayerName(String name){
        ContentValues value = new ContentValues();
        value.put(DatabaseHandler.PLAYER_NAME, name);
        dataBase.update(DatabaseHandler.PLAYER_TABLE, value, DatabaseHandler.PLAYER_KEY  + " = ?", new String[] {"1"});
    }

    public void createPlayer(String name){
        ContentValues value = new ContentValues();
        value.put(DatabaseHandler.PLAYER_NAME, name);
        value.put(DatabaseHandler.PLAYER_KEY, 1);
        dataBase.insert(DatabaseHandler.PLAYER_TABLE, null, value);

    }
}
