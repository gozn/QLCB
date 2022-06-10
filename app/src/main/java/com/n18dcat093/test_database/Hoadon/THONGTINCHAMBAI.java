package com.n18dcat093.test_database.Hoadon;

import com.n18dcat093.test_database.MonHoc.MonHoc;
import com.n18dcat093.test_database.PhieuChamBai.PhieuChamBai;

public class THONGTINCHAMBAI {
    private String sobai;
    private MonHoc monHoc;
    private PhieuChamBai phieuChamBai;
    private String thanhtien;

    public String getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(String thanhtien) {
        this.thanhtien = thanhtien;
    }

    public String getSobai() {
        return sobai;
    }

    public void setSobai(String sobai) {
        this.sobai = sobai;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public PhieuChamBai getPhieuChamBai() {
        return phieuChamBai;
    }

    public void setPhieuChamBai(PhieuChamBai phieuChamBai) {
        this.phieuChamBai = phieuChamBai;
    }

    public THONGTINCHAMBAI(String sobai, MonHoc monHoc, PhieuChamBai phieuChamBai, String thanhtien) {
        this.sobai = sobai;
        this.monHoc = monHoc;
        this.phieuChamBai = phieuChamBai;
        this.thanhtien = thanhtien;
    }

    public THONGTINCHAMBAI() {
    }
}
