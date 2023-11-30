package com.vinfarm.dao;

import com.vinfarm.entity.NhaCungCap;
import com.vinfarm.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Z18KAI
 */
public class NhaCungCapDAO {
    public void insert(NhaCungCap model) {
        String sql = "INSERT INTO NhaCungCap (MaNCC, TenNCC, SDT, Email, DiaChi) VALUES (UPPER(?), ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaNhaCungCap(),
                model.getTenNhaCungCap(),
                model.getSdtNhaCungCap(),
                model.getEmailNhaCungCap(),
                model.getDiaChiNhaCungCap());
    }

    public void update(NhaCungCap model) {
        String sql = "UPDATE NhaCungCap SET TenNCC=?, SDT=?, Email=?, DiaChi=? WHERE MaNCC=?";
        JdbcHelper.executeUpdate(sql,
                model.getTenNhaCungCap(),
                model.getSdtNhaCungCap(),
                model.getEmailNhaCungCap(),
                model.getDiaChiNhaCungCap(),
                model.getMaNhaCungCap());
    }

    public void delete(String MaKH) {
        String sql = "DELETE FROM NhaCungCap WHERE MaNCC=?";
        JdbcHelper.executeUpdate(sql, MaKH);
    }

    public List<NhaCungCap> select() {
        String sql = "SELECT * FROM NhaCungCap";
        return select(sql);
    }
    
    public List<NhaCungCap> selectByName(String namekh) {
        String sql = "SELECT * FROM NhaCungCap WHERE TenNCC like N'%'+?+'%'";
        return select(sql, namekh);
    }

    public NhaCungCap findById(String makh) {
        String sql = "SELECT * FROM NhaCungCap WHERE MaNCC=?";
        List<NhaCungCap> list = select(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }

    public NhaCungCap findByName(String namekh) {
        String sql = "SELECT * FROM NhaCungCap WHERE TenNCC like N'%'+?+'%'";
        List<NhaCungCap> list = select(sql, namekh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<NhaCungCap> select(String sql, Object... args) {
        List<NhaCungCap> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    NhaCungCap model = readFromResultSet(rs);
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

    private NhaCungCap readFromResultSet(ResultSet rs) throws SQLException {
        NhaCungCap model = new NhaCungCap();
        model.setMaNhaCungCap(rs.getString("MaNCC"));
        model.setTenNhaCungCap(rs.getString("TenNCC"));
        model.setSdtNhaCungCap(rs.getString("SDT"));
        model.setEmailNhaCungCap(rs.getString("Email"));
        model.setDiaChiNhaCungCap(rs.getString("DiaChi"));
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
        public String check_lh(String mancc) {
        String sql = "{call check_ncc(?)}";
        String cols = "sllh";
        return this.getcol(sql, cols, mancc);
    }
}
