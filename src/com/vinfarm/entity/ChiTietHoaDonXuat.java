package com.vinfarm.entity;

/**
 *
 * @author Z18KAI
 */
public class ChiTietHoaDonXuat {
    String soPhieu;
    String maSanPham;
    int soLuong;
    float giamGia;

    public ChiTietHoaDonXuat() {
    }

    @Override
    public String toString() {
        return "ChiTietHoaDonXuat{" + "soPhieu=" + soPhieu + ", maSanPham=" + maSanPham + ", soLuong=" + soLuong + ", giamGia=" + giamGia + '}';
    }

    public ChiTietHoaDonXuat(String soPhieu, String maSanPham, int soLuong, float giamGia) {
        this.soPhieu = soPhieu;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.giamGia = giamGia;
    }

    public String getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(String soPhieu) {
        this.soPhieu = soPhieu;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(float giamGia) {
        this.giamGia = giamGia;
    }
    
    
}
