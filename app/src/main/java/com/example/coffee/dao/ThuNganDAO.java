package com.example.coffee.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffee.database.Dbhelper;
import com.example.coffee.model.ThuNgan;

public class ThuNganDAO {
    Dbhelper dbhelper;
    SharedPreferences sharedPreferences;
    public ThuNganDAO(Context context){
        dbhelper = new Dbhelper(context);
        sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
    }

    public boolean dangNhap(String matn, String matkhau){
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select*from ThuNgan where matn=? and matkhau =?",new String[]{matn,matkhau});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            SharedPreferences.Editor editor =sharedPreferences.edit();
            editor.putString("matn",cursor.getString(0));
            editor.putString("tentn",cursor.getString(2));
            editor.putString("loaitaikhoan",cursor.getString(3));
            editor.commit();//lưu dữ liệu vào data với key và value
            return true;
        }else {
            return false;
        }
    }
    public boolean capNhapMK(String usrename, String newpass, String oldpass){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select*from ThuNgan where matn=? and matkhau =?",new String[]{usrename,oldpass});
        if (cursor.getCount() >0){
            ContentValues values = new ContentValues();
            values.put("matkhau",newpass);
            long check =database.update("ThuNgan",values,"matn=?",new String[]{usrename});
            if (check == -1){
                return false;
            }
            return true;
        }
        return false;
    }

    public void themtaikhoan(String matn, String tentn,String matkhau, String loaitaikhoan){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matn",matn);
        values.put("tentn",tentn);
        values.put("matkhau",matkhau);
        values.put("loaitaikhoan",loaitaikhoan);

        database.insert("ThuNgan",null,values);
        database.close();
    }
    public boolean checkUserName(String matn){
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        Cursor cursor =database.rawQuery("select*from ThuNgan where matn=?",new String[]{matn});
        if (cursor.getCount() >0){
            return true;
        }else
            return false;
    }

}
