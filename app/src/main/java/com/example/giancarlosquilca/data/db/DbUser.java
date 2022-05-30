package com.example.giancarlosquilca.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.giancarlosquilca.domain.models.User;

import java.util.ArrayList;

/**
 * Created by Giancarlos Quilca on 28/05/2022.
 */
public class DbUser extends DbHelper {

    Context context;

    public DbUser(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertUser(User user) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", user.getName());
            values.put("email", user.getEmail());
            values.put("photo", user.getPhoto());
            values.put("user", user.getUsername());
            values.put("password", user.getPassword());

            id = db.insert(TABLE_USER, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public User login(String username, String pass) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        User user = null;
        Cursor cursor;

        cursor = db.rawQuery("select * from tb_user where user = ? and password = ?" , new String[] {username,pass} );

        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPhoto(cursor.getString(3));
            user.setUsername(cursor.getString(4));
            user.setPassword("");
        }

        cursor.close();

        return user;
    }

    public ArrayList<User> getUsers() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<User> listUsers = new ArrayList<>();
        User user;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + TABLE_USER , null);

        if (cursor.moveToFirst()) {
            do {
                user = new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setPhoto(cursor.getString(3));
                user.setUsername(cursor.getString(4));
                user.setPassword(cursor.getString(5));
                listUsers.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return listUsers;
    }
}
