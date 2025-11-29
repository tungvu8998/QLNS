/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlns.model;

/**
 *
 * @author Admin
 */


public class NhanVien {
    private String maNV;
    private String hoTen;
    private String chucVu;
    private double luong;
    private String phone;

    public NhanVien(String maNV, String hoTen, String chucVu, double luong, String phone) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.chucVu = chucVu;
        this.luong = luong;
        this.phone = phone;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getChucVu() {
        return chucVu;
    }

    public double getLuong() {
        return luong;
    }

    public String getPhone() {
        return phone;
    }
}
