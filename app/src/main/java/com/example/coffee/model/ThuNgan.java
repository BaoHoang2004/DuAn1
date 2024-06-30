package com.example.coffee.model;

public class ThuNgan {
    private String matn;
    private String matkhau;
    private String tentn;
    private String loaitaikhoan;

    public ThuNgan(String matn, String matkhau, String tentn, String loaitaikhoan) {
        this.matn = matn;
        this.matkhau = matkhau;
        this.tentn = tentn;
        this.loaitaikhoan = loaitaikhoan;
    }

    public String getMatn() {
        return matn;
    }

    public void setMatn(String matn) {
        this.matn = matn;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getTentn() {
        return tentn;
    }

    public void setTentn(String tentn) {
        this.tentn = tentn;
    }

    public String getLoaitaikhoan() {
        return loaitaikhoan;
    }

    public void setLoaitaikhoan(String loaitaikhoan) {
        this.loaitaikhoan = loaitaikhoan;
    }
}
