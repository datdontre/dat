package com.vinfarm.dao;

import com.vinfarm.entity.SanPham;
import com.vinfarm.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Z18KAI
 */
public class SanPhamDAO {

    public void insert(SanPham model) {
        String sql = "INSERT INTO SanPham (MaSP, MaLH, TenSP, GiaBan, HinhSP, MaNCC, SOLG, DonVi) VALUES (UPPER(?), ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaSanPham(),
                model.getMaLoaiHang(),
                model.getTenSanPham(),
                model.getGiaBan(),
                model.getHinhSanPham(),
                model.getMaNhaCungCap(),
                model.getSoLuong(),
                model.getDonVi());
                
    }

    public void update(SanPham model) {
        String sql = "UPDATE SanPham SET MaLH=?, TenSP=?, GiaBan=?, HinhSP=?, MaNCC=?, SoLg=?, DonVi = ? WHERE MaSP=?";
        JdbcHelper.executeUpdate(sql,
                model.getMaLoaiHang(),
                model.getTenSanPham(),
                model.getGiaBan(),
                model.getHinhSanPham(),
                model.getMaNhaCungCap(),
                model.getSoLuong(),
                model.getDonVi(),
                model.getMaSanPham());
    }

    public void delete(String MaKH) {
        String sql = "DELETE FROM SanPham WHERE MaSP=?";
        JdbcHelper.executeUpdate(sql, MaKH);
    }

    public List<SanPham> select_cthdxuat(String soPhieu) {
        String sql = "select * from sanpham where MaSP not in (select MaSP from CTHDXuat where SoPhieu = ?)";
        return select(sql, soPhieu);
    }
    
    public List<SanPham> select_cthdnhap(String soPhieu) {
        String sql = "select * from sanpham where MaSP not in (select MaSP from CTHDNhap where SoPhieu = ?)";
        return select(sql, soPhieu);
    }
    
    public List<SanPham> select() {
        String sql = "SELECT * FROM SanPham";
        return select(sql);
    }
    
    public List<SanPham> selectbyName(String namesp) {
        String sql = "SELECT * FROM SanPham WHERE TenSP like N'%'+?+'%'";
        return select(sql, namesp);
    }
    
    public List<SanPham> select_notIn_nhap(String soPhieu) {
        String sql = "select * from SanPham where MaSP not in (select MaSP from CTHDNhap where SoPhieu = ?)";
        return select(sql, soPhieu);
    }
    public List<SanPham> select_notIn_xuat(String soPhieu) {
        String sql = "select * from SanPham where MaSP not in (select MaSP from CTHDXuat where SoPhieu = ?)";
        return select(sql, soPhieu);
    }
    public List<SanPham> select_In_Xuat(String soPhieu) {
        String sql = "select * from SanPham where MaSP in (select MaSP from CTHDXuat where SoPhieu = ?)";
        return select(sql, soPhieu);
    }
    public List<SanPham> select_In_Nhap(String soPhieu) {
        String sql = "select * from SanPham where MaSP in (select MaSP from CTHDNhap where SoPhieu = ?)";
        return select(sql, soPhieu);
    }

    public SanPham findById(String makh) {
        String sql = "SELECT * FROM SanPham WHERE MaSP=?";
        List<SanPham> list = select(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }

    public SanPham findByName(String namekh) {
        String sql = "SELECT * FROM SanPham WHERE TenSP like N'%'+?+'%'";
        List<SanPham> list = select(sql, namekh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<SanPham> select(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    SanPham model = readFromResultSet(rs);
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
public void update_soLuong(int soLuong, String maSanPham) {
        String sql = "UPDATE SanPham SET SoLuong=? WHERE MaSP=?";
        JdbcHelper.executeUpdate(sql,
                soLuong,
                maSanPham);
    }
    private SanPham readFromResultSet(ResultSet rs) throws SQLException {
        SanPham model = new SanPham();
        model.setMaSanPham(rs.getString("MaSP"));
        model.setMaLoaiHang(rs.getString("MaLH"));
        model.setTenSanPham(rs.getString("TenSP"));
        model.setGiaBan(rs.getFloat("GiaBan"));
        model.setHinhSanPham(rs.getString("HinhSP"));
        model.setMaNhaCungCap(rs.getString("MaNCC"));
        model.setSoLuong(rs.getInt("SoLg"));
        model.setDonVi(rs.getString("DonVi"));
        model.setGiamGia(rs.getFloat("GiamGia"));
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
        public String check_sl(String masp) {
        String sql = "{call check_sp(?)}";
        String cols = "solg";
        return this.getcol(sql, cols, masp);
    }
}
