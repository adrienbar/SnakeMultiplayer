package com.snakeindustry.snakemultiplayer.generalApp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kriss on 17/04/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {


    public static final String PLAYER_TABLE = "player";
    public static final String PLAYER_NAME = "name";
    public static final String PLAYER_KEY = "id";

    public static final String CREATE_PLAYER_TABLE =  "CREATE TABLE " + PLAYER_TABLE + "(" +
            PLAYER_KEY + " INTEGER PRIMARY KEY, " +
            PLAYER_NAME + " TEXT);";
    public static final String TABLE_DROP_PLAYER_TABLE = "DROP TABLE IF EXISTS " + PLAYER_TABLE + ";";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLAYER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_DROP_PLAYER_TABLE);
        onCreate(db);
    }

}
