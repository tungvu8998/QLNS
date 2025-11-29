/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlns.ui;

/**
 *
 * @author Admin
 */

import qlns.dao.NhanVienDAO;
import qlns.model.NhanVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class QLNhanVienFrame extends JFrame {

    private JTextField txtMaNV, txtHoTen, txtChucVu, txtLuong, txtPhone;
    private JButton btnHienThi, btnThem, btnCapNhat, btnXoa, btnReset;
    private JTable tbl;

    private final NhanVienDAO dao = new NhanVienDAO();

    public QLNhanVienFrame() {
        setTitle("Quan Ly Nhan Vien");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();  
        loadTable(); 
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        // =============== Panel nhập liệu ===============
        JPanel panelNhap = new JPanel(new GridLayout(5, 2, 5, 5));
        panelNhap.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelNhap.add(new JLabel("Ma NV:"));
        txtMaNV = new JTextField();
        panelNhap.add(txtMaNV);

        panelNhap.add(new JLabel("Ho Ten:"));
        txtHoTen = new JTextField();
        panelNhap.add(txtHoTen);

        panelNhap.add(new JLabel("Chuc Vu:"));
        txtChucVu = new JTextField();
        panelNhap.add(txtChucVu);

        panelNhap.add(new JLabel("Luong:"));
        txtLuong = new JTextField();
        panelNhap.add(txtLuong);

        panelNhap.add(new JLabel("Phone:"));
        txtPhone = new JTextField();
        panelNhap.add(txtPhone);

        add(panelNhap, BorderLayout.NORTH);

        // =============== Bảng JTable ===============
        tbl = new JTable(new DefaultTableModel(
                new Object[]{"Ma NV", "Ho Ten", "Chuc Vu", "Luong", "Phone"}, 0));

        tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });

        add(new JScrollPane(tbl), BorderLayout.CENTER);

        // =============== Panel chứa 5 button ===============
        JPanel panelButton = new JPanel(new GridLayout(1, 5, 10, 10));
        panelButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnHienThi = new JButton("Hien thi");
        btnThem = new JButton("Them");
        btnCapNhat = new JButton("Cap nhat");
        btnXoa = new JButton("Xoa");
        btnReset = new JButton("Reset");

        panelButton.add(btnHienThi);
        panelButton.add(btnThem);
        panelButton.add(btnCapNhat);
        panelButton.add(btnXoa);
        panelButton.add(btnReset);

        add(panelButton, BorderLayout.SOUTH);

        // =============== Gán sự kiện cho button ===============
        btnHienThi.addActionListener(e -> loadTable());
        btnThem.addActionListener(e -> them());
        btnCapNhat.addActionListener(e -> capNhat());
        btnXoa.addActionListener(e -> xoa());
        btnReset.addActionListener(e -> resetForm());
    }

    // ====================== XỬ LÝ CHỨC NĂNG ======================
    public void loadTable() {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);

        ArrayList<NhanVien> list = dao.getAll();

        for (NhanVien nv : list) {
            model.addRow(new Object[]{
                    nv.getMaNV(),
                    nv.getHoTen(),
                    nv.getChucVu(),
                    nv.getLuong(),
                    nv.getPhone()
            });
        }
    }

    public void resetForm() {
        txtMaNV.setText("");
        txtHoTen.setText("");
        txtChucVu.setText("");
        txtLuong.setText("");
        txtPhone.setText("");
    }

    public void them() {
        try {
            String ma = txtMaNV.getText().trim();
            if (ma.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ma NV khong duoc de trong!");
                return;
            }
            String ten = txtHoTen.getText().trim();
            String cv = txtChucVu.getText().trim();
            double luong = txtLuong.getText().trim().isEmpty() ? 0 : Double.parseDouble(txtLuong.getText().trim());
            String phone = txtPhone.getText().trim();

            NhanVien nv = new NhanVien(ma, ten, cv, luong, phone);

            if (dao.insert(nv)) {
                JOptionPane.showMessageDialog(this, "Them thanh cong");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Ma NV da ton tai hoac loi");
            }

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Luong phai la so!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Loi khi them: " + e.getMessage());
        }
    }

    public void capNhat() {
        try {
            String ma = txtMaNV.getText().trim();
            if (ma.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chon 1 nhan vien de cap nhat (Ma NV).");
                return;
            }
            String ten = txtHoTen.getText().trim();
            String cv = txtChucVu.getText().trim();
            double luong = txtLuong.getText().trim().isEmpty() ? 0 : Double.parseDouble(txtLuong.getText().trim());
            String phone = txtPhone.getText().trim();

            NhanVien nv = new NhanVien(ma, ten, cv, luong, phone);

            if (dao.update(nv)) {
                JOptionPane.showMessageDialog(this, "Cap nhat thanh cong");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Cap nhat that bai");
            }

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Luong phai la so!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Loi khi cap nhat: " + e.getMessage());
        }
    }

    public void xoa() {
        String ma = txtMaNV.getText().trim();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chon 1 nhan vien de xoa (Ma NV).");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Ban co chac muon xoa MaNV=" + ma + " ?", "Xac nhan", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        if (dao.delete(ma)) {
            JOptionPane.showMessageDialog(this, "Xoa thanh cong");
            loadTable();
            resetForm();
        } else {
            JOptionPane.showMessageDialog(this, "Khong the xoa");
        }
    }

    // ======================= Sự kiện click bảng =======================
    private void tblMouseClicked(java.awt.event.MouseEvent evt) {
        int row = tbl.getSelectedRow();
        if (row < 0) return;

        txtMaNV.setText(tbl.getValueAt(row, 0).toString());
        txtHoTen.setText(tbl.getValueAt(row, 1).toString());
        txtChucVu.setText(tbl.getValueAt(row, 2).toString());
        txtLuong.setText(tbl.getValueAt(row, 3).toString());
        txtPhone.setText(tbl.getValueAt(row, 4).toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QLNhanVienFrame frame = new QLNhanVienFrame();
            frame.setVisible(true);
        });
    }
}

