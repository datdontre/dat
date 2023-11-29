package com.vinfarm.entity;

import java.io.Serializable;

/**
 *
 * @author Z18KAI
 */
public class LoaiHang implements Serializable{
    String maLoaiHang;
    String tenLoaiHang;

    public LoaiHang() {
    }

    public LoaiHang(String maLoaiHang, String tenLoaiHang) {
        this.maLoaiHang = maLoaiHang;
        this.tenLoaiHang = tenLoaiHang;
    }

    public String getMaLoaiHang() {
        return maLoaiHang;
    }

    public void setMaLoaiHang(String maLoaiHang) {
        this.maLoaiHang = maLoaiHang;
    }

    public String getTenLoaiHang() {
        return tenLoaiHang;
    }

    public void setTenLoaiHang(String tenLoaiHang) {
        this.tenLoaiHang = tenLoaiHang;
    }
    
    
}
