package com.example.heaven_motor.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.heaven_motor.model.Feedback;

import java.util.ArrayList;
import java.util.List;

public class FeedbackDao {
    SQL sqlite;
    SQLiteDatabase db;
    Context context;

    public FeedbackDao(Context context) {
        this.context = context;
        sqlite = new SQL(context);
        db = sqlite.getWritableDatabase();;
    }

    public int Insert(Feedback f){
        ContentValues values = new ContentValues();
        values.put("user_id",f.getUser_id());
        values.put("phanhoi",f.getPhanhoi());
        long kq = db.insert("Feedback",null,values);

        if (kq <= 0){
            return -1;
        }
        return 1;
    }
    @SuppressLint("Range")
    public List<Feedback> getData(String sql,String ...selectionArgs){
        List<Feedback> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            Feedback f = new Feedback();
            f.setId(c.getInt(c.getColumnIndex("id")));
            f.setUser_id(c.getString(c.getColumnIndex("user_id")));
            f.setPhanhoi(c.getString(c.getColumnIndex("phanhoi")));
           list.add(f);
        }
        return list;
    }
    public List<Feedback> getAll(){
        String  sql = "SELECT * FROM Feedback ORDER BY id DESC";
        return getData(sql);
    }
}
