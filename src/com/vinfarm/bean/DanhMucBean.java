/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vinfarm.bean;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author hau
 */
public class DanhMucBean {
    
    private String menu;
    private JPanel pnl;
    private JLabel lbl;

    public DanhMucBean() {
    }

    public DanhMucBean(String menu, JPanel pnl, JLabel lbl) {
        this.menu = menu;
        this.pnl = pnl;
        this.lbl = lbl;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public JPanel getPnl() {
        return pnl;
    }

    public void setPnl(JPanel pnl) {
        this.pnl = pnl;
    }

    public JLabel getLbl() {
        return lbl;
    }

    public void setLbl(JLabel lbl) {
        this.lbl = lbl;
    }

    
    
}
