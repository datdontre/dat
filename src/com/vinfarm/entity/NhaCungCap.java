package com.vinfarm.entity;


/**
 *
 * @author Z18KAI
 */
public class NhaCungCap {
    String maNhaCungCap;
    String tenNhaCungCap;
    String sdtNhaCungCap;
    String emailNhaCungCap;
    String diaChiNhaCungCap;

    public NhaCungCap() {
    }

    public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, String sdtNhaCungCap, String emailNhaCungCap, String diaChiNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.sdtNhaCungCap = sdtNhaCungCap;
        this.emailNhaCungCap = emailNhaCungCap;
        this.diaChiNhaCungCap = diaChiNhaCungCap;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getSdtNhaCungCap() {
        return sdtNhaCungCap;
    }

    public void setSdtNhaCungCap(String sdtNhaCungCap) {
        this.sdtNhaCungCap = sdtNhaCungCap;
    }

    public String getEmailNhaCungCap() {
        return emailNhaCungCap;
    }

    public void setEmailNhaCungCap(String emailNhaCungCap) {
        this.emailNhaCungCap = emailNhaCungCap;
    }

    public String getDiaChiNhaCungCap() {
        return diaChiNhaCungCap;
    }

    public void setDiaChiNhaCungCap(String diaChiNhaCungCap) {
        this.diaChiNhaCungCap = diaChiNhaCungCap;
    }
    
    
}
