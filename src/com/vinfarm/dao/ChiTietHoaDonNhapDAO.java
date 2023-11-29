package com.vinfarm.dao;

import com.vinfarm.entity.ChiTietHoaDonNhap;
import com.vinfarm.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Z18KAI
 */
public class ChiTietHoaDonNhapDAO {
    public void insert(ChiTietHoaDonNhap model) {
        String sql = "INSERT INTO CTHDNhap (SoPhieu, MaSP, SoLuong, GiaNhap) VALUES (?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getSoPhieu(),
                model.getMaSanPham(),
                model.getSoLuong(),
                model.getGiaNhap());
    }
    /// update 1.0
       public void insert(String SoPhieu, String maSanPham, int SoLuong, int GiaNhap) {
        String sql = "INSERT INTO CTHDNhap (SoPhieu, MaSP, SoLuong, GiaNhap) VALUES (?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                SoPhieu,
                maSanPham,
                SoLuong,
                GiaNhap);
    }
 public void update_soLuong(int soLuong, int giaNhap, String soPhieu, String maSanPham) {
        String sql = "UPDATE CTHDNhap SET SoLuong=?, GiaNhap=? WHERE SoPhieu=? and MaSP = ?";
        JdbcHelper.executeUpdate(sql,
                soLuong,
                giaNhap,
                soPhieu, 
                maSanPham);
    }
    public void update(ChiTietHoaDonNhap model) {
        String sql = "UPDATE CTHDNhap SET MaSP=?, SoLuong=?, GiaNhap=? WHERE SoPhieu=?";
        JdbcHelper.executeUpdate(sql,
                model.getMaSanPham(),
                model.getSoLuong(),
                model.getGiaNhap(),
                model.getSoPhieu());
    }

    public void delete(String MaKH) {
        String sql = "DELETE FROM CTHDNhap WHERE SoPhieu=?";
        JdbcHelper.executeUpdate(sql, MaKH);
    }
    
    public void delete(String sophieu, String maSanPham) {
        String sql = "DELETE FROM CTHDNhap WHERE SoPhieu = ? and MaSP = ?";
        JdbcHelper.executeUpdate(sql, sophieu, maSanPham);
    }

    public List<ChiTietHoaDonNhap> select() {
        String sql = "SELECT * FROM CTHDNhap";
        return select(sql);
    }

   public ChiTietHoaDonNhap findById(String sophieu, String maSanPham) {
        String sql = "SELECT * FROM CTHDNhap WHERE SoPhieu = ? and MaSP = ?";
        List<ChiTietHoaDonNhap> list = select(sql, sophieu, maSanPham);
        return list.size() > 0 ? list.get(0) : null;
    }

    
    public ChiTietHoaDonNhap findById(String makh) {
        String sql = "SELECT * FROM CTHDNhap WHERE SoPhieu=?";
        List<ChiTietHoaDonNhap> list = select(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public List<ChiTietHoaDonNhap> select_ID(String MaKH) {
        String sql = "SELECT * FROM CTHDNhap WHERE SoPhieu=?";
        return select(sql, MaKH);
    }

    public ChiTietHoaDonNhap findByName(String namekh) {
        String sql = "SELECT * FROM CTHDNhap WHERE MaSP like N'%'+?+'%'";
        List<ChiTietHoaDonNhap> list = select(sql, namekh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<ChiTietHoaDonNhap> select(String sql, Object... args) {
        List<ChiTietHoaDonNhap> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    ChiTietHoaDonNhap model = readFromResultSet(rs);
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
public void update_soLuong(int soLuong, int giaNhap, String soPhieu) {
        String sql = "UPDATE CTHDNhap SET SoLuong=?, GiaNhap=? WHERE SoPhieu=?";
        JdbcHelper.executeUpdate(sql,
                soLuong,
                giaNhap,
                soPhieu);
    }
    private ChiTietHoaDonNhap readFromResultSet(ResultSet rs) throws SQLException {
        ChiTietHoaDonNhap model = new ChiTietHoaDonNhap();
        model.setSoPhieu(rs.getString("SoPhieu"));
        model.setMaSanPham(rs.getString("MaSP"));
        model.setSoLuong(rs.getInt("SoLuong"));
        model.setGiaNhap(rs.getInt("GiaNhap"));
        return model;
    }
}
