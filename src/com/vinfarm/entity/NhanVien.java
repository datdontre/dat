/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vinfarm.entity;

/**
 *
 * @author hau
 */
public class NhanVien {
    private String maNV;
    private String hoTen;
    private String SDT;
    private String email;
    private boolean vaiTro;
    private String matKhau;
    private String trangThai;

    public NhanVien() {
    }

    public NhanVien(String maNV, String hoTen, String SDT, String email, boolean vaiTro, String matKhau, String trangThai) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.SDT = SDT;
        this.email = email;
        this.vaiTro = vaiTro;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
