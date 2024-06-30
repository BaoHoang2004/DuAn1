package com.example.coffee.model;

public class Size {
    private int masize;
    private String tensize;
    private int dongia;
    private int madouong;

    public Size(int masize, String tensize, int dongia, int madouong) {
        this.masize = masize;
        this.tensize = tensize;
        this.dongia = dongia;
        this.madouong = madouong;
    }

    public int getMasize() {
        return masize;
    }

    public void setMasize(int masize) {
        this.masize = masize;
    }

    public String getTensize() {
        return tensize;
    }

    public void setTensize(String tensize) {
        this.tensize = tensize;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getMadouong() {
        return madouong;
    }

    public void setMadouong(int madouong) {
        this.madouong = madouong;
    }
}
