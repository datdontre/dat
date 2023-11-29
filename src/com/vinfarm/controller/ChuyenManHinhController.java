/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vinfarm.controller;

import com.vinfarm.bean.DanhMucBean;
import com.vinfarm.view.DoiMatKhauJPanel;
import com.vinfarm.view.HoaDonNhapJPanel;
import com.vinfarm.view.HoaDonXuatJPanel;
import com.vinfarm.view.KhachHangJPanel;
import com.vinfarm.view.LoaiHangJPanel;
import com.vinfarm.view.NhaCungCapJPanel;
import com.vinfarm.view.NhanVienJPanel;
import com.vinfarm.view.SanPhamJPanel;
import com.vinfarm.view.ThongKeJPanel;
import com.vinfarm.view.TrangChuJPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author hau
 */
public class ChuyenManHinhController {

    private JPanel root;
    private String menuItem = "";
    private List<DanhMucBean> listItem = null;

    public ChuyenManHinhController(JPanel pnlRoot) {
        this.root = pnlRoot;
    }

    public void setView(JPanel pnlItem, JLabel lblItem) {
        menuItem = "TrangChu";
        pnlItem.setBackground(new Color(96, 100, 191));
        lblItem.setBackground(new Color(96, 100, 191));

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChuJPanel());
        root.validate();
        root.repaint();
    }

    public void setEvent(List<DanhMucBean> listItem) {
        this.listItem = listItem;
        for (DanhMucBean item : listItem) {
            item.getLbl().addMouseListener(new LabelEvent(item.getMenu(), item.getPnl(), item.getLbl()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node;

        private String menu;
        private JPanel pnlItem;
        private JLabel lblItem;

        public LabelEvent(String menu, JPanel pnlItem, JLabel lblItem) {
            this.menu = menu;
            this.pnlItem = pnlItem;
            this.lblItem = lblItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (menu) {
                case "TrangChu":
                    node = new TrangChuJPanel();
                    break;
                case "NhanVien":
                    node = new NhanVienJPanel();
                    break;
                case "KhachHang":
                    node = new KhachHangJPanel();
                    break;
                case "SanPham":
                    node = new SanPhamJPanel();
                    break;
                case "LoaiHang":
                    node = new LoaiHangJPanel();
                    break;
                case "NhaCungCap":
                    node = new NhaCungCapJPanel();
                    break;
                case "HoaDonNhap":
                    node = new HoaDonNhapJPanel();
                    break;
                case "HoaDonXuat":
                    node = new HoaDonXuatJPanel();
                    break;
                case "ThongKe":
                    node = new ThongKeJPanel();
                    break;
                case "DoiMatKhau":
                    node = new DoiMatKhauJPanel();
                    break;
                default:
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(menu);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            menuItem = menu;
            pnlItem.setBackground(new Color(96, 100, 191));
            lblItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            pnlItem.setBackground(new Color(96, 100, 191));
            lblItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!menuItem.equalsIgnoreCase(menu)) {
                pnlItem.setBackground(new Color(0, 204, 0));
                lblItem.setBackground(new Color(0, 204, 0));
            }
        }
    }

    private void setChangeBackground(String menu) {
        for (DanhMucBean item : listItem) {
            if (item.getMenu().equalsIgnoreCase(menu)) {
                item.getPnl().setBackground(new Color(96, 100, 191));
                item.getLbl().setBackground(new Color(96, 100, 191));
            } else {
                item.getPnl().setBackground(new Color(0, 204, 0));
                item.getLbl().setBackground(new Color(0, 204, 0));
            }
        }
    }
}
