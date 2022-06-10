package com.n18dcat093.test_database.TTPCB;

import java.io.Serializable;

public class PCB implements Serializable {

    private String maPhieu, maMH, soBai;

    public PCB() {
    }

    public PCB(String maPhieu, String maMH, String soBai) {
        this.maPhieu = maPhieu;
        this.maMH = maMH;
        this.soBai = soBai;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getSoBai() {
        return soBai;
    }

    public void setSoBai(String soBai) {
        this.soBai = soBai;
    }
}
