package com.vinfarm.entity;

/**
 *
 * @author Z18KAI
 */
public class ChiTietHoaDonNhap {
    String soPhieu;
    String maSanPham;
    int soLuong;
    int giaNhap;

    public ChiTietHoaDonNhap() {
    }

    public ChiTietHoaDonNhap(String soPhieu, String maSanPham, int soLuong, int giaNhap) {
        this.soPhieu = soPhieu;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
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

    public int getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(int giaNhap) {
        this.giaNhap = giaNhap;
    }
    
    
    
}
