package com.dvalenzuela.myapphuellas.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HuellasDB  extends SQLiteOpenHelper {
    public HuellasDB(Context context) {
        super(context, Constantes.NOMBRE_BD, null, Constantes.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + Constantes.NOMBRE_TABLA_USUARIO +
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " nombres TEXT NOT NULL," +
                        " apellidos TEXT NOT NULL," +
                        " correo TEXT NOT NULL," +
                        " contrasenia TEXT NOT NULL);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
