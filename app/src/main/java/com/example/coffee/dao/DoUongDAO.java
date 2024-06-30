package com.example.coffee.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SyncRequest;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.coffee.database.Dbhelper;
import com.example.coffee.model.DoUong;

import java.security.PublicKey;
import java.util.ArrayList;

public class DoUongDAO {
    Dbhelper dbhelper;

    public DoUongDAO(Context context) {
        dbhelper = new Dbhelper(context);
    }

    public ArrayList<DoUong> getListDoUong() {
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        ArrayList<DoUong> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select du.tendouong , s.dongia, s.tensize, du.trangthai, du.thongtin, du.madouong,du.avatar, du.maloai from DoUong du, Size s where du.madouong = s.madouong group by du.tendouong , s.dongia, du.trangthai,s.tensize, du.thongtin, du.madouong, du.avatar, du.maloai HAVING tensize like 'Size S' ORDER BY du.madouong DESC LIMIT 10", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new DoUong(cursor.getString(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6), cursor.getInt(7)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<DoUong> getListDoUongSearch() {
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        ArrayList<DoUong> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select du.tendouong , s.dongia, s.tensize, du.trangthai, du.thongtin, du.madouong,du.avatar, du.maloai from DoUong du, Size s where du.madouong = s.madouong group by du.tendouong , s.dongia, du.trangthai,s.tensize, du.thongtin, du.madouong, du.avatar, du.maloai HAVING tensize like 'Size S'", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new DoUong(cursor.getString(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6), cursor.getInt(7)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<DoUong> getListDoUongLoai(int madouong) {
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        ArrayList<DoUong> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select du.tendouong , s.dongia, s.tensize, du.trangthai, du.thongtin, du.madouong,du.avatar,du.maloai from DoUong du, Size s where du.madouong = s.madouong group by du.tendouong , s.dongia, du.trangthai,s.tensize, du.thongtin, du.madouong, du.avatar, du.maloai HAVING tensize like 'Size S' AND du.maloai = ?", new String[]{String.valueOf(madouong)});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new DoUong(cursor.getString(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6), cursor.getInt(7)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public void capNhatTrangThai(int maDoUong, int trangThai) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("trangthai", trangThai);

        // Cập nhật hàng trong bảng dựa trên mã đồ uống
        db.update("DoUong", values, "madouong = ?", new String[]{String.valueOf(maDoUong)});

        db.close();
    }

    public boolean capNhatDoUong(int madouong, String tendouong, String thongtin) {
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tendouong", tendouong);
        values.put("thongtin", thongtin);

        long check = database.update("DoUong", values, "madouong = ?", new String[]{String.valueOf(madouong)});
        return check != -1;
    }

    public long themdouong(String tendu, String thongtin, int maloai, int trangthai, String avatar) {
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tendouong", tendu);
        values.put("thongtin", thongtin);
        values.put("maloai", maloai);
        values.put("trangthai", trangthai);
        values.put("avatar", avatar);
        long check = database.insert("DoUong", null, values);

        return check;

    }

    public boolean xoaDoUong(int madouong) {
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        long check = database.delete("DoUong", "madouong = ?", new String[]{String.valueOf(madouong)});
        return check != -1;
    }
}
