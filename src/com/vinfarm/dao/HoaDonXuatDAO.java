package com.vinfarm.dao;

import com.vinfarm.entity.HoaDonXuat;
import com.vinfarm.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Z18KAI
 */
public class HoaDonXuatDAO {
    public void update_ThanhTien(float ThanhTien, String Sophieu) {
        String sql = "UPDATE HDXuat set ThanhTien=? WHERE SoPhieu=?";
        JdbcHelper.executeUpdate(sql,
                ThanhTien,
                Sophieu);
    }
    public void insert(HoaDonXuat model) {
        String sql = "INSERT INTO HDXuat ( MaNV, MaKH, ThanhTien, NgayXuat) VALUES ( ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                //                model.getSoPhieu(),
                model.getMaNhanVien(),
                model.getMaKhachHang(),
                model.getThanhTien(),
                model.getNgayXuat());
    }

    public void update(HoaDonXuat model) {
        String sql = "UPDATE HDXuat SET MaNV=?, MaKH=?, ThanhTien=?, NgayXuat=? WHERE SoPhieu=?";
        JdbcHelper.executeUpdate(sql,
                model.getMaNhanVien(),
                model.getMaKhachHang(),
                model.getThanhTien(),
                model.getNgayXuat(),
                model.getSoPhieu());
    }

    public void update_ThanhTien(HoaDonXuat model) {
        String sql = "UPDATE HDXuat set ThanhTien=? WHERE SoPhieu=?";
        JdbcHelper.executeUpdate(sql,
                model.getThanhTien(),
                model.getSoPhieu());
    }

    public void delete(String MaKH) {
        String sql = "DELETE FROM HDXuat WHERE SoPhieu=?";
        JdbcHelper.executeUpdate(sql, MaKH);
    }

    public List<HoaDonXuat> select() {
        String sql = "SELECT * FROM HDXuat ORDER BY NgayXuat desc ";
        return select(sql);
    }
    public List<HoaDonXuat> select_ngayxuat() {
        String sql = "SELECT * FROM HDXuat order by NgayXuat desc";
        return select(sql);
    }
  public List<HoaDonXuat> select_From_To(String date1 , String date2) {
        String sql = "SELECT * FROM dbo.HDXuat WHERE NgayXuat BETWEEN  ? AND  ? ORDER BY NgayXuat DESC";
        return select(sql,date1,date2);
    }
    public HoaDonXuat findById(String makh) {
        String sql = "SELECT * FROM HDXuat WHERE SoPhieu=?";
        List<HoaDonXuat> list = select(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }

    public String getSoPhieu() {
        String sql = "select max(sophieu) as 'soPhieu' from HDXuat";
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
        String sql = "SELECT * FROM dbo.HDXuat WHERE SoPhieu = ?";
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

    public HoaDonXuat findByName(String namekh) {
        String sql = "SELECT * FROM HDXuat WHERE MaKH like N'%'+?+'%'";
        List<HoaDonXuat> list = select(sql, namekh);
        return list.size() > 0 ? list.get(0) : null;
    }
public int getTongSoLuong(String soPhieu) {
        String sql = "select count (*) as 'TongSoLuong'  from CTHDXuat where SoPhieu = ? group by SoPhieu";
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
    private List<HoaDonXuat> select(String sql, Object... args) {
        List<HoaDonXuat> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    HoaDonXuat model = readFromResultSet(rs);
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

    private HoaDonXuat readFromResultSet(ResultSet rs) throws SQLException {
        HoaDonXuat model = new HoaDonXuat();
        model.setSoPhieu(rs.getInt("SoPhieu") + "");
        model.setMaNhanVien(rs.getString("MaNV"));
        model.setMaKhachHang(rs.getString("MaKH"));
        model.setThanhTien(rs.getFloat("ThanhTien"));
        model.setNgayXuat(rs.getString("NgayXuat"));
        return model;
    }
}
