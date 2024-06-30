package com.example.coffee.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffee.database.Dbhelper;
import com.example.coffee.model.Size;

import java.util.ArrayList;

public class SizeDAO {
    Dbhelper dbhelper;
    public SizeDAO(Context context){
        dbhelper = new Dbhelper(context);
    }

    public ArrayList<Size> getListSize(int madouong){
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        ArrayList<Size> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT s.masize, s.tensize, s.dongia, s.madouong FROM Size s JOIN DoUong du on du.madouong = s.madouong WHERE du.madouong = ?",new String[]{String.valueOf(madouong)});
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                list.add(new Size(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean capNhatSize(int masize, int dongia){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("dongia",dongia);
        long check = database.update("Size",values,"masize =?",new String[]{String.valueOf(masize)});
        return check != -1;
    }

    public boolean themSize(int madouong,String tensize, int dongia){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("madouong",madouong);
        contentValues.put("tensize",tensize);
        contentValues.put("dongia",dongia);

        long check = database.insert("Size",null,contentValues);
        return check != -1;
    }
}
