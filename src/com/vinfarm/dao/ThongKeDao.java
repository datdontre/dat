/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vinfarm.dao;


import com.vinfarm.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NCThanh
 */
public class ThongKeDao {
      public List<Object[]> getlistOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = null;
            rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0;i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }                               
                list.add(vals);
               
            }
             rs.getStatement().getConnection().close();
                return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
//e.printStackTrace();
        }
//        return null;

    }
      public String getcol(String sql,String cols , Object... args){
          try {
             String a = "0";
             ResultSet rs = JdbcHelper.executeQuery(sql, args);
             while(rs.next()){
                 a = rs.getString(cols);
                 if(a== null){
                     a = "0";
                 }
             }
             return a;
          } catch (Exception e) {
              e.printStackTrace();
          }
          return null;
      }
    public String get_SLDonNhap_Thang(Integer Month) {
        String sql = "{call SL_DonNhap(?)}";
        String cols = "SL";
        return this.getcol(sql, cols, Month);
    }
 public String get_SLDonXuat_Thang(Integer Month) {
        String sql = "{call SL_DonXuat(?)}";
        String cols = "SL";
        return this.getcol(sql, cols, Month);
    }

  

    public List<Object[]> getTop3SP_muaMax(int month) {
        String sql = "{CALL SP_muaMax(?)}";
        String[] cols = {"MaSP", "MaLH", "TenSP", "GiaBan", "sl"};
        return this.getlistOfArray(sql, cols,month);
    }

    
     public List<Object[]> getTop3NCC(int month) {
        String sql = "{CALL DonNhap_NCC_Max(?)}";
        String[] cols = {"MaNCC","TenNCC","sl"};
        return this.getlistOfArray(sql, cols,month);
    }
      public String get_DoanhThu_Ngay(Integer day) {
        String sql = "{call DoanhThu_Ngay(?)}";
        String cols = "SUM";
        return this.getcol(sql, cols, day);
    }
         public String get_DoanhThu_Thang(Integer month ,Integer year ) {
        String sql = "{call DoanhThu_Thang(?,?)}";
        String cols = "sum";
        return this.getcol(sql, cols, month,year);
    }
            public String get_DoanhThu_Nam(Integer Year) {
        String sql = "{call DoanhThu_Nam(?)}";
        String cols = "sum";
        return this.getcol(sql, cols, Year);
    }
}
