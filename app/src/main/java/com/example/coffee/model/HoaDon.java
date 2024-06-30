package com.example.coffee.model;

import java.io.Serializable;

public class HoaDon implements Serializable {
    private int mahoadon;
    private String ngaymua;
    private String matn;
    private int trangthai;

    public HoaDon(String ngaymua, String matn, int trangthai) {
        this.ngaymua = ngaymua;
        this.matn = matn;
        this.trangthai = trangthai;
    }

    public HoaDon(int mahoadon, String ngaymua, String matn, int trangthai) {
        this.mahoadon = mahoadon;
        this.ngaymua = ngaymua;
        this.matn = matn;
        this.trangthai = trangthai;
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    public String getMatn() {
        return matn;
    }

    public void setMatn(String matn) {
        this.matn = matn;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
