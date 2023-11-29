/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinfarm.utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author ucong
 */
public class Notifier {
    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, 
                "VinFarm", JOptionPane.INFORMATION_MESSAGE);
    }
    public static boolean confirm(Component parent, String message, String add) {
        int result = JOptionPane.showConfirmDialog(parent, message, 
                "VinFarm" + add, 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    } 
    public static String prompt(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message, 
                "VinFarm", JOptionPane.INFORMATION_MESSAGE);
    }
}
