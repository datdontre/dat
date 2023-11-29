package com.vinfarm.entity;

import java.util.Date;

/**
 *
 * @author Z18KAI
 */
public class HoaDonNhap {
    String soPhieu;
    String ngayNhap;
    String maNhanVien;

    public HoaDonNhap() {
    }

    public HoaDonNhap(String soPhieu, String ngayNhap, String maNhanVien) {
        this.soPhieu = soPhieu;
        this.ngayNhap = ngayNhap;
        this.maNhanVien = maNhanVien;
    }

    public String getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(String soPhieu) {
        this.soPhieu = soPhieu;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
    
    
}
