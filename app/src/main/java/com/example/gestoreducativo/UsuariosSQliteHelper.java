package com.example.gestoreducativo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLClientInfoException;

public class UsuariosSQliteHelper extends SQLiteOpenHelper {

    String creacionSQL="Create table InicioSesion(idUsuario INTEGER primary key autoincrement,nombre TEXT,contasena TEXT)";
    String borradoSQL="Drop table if exists InicioSesion";

    public UsuariosSQliteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creacionSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(borradoSQL);
        db.execSQL(creacionSQL);
    }
}
