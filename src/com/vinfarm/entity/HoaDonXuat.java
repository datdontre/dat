package com.vinfarm.entity;


/**
 *
 * @author Z18KAI
 */
public class HoaDonXuat {
    String soPhieu;
    String maNhanVien;
    String maKhachHang;
    float thanhTien;
    String ngayXuat;

    @Override
    public String toString() {
        return "HoaDonXuat{" + "soPhieu=" + soPhieu + ", maNhanVien=" + maNhanVien + ", maKhachHang=" + maKhachHang + ", thanhTien=" + thanhTien + ", ngayXuat=" + ngayXuat + '}';
    }

    public HoaDonXuat() {
    }

    public HoaDonXuat(String soPhieu, String maNhanVien, String maKhachHang, float thanhTien, String ngayXuat) {
        this.soPhieu = soPhieu;
        this.maNhanVien = maNhanVien;
        this.maKhachHang = maKhachHang;
        this.thanhTien = thanhTien;
        this.ngayXuat = ngayXuat;
    }

    public String getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(String soPhieu) {
        this.soPhieu = soPhieu;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        this.ngayXuat = ngayXuat;
    }
    
    
}
