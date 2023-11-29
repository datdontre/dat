package com.vinfarm.dao;

import com.vinfarm.entity.LoaiHang;
import com.vinfarm.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Z18KAI
 */
public class LoaiHangDAO {
    public void insert(LoaiHang model) {
        String sql = "INSERT INTO LoaiHang (MaLH, TenLH) VALUES (UPPER(?), ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaLoaiHang(),
                model.getTenLoaiHang());
    }

    public void update(LoaiHang model) {
        String sql = "UPDATE LoaiHang SET TenLH=? WHERE MaLH=?";
        JdbcHelper.executeUpdate(sql,
                model.getTenLoaiHang(),
                model.getMaLoaiHang());
    }

    public void delete(String MaKH) {
        String sql = "DELETE FROM LoaiHang WHERE MaLH=?";
        JdbcHelper.executeUpdate(sql, MaKH);
    }

    public List<LoaiHang> select() {
        String sql = "SELECT * FROM LoaiHang";
        return select(sql);
    }

    public LoaiHang findById(String makh) {
        String sql = "SELECT * FROM LoaiHang WHERE MaLH=?";
        List<LoaiHang> list = select(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }

    public LoaiHang findByName(String namekh) {
        String sql = "SELECT * FROM LoaiHang WHERE TenLH like N'%'+?+'%'";
        List<LoaiHang> list = select(sql, namekh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<LoaiHang> select(String sql, Object... args) {
        List<LoaiHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    LoaiHang model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return list;
    }

    private LoaiHang readFromResultSet(ResultSet rs) throws SQLException {
        LoaiHang model = new LoaiHang();
        model.setMaLoaiHang(rs.getString("MaLH"));
        model.setTenLoaiHang(rs.getString("TenLH"));
        return model;
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
        public String check_lh(String malh) {
        String sql = "{call check_lh(?)}";
        String cols = "malh";
        return this.getcol(sql, cols, malh);
    }
}
