/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinafarm.validate;

import com.vinfarm.utils.Notifier;
import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author ucong
 */
public class Validate {
    public static boolean isTrong(JTextField txtFiled, StringBuilder str, String msg) {
        String strFiled = txtFiled.getText().trim();
        if (strFiled.equals("")) {             
            str.append(msg).append("\n");
            txtFiled.setBackground(new Color(250, 250, 210));
            return true;
        }
        txtFiled.setBackground(Color.white);
        return false;
    }
    
    public static boolean isEmail(JTextField txtFiled, StringBuilder str, String msg) {
        String strFiled = txtFiled.getText().trim();
        String valiemail ="^[a-zA-Z]\\w{2,}@\\w{2,}(.\\w{2,}){2,7}$";
        if (!strFiled.matches(valiemail)) {             
            str.append(msg).append("\n");
            txtFiled.setBackground(new Color(250, 250, 210));
            return false;
        }
        txtFiled.setBackground(Color.white);
        return true;
    }
    
    public static boolean isDiaChi(JTextField txtFiled, StringBuilder str, String msg) {
        String strFiled = txtFiled.getText().trim();
        String valiemail1 ="/^[\\p{L} \\.'\\-]+$/";
        if (!strFiled.matches(valiemail1)) {             
            str.append(msg).append("\n");
            txtFiled.setBackground(new Color(250, 250, 210));
            return false;
        }
        txtFiled.setBackground(Color.white);
        return true;
    }
    
    public static boolean isMK(JTextField txtField, StringBuilder str, String msg){
        String strFiled = txtField.getText().trim();
        String valiMH ="([A-Z]{1})(0-9){5,}";
        if(!strFiled.matches(valiMH)){
            str.append(msg).append("\n");
            txtField.setBackground(new Color(250, 250, 210));
            return false;
        }
        txtField.setBackground(Color.white);
        return true;
    }
    
    public static boolean isTK(JTextField txtField, StringBuilder str, String msg){
        String strFiled = txtField.getText().trim();
        String valiTH ="/^[\\p{L} \\.'\\-]+$/";
        if(!strFiled.matches(valiTH)){
            str.append(msg).append("\n");
            txtField.setBackground(new Color(250, 250, 210));
            return false;
        }
        txtField.setBackground(Color.white);
        return true;
    }
    
    public static boolean isSDT(JTextField txtFiled, StringBuilder str, String msg) {
        String strFiled = txtFiled.getText().trim();
        String valiemail ="^\\d{10}$";
        if (!strFiled.matches(valiemail)) {             
            str.append(msg).append("\n");
            txtFiled.setBackground(new Color(250, 250, 210));
            return false;
        }
        txtFiled.setBackground(Color.white);
        return true;
    }
    
    public static boolean isTen(JTextField txtFiled, StringBuilder str, String msg) {
        String strFiled = txtFiled.getText().trim();
        String valiname ="/^[\\p{L} \\.'\\-]+$/";
        if (!strFiled.matches(valiname)) {             
            str.append(msg).append("\n");
            txtFiled.setBackground(new Color(250, 250, 210));
            return false;
        }
        txtFiled.setBackground(Color.white);
        return true;
    }
    
    public static boolean isMa(JTextField txtFiled, StringBuilder str, String msg) {
        String strFiled = txtFiled.getText().trim();
        String valiemail ="^(?=.*[A-Za-z].*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,}$";
        if (!strFiled.matches(valiemail)) {             
            str.append(msg).append("\n");
            txtFiled.setBackground(new Color(250, 250, 210));
            return false;
        }
        txtFiled.setBackground(Color.white);
        return true;
    }
}
