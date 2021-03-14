package com.example.shippingsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class thunderShippingDb extends SQLiteOpenHelper{
    public thunderShippingDb (Context context, String nombre, CursorFactory factory, int version){
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table envios( id integer PRIMARY KEY AUTOINCREMENT NOT NULL ,nombre text, peso double ,continente text, pais text, costo double)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue){
        db.execSQL("drop table if exists envios");
        db.execSQL("create table envios(id integer PRIMARY KEY AUTOINCREMENT NOT NULL ,nombre text, peso double ,continente text, pais text, costo double)");;
    }
}
