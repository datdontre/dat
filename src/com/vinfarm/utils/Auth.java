/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vinfarm.utils;

import com.vinfarm.entity.NhanVien;



/**
 *
 * @author hau
 */
public class Auth {
//    Tạo đối tượng chứa thông tin người dùng sau khi đăng nhập
    public static NhanVien user = null;
//    Xoá thông tin của người sử dụng khi có yêu cầu đăng xuất
    public static void clear() {
        Auth.user = null;
    }
//    Kiểm tra người dùng đã đăng nhập hay chưa
    public static boolean isLogin() {
        return Auth.user != null;
    }
//    Kiểm tra quyền hạn (vai trò)
    public static boolean isManager() {
        return Auth.isLogin() && user.isVaiTro();
    } 
}
