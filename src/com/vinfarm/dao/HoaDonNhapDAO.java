package com.vinfarm.dao;

import com.vinfarm.entity.HoaDonNhap;
import com.vinfarm.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Z18KAI
 */
public class HoaDonNhapDAO {

    public void insert(HoaDonNhap model) {
        String sql = "INSERT INTO HDNhap ( NgayNhap, MaNV) VALUES ( ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getNgayNhap(),
                model.getMaNhanVien());
    }

    public void update(HoaDonNhap model) {
        String sql = "UPDATE HDNhap SET NgayNhap=?, MaNV=? WHERE SoPhieu=?";
        JdbcHelper.executeUpdate(sql,
                model.getNgayNhap(),
                model.getMaNhanVien(),
                model.getSoPhieu());
    }

    public void delete(String MaKH) {
        String sql = "DELETE FROM HDNhap WHERE SoPhieu=?";
        JdbcHelper.executeUpdate(sql, MaKH);
    }

    public List<HoaDonNhap> select() {
        String sql = "SELECT * FROM HDNhap ORDER BY NgayNhap desc ";
        return select(sql);
    }

    public List<HoaDonNhap> select_ngayThangNam(String date1 , String date2) {
        String sql = "SELECT * FROM HDNhap WHERE NgayNhap BETWEEN ? AND ? ORDER BY NgayNhap desc" ;
        return select(sql,date1,date2);
    }

    public HoaDonNhap findById(String makh) {
        String sql = "SELECT * FROM HDNhap WHERE SoPhieu=?";
        List<HoaDonNhap> list = select(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }

    public String getSoPhieu() {
        String sql = "select max(sophieu) as 'soPhieu' from HDNhap";
        String soPhieu = null;
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql);
                while (rs.next()) {
                    soPhieu = rs.getString("soPhieu");
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return soPhieu;
    }

    public int getSoLuongSanPham(String SoPhieu) {
        String sql = "SELECT * FROM dbo.CTHDNhap WHERE SoPhieu = ?";
        int SoLuong = 0;
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, SoPhieu);
                while (rs.next()) {
                    SoLuong = rs.getInt("soPhieu");
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return SoLuong;
    }

    public HoaDonNhap findByName(String namekh) {
        String sql = "SELECT * FROM HDNhap WHERE MaNV like N'%'+?+'%'";
        List<HoaDonNhap> list = select(sql, namekh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<HoaDonNhap> select(String sql, Object... args) {
        List<HoaDonNhap> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    HoaDonNhap model = readFromResultSet(rs);
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

    public int getTongSoLuong(String soPhieu) {
        String sql = "select count (*) as 'TongSoLuong'  from CTHDNhap where SoPhieu = ? group by SoPhieu";
        int SoLuong = 0;
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, soPhieu);
                while (rs.next()) {
                    SoLuong = rs.getInt("TongSoLuong");
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return SoLuong;
    }

    private HoaDonNhap readFromResultSet(ResultSet rs) throws SQLException {
        HoaDonNhap model = new HoaDonNhap();
        model.setSoPhieu(rs.getString("SoPhieu"));
        model.setNgayNhap(rs.getString("NgayNhap"));
        model.setMaNhanVien(rs.getString("MaNV"));
        return model;
    }
}
