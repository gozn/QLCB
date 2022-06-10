package com.n18dcat093.test_database.GiaoVien;

public class GiaoVien {
    private String id;
    private String hoTen;
    private String sdt;
    private byte[] hinhanh;

    public GiaoVien() {
    }

    public GiaoVien(String id, String hoTen, String sdt, byte[] hinhanh) {
        this.id = id;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.hinhanh = hinhanh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public byte[] getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        this.hinhanh = hinhanh;
    }

    @Override
    public String toString() {
        return "GiaoVien{" +
                "id='" + id + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", sdt='" + sdt + '\'' +
                '}';
    }
}
