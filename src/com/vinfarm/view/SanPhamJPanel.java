/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.vinfarm.view;

import com.vinafarm.validate.Validate;
import com.vinfarm.dao.LoaiHangDAO;
import com.vinfarm.dao.NhaCungCapDAO;
import com.vinfarm.dao.SanPhamDAO;
import com.vinfarm.entity.LoaiHang;
import com.vinfarm.entity.NhaCungCap;
import com.vinfarm.entity.SanPham;
import com.vinfarm.main.Main;
import com.vinfarm.utils.Notifier;
import com.vinfarm.utils.ShareHelper;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import static java.awt.SystemColor.scrollbar;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hau
 */
public class SanPhamJPanel extends javax.swing.JPanel {

    /**
     * Creates new form SanPhamJPanel
     */
    class NonEditableTableModel extends DefaultTableModel {

        NonEditableTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Ngăn không cho phép sửa trực tiếp
        }
    }
    
    public SanPhamJPanel() {
        initComponents();
        init();
    }

    SanPham sp = new SanPham();
    SanPhamDAO sanPhamDAO = new SanPhamDAO();
    LoaiHangDAO loaiHangDAO = new LoaiHangDAO();
    NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
    int row = 0;
    String imageName = null;
    Locale local = new Locale("vi", "VN");
    NumberFormat format = NumberFormat.getCurrencyInstance(local);
    DefaultTableModel tblModel;
    
    void init() {
        String[] columnNames = {"STT", "MÃ LOẠI", "MÃ NCC", "MÃ SP", "TÊN SP", "GIÁ BÁN","ĐƠN VỊ", "SỐ LG"};
        tblModel = new SanPhamJPanel.NonEditableTableModel(new Object[0][0], columnNames);
        tblSanPham.setModel(tblModel);

        fillTable();
        fillComboBox_LoaiHang();
        fillComboBox_NCC();
        setStatus(true);
        fillComboBox_donvi();
        txtMaSanPham.setEnabled(true);
    }
    
    void fillComboBox_LoaiHang() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiHang.getModel();
        model.removeAllElements();
        try {
            List<LoaiHang> list = loaiHangDAO.select();
            for (LoaiHang loaiHang : list) {
                model.addElement(loaiHang.getMaLoaiHang() + " - " + loaiHang.getTenLoaiHang());
            }
        } catch (Exception e) {
            Notifier.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void fillComboBox_NCC() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNhaCungCap.getModel();
        model.removeAllElements();
        try {
            List<NhaCungCap> list = nhaCungCapDAO.select();
            for (NhaCungCap NCC : list) {
                model.addElement(NCC.getMaNhaCungCap() + " - " + NCC.getTenNhaCungCap());
            }
        } catch (Exception e) {
            Notifier.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void fillComboBox_donvi() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboDonVi.getModel();
        model.removeAllElements();
        String[] header = {"KG", "G", "Hộp"};
        for (String string : header) {
            model.addElement(string);
        }
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        int stt = 1;
        try {
            List<SanPham> list = sanPhamDAO.select();
            for (SanPham sanpham : list) {
                Object[] row = {
                    stt++,
                    sanpham.getMaLoaiHang(),
                    sanpham.getMaNhaCungCap(),
                    sanpham.getMaSanPham(),
                    sanpham.getTenSanPham(),
                    format.format(sanpham.getGiaBan()),
                    sanpham.getDonVi(),
                    sanpham.getSoLuong(),
                    sanpham.getImage()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notifier.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    SanPham getModel() {
        SanPham model = new SanPham();
        model.setMaLoaiHang(String.valueOf(cboLoaiHang.getSelectedItem()).substring(0, 5));
        model.setMaNhaCungCap(String.valueOf(cboNhaCungCap.getSelectedItem()).substring(0, 6));
        model.setMaSanPham(txtMaSanPham.getText());
        model.setTenSanPham(txtTenSanPham.getText());

        model.setGiaBan(Integer.valueOf(txtGiaBan.getText()));
        model.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        model.setDonVi(cboDonVi.getSelectedItem() + "");
        lbAnh.getToolTipText();
        imageName = lbAnh.getToolTipText();
        model.setHinhSanPham(imageName);
        return model;
    }

    public ImageIcon Resize(ImageIcon icon) {
        Image image = icon.getImage();
        ImageIcon icon1 = new ImageIcon(image.getScaledInstance(lbAnh.getWidth(), lbAnh.getHeight(), image.SCALE_SMOOTH));
        return icon1;
    }

    public void ResizeImage(String imageName) {
        ImageIcon icon = new ImageIcon("src\\Image\\" + imageName);
        Image image = icon.getImage();
        ImageIcon icon1 = new ImageIcon(image.getScaledInstance(lbAnh.getWidth(), lbAnh.getHeight(), image.SCALE_SMOOTH));
        lbAnh.setIcon(icon1);
    }
    
    void setModel(SanPham model) {
        LoaiHang loaiHang = loaiHangDAO.findById(model.getMaLoaiHang());
        NhaCungCap nhaCungCap = nhaCungCapDAO.findById(model.getMaNhaCungCap());

        cboLoaiHang.getModel().setSelectedItem(loaiHang.getMaLoaiHang() + " - " + loaiHang.getTenLoaiHang());
        cboNhaCungCap.getModel().setSelectedItem(nhaCungCap.getMaNhaCungCap() + " - " + nhaCungCap.getTenNhaCungCap());
        txtMaSanPham.setText(model.getMaSanPham());
        txtTenSanPham.setText(model.getTenSanPham());
        txtGiaBan.setText(String.valueOf((int) model.getGiaBan()));
        txtSoLuong.setText(String.valueOf(model.getSoLuong()));
        cboDonVi.setSelectedItem(model.getDonVi());
        if (model.getHinhSanPham() != null) {
            lbAnh.setToolTipText(model.getHinhSanPham());
            lbAnh.setIcon(Resize(ShareHelper.readLogo(model.getHinhSanPham())));
        }
    }

    void setStatus(boolean insertable) {
        btnLamMoi.setEnabled(!insertable);
        //btnThem.setEnabled(insertable);
        if (row >= tblSanPham.getRowCount()) {
            row = tblSanPham.getRowCount() -1 ;
        }
        if (sp != null) {
            txtMaSanPham.setEnabled(insertable);
        } else {
            txtMaSanPham.setEnabled(!insertable);
        }
        String masp = txtMaSanPham.getText();
        /*if (sanPhamDAO.check_sl(masp).equals("0")) {
            btnXoa.setEnabled(false);
        } else {
            btnXoa.setEnabled(true);
        }*/
        btnSua.setEnabled(!insertable);
        boolean first = this.row > 0;
        boolean last = this.row < tblSanPham.getRowCount() - 1;
        btnPrev.setEnabled(!insertable && first);
        btnFirst.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
    }

    void edit() {
        try {
            String makh = (String) tblSanPham.getValueAt(row, 3);
            SanPham model = sanPhamDAO.findById(makh);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            Notifier.alert(this,"Lỗi truy vấn dữ liệu!");
        }
    }

    void clear() {
//        this.setModel(new SanPham());
        txtGiaBan.setText("");
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");
        txtSoLuong.setText("");

        this.setStatus(true);

        cboLoaiHang.requestFocus();
        txtGiaBan.setBackground(Color.white);
        txtMaSanPham.setBackground(Color.white);
        txtTenSanPham.setBackground(Color.white);
        txtSoLuong.setBackground(Color.white);
        fillTable();
    }

    void insert() {
        SanPham model = getModel();
        try {
            sanPhamDAO.insert(model);
            this.fillTable();
            Notifier.alert(this,"Thêm mới thành công!");
        } catch (HeadlessException e) {
            Notifier.alert(this,"Thêm mới thất bại!");
            e.printStackTrace();
        }

    }

    void update() {

        SanPham model = getModel();
        try {
            sanPhamDAO.update(model);
            this.fillTable();
            Notifier.alert(this,"Cập nhật thành công!");
        } catch (Exception e) {
            Notifier.alert(this,"Cập nhật thất bại!");
            e.printStackTrace();
        }
    }

    void delete() {
        if (Notifier.confirm(this, "Bạn thực sự muốn xóa sản phẩm này?", "Không thể khôi phục sau khi xoá")) {
            String makh = txtMaSanPham.getText();
            try {
                sanPhamDAO.delete(makh);
                this.fillTable();
                this.clear();
                Notifier.alert(this,"Xóa thành công!");
            } catch (Exception e) {
                e.printStackTrace();
                Notifier.alert(this,"Xóa thất bại!");
            }
        }
    }
    
        void Find(){
        String f = txtTim.getText();
        try{
            if(!f.equals("")){
                DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
                List<SanPham> list = sanPhamDAO.selectbyName(f);
                if(!list.isEmpty()){
                    model.setRowCount(0);
                    int stt = 1;
                    for (SanPham sanpham : list) {
                        Object[] info = {
                            stt++,
                            sanpham.getMaLoaiHang(),
                            sanpham.getMaNhaCungCap(),
                            sanpham.getMaSanPham(),
                            sanpham.getTenSanPham(),
                            format.format(sanpham.getGiaBan()),
                            sanpham.getDonVi(),
                            sanpham.getSoLuong(),
                            sanpham.getImage()
                        };
                        model.addRow(info);
                    }
                }else{
                    Notifier.alert(this, "Không tìm thấy sp này");
                }
            }else{
                Notifier.alert(this, "Nhập tên sp cần tìm");
            }
        }catch (Exception ex) {
            Notifier.alert(this, "Lỗi truy vấn dữ liệu");
            ex.printStackTrace();
        }
    }
        
    void first() {
        this.row = 0;
        this.edit();
        tblSanPham.setRowSelectionInterval(row, row);
    }

    void prev() {
        if (this.row > 0) {
            this.row--;
            this.edit();
            tblSanPham.setRowSelectionInterval(row, row);
        }
    }

    void next() {
        if (this.row < tblSanPham.getRowCount() - 1) {
            this.row++;
            this.edit();
            tblSanPham.setRowSelectionInterval(row, row);
        }
    }

    void last() {
        this.row = tblSanPham.getRowCount() - 1;
        this.edit();
        tblSanPham.setRowSelectionInterval(row, row);
    }
    
    boolean  ValiadateForm(){
        StringBuilder str = new StringBuilder();
        if (Validate.isTrong(txtMaSanPham, str, "")) {
            Notifier.alert(this, "Mã sản phẩm không được để trống");
            return false;
        }
        if (!Validate.isMa(txtMaSanPham, str, "")) {
            Notifier.alert(this, "Mã sản phẩm không đúng định dạng");
            return false;

        }
        if (Validate.isTrong(txtTenSanPham, str, "")) {
            Notifier.alert(this, "Tên sản phẩm không được để trống");
            return false;
        }
        String valiname ="/^[\\p{L} \\.'\\-]+$/";
        if (txtTenSanPham.getText().matches(valiname)) {             
            Notifier.alert(this, "Tên sản phẩm không đúng định dạng");
            txtTenSanPham.setBackground(new Color(250, 250, 210));
            return false;
        }
        if (Validate.isTrong(txtGiaBan, str, "")) {
            Notifier.alert(this, "Giá bán không được để trống");
            return false;
        }
        if (Integer.parseInt(txtGiaBan.getText()) < 0) {
            Notifier.alert(this, "Giá bán phải lớn hơn 0");
            return false;
        }
        if (Validate.isTrong(txtSoLuong, str, "")) {
            Notifier.alert(this, "Số lượng không được để trống");
            return false;
        }
        if (Integer.parseInt(txtSoLuong.getText()) < 0) {
            Notifier.alert(this, "Số lượng chưa đúng định dạng");
            return false;
        }
        return true;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbAnh = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtMaSanPham = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        cboLoaiHang = new javax.swing.JComboBox<>();
        cboNhaCungCap = new javax.swing.JComboBox<>();
        cboDonVi = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(0, 0, 255));
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("SẢN PHẨM");

        jPanel7.setBackground(new java.awt.Color(204, 255, 255));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(204, 255, 255));

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel7.setText("Tên sản phẩm");

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel8.setText("Mã sản phẩm");

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel9.setText("Giá bán ");

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel10.setText("Số lượng");

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");

        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("*");

        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("*");

        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("*");

        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("*");

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel6.setText("Mã loại hàng");

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel11.setText("Mã nhà cung cấp");

        jLabel16.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel16.setText("Đơn vị");

        lbAnh.setBackground(new java.awt.Color(255, 255, 255));
        lbAnh.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        lbAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAnh.setText("Ảnh");
        lbAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lbAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAnhMouseClicked(evt);
            }
        });

        txtTenSanPham.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtGiaBan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtSoLuong.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtMaSanPham.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinfarm/icon/previous.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinfarm/icon/first-button.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinfarm/icon/forward-button.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinfarm/icon/next.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        cboLoaiHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboNhaCungCap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboDonVi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Tìm kiếm:");

        txtTim.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cboNhaCungCap, 0, 170, Short.MAX_VALUE)
                                    .addComponent(cboLoaiHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 206, Short.MAX_VALUE))
                                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtMaSanPham))
                                .addGap(122, 122, 122)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(57, 57, 57)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16)))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLamMoi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58)
                .addComponent(lbAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel16))
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(cboDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel6))
                                        .addGap(1, 1, 1)))
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(cboLoaiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(13, 13, 13)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cboNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel12))
                                        .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLast)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnNext)
                                .addComponent(btnFirst)
                                .addComponent(btnPrev))
                            .addComponent(btnLamMoi)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnSua)
                                .addComponent(btnThem)
                                .addComponent(btnXoa))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lbAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                .addGap(390, 390, 390))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(318, 318, 318))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTieuDe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTim)
                .addContainerGap(533, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        tblSanPham.setRowSelectionAllowed(true);
        if (evt.getClickCount() == 1) {
            this.row = tblSanPham.getSelectedRow();
            if (this.row >= 0) {
                this.edit();
            }
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void lbAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAnhMouseClicked
        // TODO add your handling code here:
        try {
            JFileChooser file = new JFileChooser("src\\Image\\");
                int kq = file.showOpenDialog(file);
                if (kq == JFileChooser.APPROVE_OPTION) {
                    imageName = file.getSelectedFile().getName();
                    System.out.println(imageName);
                    lbAnh.setText("");
                    ResizeImage(imageName);
                } else {
                    Notifier.alert(this,"bạn chưa chọn ảnh");
                }

            } catch (Exception a) {
                a.printStackTrace();
            }
    }//GEN-LAST:event_lbAnhMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if(ValiadateForm()){
            update();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if(ValiadateForm()){
            insert();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        Find();
    }//GEN-LAST:event_btnTimActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboDonVi;
    private javax.swing.JComboBox<String> cboLoaiHang;
    private javax.swing.JComboBox<String> cboNhaCungCap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbAnh;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
