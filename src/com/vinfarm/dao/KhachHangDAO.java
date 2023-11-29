package com.vinfarm.dao;

import com.vinfarm.entity.KhachHang;
import com.vinfarm.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Z18KAI
 */
public class KhachHangDAO {
    public void insert(KhachHang model) {
        String sql = "INSERT INTO KhachHang (MaKH, HoTen, SDT, Email) VALUES (UPPER(?), ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaKhachHang(),
                model.getHoTenKhachHang(),
                model.getSdtKhachHang(),
                model.getEmailKhachHang());
    }

    public void update(KhachHang model) {
        String sql = "UPDATE KhachHang SET HoTen=?, SDT=?, Email=? WHERE MaKH=?";
        JdbcHelper.executeUpdate(sql,
                model.getHoTenKhachHang(),
                model.getSdtKhachHang(),
                model.getEmailKhachHang(),
                model.getMaKhachHang());
    }

    public void delete(String MaKH) {
        String sql = "DELETE FROM KhachHang WHERE MaKH=?";
        JdbcHelper.executeUpdate(sql, MaKH);
    }

    public List<KhachHang> select() {
        String sql = "SELECT * FROM KhachHang";
        return select(sql);
    }

    public KhachHang findById(String makh) {
        String sql = "SELECT * FROM KhachHang WHERE MaKH=?";
        List<KhachHang> list = select(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }

    public KhachHang findByName(String namekh) {
        String sql = "SELECT * FROM KhachHang WHERE HoTen like N'%'+?+'%'";
        List<KhachHang> list = select(sql, namekh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<KhachHang> select(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    KhachHang model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private KhachHang readFromResultSet(ResultSet rs) throws SQLException {
        KhachHang model = new KhachHang();
        model.setMaKhachHang(rs.getString("MaKH"));
        model.setHoTenKhachHang(rs.getString("HoTen"));
        model.setSdtKhachHang(rs.getString("SDT"));
        model.setEmailKhachHang(rs.getString("Email"));
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
        public String check_KH(String makh) {
        String sql = "{call check_kh(?)}";
        String cols = "makh";
        return this.getcol(sql, cols, makh);
    }
}
