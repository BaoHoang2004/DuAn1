package com.example.coffee.model;

import java.io.Serializable;

public class DoUong implements Serializable {
    private int madouong;
    private String tendouong;
    private String thongtin;
    private int trangthai;
    private int maloai;
    private String tenloai;
    private int dongia;
    private String tensize;
    private String avatar;

    public DoUong(String tendouong, int trangthai, int dongia) {
        this.tendouong = tendouong;
        this.trangthai = trangthai;
        this.dongia = dongia;
    }

    //du.tendouong , s.dongia, s.tensize, du.trangthai, du.thongtin , du.madouong
    public DoUong(String tendouong, int dongia, String tensize, int trangthai, String thongtin, int madouong, String avatar) {
        this.madouong = madouong;
        this.tendouong = tendouong;
        this.thongtin = thongtin;
        this.trangthai = trangthai;
        this.dongia = dongia;
        this.tensize = tensize;
        this.avatar = avatar;
    }
    public DoUong(String tendouong, int dongia, String tensize, int trangthai, String thongtin, int madouong, String avatar,int maloai) {
        this.madouong = madouong;
        this.tendouong = tendouong;
        this.thongtin = thongtin;
        this.trangthai = trangthai;
        this.dongia = dongia;
        this.tensize = tensize;
        this.avatar = avatar;
        this.maloai = maloai;
    }


    public DoUong(int madouong, String tendouong, String thongtin, int trangthai, int maloai, String tenloai, int dongia, String tensize) {
        this.madouong = madouong;
        this.tendouong = tendouong;
        this.thongtin = thongtin;
        this.trangthai = trangthai;
        this.maloai = maloai;
        this.tenloai = tenloai;
        this.dongia = dongia;
        this.tensize = tensize;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getMadouong() {
        return madouong;
    }

    public void setMadouong(int madouong) {
        this.madouong = madouong;
    }

    public String getTendouong() {
        return tendouong;
    }

    public void setTendouong(String tendouong) {
        this.tendouong = tendouong;
    }

    public String getThongtin() {
        return thongtin;
    }

    public void setThongtin(String thongtin) {
        this.thongtin = thongtin;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public String getTensize() {
        return tensize;
    }

    public void setTensize(String tensize) {
        this.tensize = tensize;
    }
}
