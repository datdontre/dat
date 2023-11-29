package com.vinfarm.dao;

import com.vinfarm.entity.ChiTietHoaDonXuat;
import com.vinfarm.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Z18KAI
 */
public class ChiTietHoaDonXuatDAO {

    public void insert(ChiTietHoaDonXuat model) {
        String sql = "INSERT INTO CTHDXuat (SoPhieu, MaSP, SoLuong, GiamGia) VALUES (?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getSoPhieu(),
                model.getMaSanPham(),
                model.getSoLuong(),
                model.getGiamGia());
    }

    public void update(ChiTietHoaDonXuat model) {
        String sql = "UPDATE CTHDXuat SET SoLuong=?, GiamGia=? WHERE MaSP=?";
        JdbcHelper.executeUpdate(sql,
                model.getSoLuong(),
                model.getGiamGia(),
                model.getMaSanPham());
    }
   public void update_soLuong(int soLuong, float giamGia, String soPhieu, String maSanPham) {
        String sql = "UPDATE CTHDXuat SET SoLuong=?, GiamGia=? WHERE SoPhieu=? and MaSP = ?";
        JdbcHelper.executeUpdate(sql,
                soLuong,
                giamGia,
                soPhieu, 
                maSanPham);
    }

    public void delete(String sophieu, String maSanPham) {
        String sql = "DELETE FROM CTHDXuat WHERE SoPhieu = ? and MaSP = ?";
        JdbcHelper.executeUpdate(sql, sophieu, maSanPham);
    }
    
    public void delete_All(String MaKH) {
        String sql = "delete from CTHDXuat where SoPhieu = ?";
        JdbcHelper.executeUpdate(sql, MaKH);
    }

    public List<ChiTietHoaDonXuat> select() {
        String sql = "SELECT * FROM CTHDXuat";
        return select(sql);
    }

    public List<ChiTietHoaDonXuat> select_ID(String MaKH) {
        String sql = "SELECT * FROM CTHDXuat WHERE SoPhieu=?";
        return select(sql, MaKH);
    }

    public ChiTietHoaDonXuat findById(String sophieu, String maSanPham) {
        String sql = "SELECT * FROM CTHDXuat WHERE SoPhieu = ? and MaSP = ?";
        List<ChiTietHoaDonXuat> list = select(sql, sophieu, maSanPham);
        return list.size() > 0 ? list.get(0) : null;
    }

    public ChiTietHoaDonXuat findByName(String namekh) {
        String sql = "SELECT * FROM CTHDXuat WHERE MaSP like N'%'+?+'%'";
        List<ChiTietHoaDonXuat> list = select(sql, namekh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<ChiTietHoaDonXuat> select(String sql, Object... args) {
        List<ChiTietHoaDonXuat> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    ChiTietHoaDonXuat model = readFromResultSet(rs);
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

    private ChiTietHoaDonXuat readFromResultSet(ResultSet rs) throws SQLException {
        ChiTietHoaDonXuat model = new ChiTietHoaDonXuat();
        model.setSoPhieu(rs.getInt("SoPhieu")+"");
        model.setMaSanPham(rs.getString("MaSP"));
        model.setSoLuong(rs.getInt("SoLuong"));
        model.setGiamGia(rs.getInt("GiamGia"));
        return model;
    }
    ThongKeDao tkdao = new ThongKeDao();

    
    public String getcol(String sql, Object... args){
          try {
             String a = null;
             JdbcHelper.executeUpdate(sql, args);
             return a;
          } catch (Exception e) {
              e.printStackTrace();
          }
          return null;
      }
    public String update_Gia(String soPhieu){
        String sql = "{call update_gia(?)}";
        return this.getcol(sql, soPhieu);
    }
}
