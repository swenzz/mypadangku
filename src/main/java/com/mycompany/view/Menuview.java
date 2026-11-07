package com.mycompany.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.mycompany.model.Menu;

public class Menuview extends JFrame {

    public JTextField txtNama = new JTextField(15);
    public JTextField txtHarga = new JTextField(15);
    public JComboBox<String> cmbKategori = new JComboBox<>(new String[]{"ID", "Nama", "Kategori", "Harga"});
    public JButton btnTambah = new JButton("Tambah");
    public JButton btnUbah = new JButton("Ubah");
    public JButton btnHapus = new JButton("Hapus");
    public DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nama", "Kategori", "Harga"}, 0);

    public JTable table = new JTable(model);

    public Menuview() {
        super("Kelola Menu - SON AMPERA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));
    }

    public void tampilkan(List<Menu> list) {
        model.setRowCount(0);
        for (Menu m : list) {
            model.addRow(new Object[]{m.id, m.nama, m.kategori, m.harga});
        }
    }

    public void kosongkanForm() {
        txtNama.setText("");
        txtHarga.setText("");
        table.clearSelection();
    }

    public void pesan(String s) {
        JOptionPane.showMessageDialog(this, s);
    }
}
