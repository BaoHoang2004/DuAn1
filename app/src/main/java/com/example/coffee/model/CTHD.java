package com.example.coffee.model;

public class CTHD {
    private int macthd;
    private int mahoadon;
    private int soluong;
    private String ghichu;
    private int giatien;
    private int trangthai;
    private int masize;
    private String tendouong;
    private String tensize;
    private String ngaymua;
    private String avatar;

//mahoadon integer references HoaDon(mahoadon), soluong integer, ghichu text, giatien integer, trangthai integer, masize
    public CTHD(int mahoadon, int soluong, String ghichu, int giatien, int masize) {
        this.mahoadon = mahoadon;
        this.soluong = soluong;
        this.ghichu = ghichu;
        this.giatien = giatien;
        this.masize = masize;

    }
    //macthd integer primary key autoincrement, mahoadon integer references HoaDon(mahoadon), soluong integer, ghichu text, giatien integer, trangthai integer, masize integer

    public CTHD(int macthd, int mahoadon, int soluong, String ghichu, int giatien, int trangthai, int masize) {
        this.macthd = macthd;
        this.mahoadon = mahoadon;
        this.soluong = soluong;
        this.ghichu = ghichu;
        this.giatien = giatien;
        this.trangthai = trangthai;
        this.masize = masize;
    }

               //ct.macthd, ct.mahoadon, ct.soluong, ct.ghichu, ct.giatien, ct.trangthai, s.masize, du.tendouong, s.tensize, hd.ngaymua
    public CTHD(int macthd, int mahoadon, int soluong, String ghichu, int giatien, int trangthai, int masize, String tendouong, String tensize, String ngaymua) {
        this.macthd = macthd;
        this.mahoadon = mahoadon;
        this.soluong = soluong;
        this.ghichu = ghichu;
        this.giatien = giatien;
        this.trangthai = trangthai;
        this.masize = masize;
        this.tendouong = tendouong;
        this.tensize = tensize;
        this.ngaymua = ngaymua;
    }
    public CTHD(int macthd, int mahoadon, int soluong, String ghichu, int giatien, int trangthai, int masize, String tendouong, String tensize, String ngaymua,String avatar) {
        this.macthd = macthd;
        this.mahoadon = mahoadon;
        this.soluong = soluong;
        this.ghichu = ghichu;
        this.giatien = giatien;
        this.trangthai = trangthai;
        this.masize = masize;
        this.tendouong = tendouong;
        this.tensize = tensize;
        this.ngaymua = ngaymua;
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTensize() {
        return tensize;
    }

    public void setTensize(String tensize) {
        this.tensize = tensize;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    //ct.macthd, ct.macthd, ct.soluong, ct.ghichu, ct.giatien, ct.trangthai, s.masize, du.tendouong


    public int getMacthd() {
        return macthd;
    }

    public void setMacthd(int macthd) {
        this.macthd = macthd;
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getGiatien() {
        return giatien;
    }

    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getMasize() {
        return masize;
    }

    public void setMasize(int masize) {
        this.masize = masize;
    }

    public String getTendouong() {
        return tendouong;
    }

    public void setTendouong(String tendouong) {
        this.tendouong = tendouong;
    }
}
