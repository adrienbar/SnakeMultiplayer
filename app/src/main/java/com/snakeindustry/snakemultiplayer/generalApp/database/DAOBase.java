package com.snakeindustry.snakemultiplayer.generalApp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Kriss on 17/04/2015.
 */
public abstract class DAOBase {
    // Nous sommes à la première version de la base
    // Si je décide de la mettre à jour, il faudra changer cet attribut

    protected final static int VERSION = 1;
    // Le nom du fichier qui représente ma base
    protected final static String NOM = "database.db";

    protected SQLiteDatabase dataBase = null;
    protected DatabaseHandler dataBaseHandler = null;

    public DAOBase(Context context) {
        this.dataBaseHandler = new DatabaseHandler(context, NOM, null, VERSION);
    }

    public SQLiteDatabase open() {
        // Pas besoin de fermer la dernière base puisque getWritableDatabase s'en charge
        dataBase = dataBaseHandler.getWritableDatabase();
        return dataBase;
    }

    public void close() {
        dataBase.close();
    }

    public SQLiteDatabase getDb() {
        return dataBase;
    }
}