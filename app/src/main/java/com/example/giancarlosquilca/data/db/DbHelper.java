package com.example.giancarlosquilca.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Created by Giancarlos Quilca on 28/05/2022.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "lucky.db";
    public static final String TABLE_USER = "tb_user";
    public static final String TABLE_POINTSALE = "tb_pointsale";
    public static final String TABLE_PRODUCT = "tb_product";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USER + "(" +
                "id_user INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "photo TEXT NOT NULL," +
                "user TEXT NOT NULL," +
                "password TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_POINTSALE + "(" +
                "id_point INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "code TEXT  ," +
                "direction TEXT NOT NULL," +
                "photo TEXT NOT NULL)"
                );

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PRODUCT + "(" +
                "id_prod INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "cost INTEGER  NOT NULL," +
                "price INTEGER NOT NULL," +
                "stock INTEGER NOT NULL," +
                 "id_point  NOT NULL," +
                "FOREIGN KEY (id_point)  references tb_pointsale (id_point))"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PRODUCT);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_POINTSALE);
        onCreate(sqLiteDatabase);
    }
}