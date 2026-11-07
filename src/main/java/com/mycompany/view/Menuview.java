package com.mycompany.view;

import com.mycompany.model.Menu;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Menuview extends JFrame {

    public JTextField txtNama = new JTextField(15);
    public JTextField txtHarga = new JTextField(15);
    public JComboBox<String> cmbKategori = new JComboBox<>(new String[]{"Lauk", "Sayuran", "Minuman"});
    public JButton btnTambah = new JButton("Tambah");
    public JButton btnUbah = new JButton("Ubah");
    public JButton btnHapus = new JButton("Hapus");
    public DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nama", "Kategori", "Harga"}, 0);
    public JTable table = new JTable(model);

    public Menuview() {
        super("Kelola Menu - SON AMPERA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
        form.add(new JLabel("Nama Menu"));
        form.add(txtNama);
        form.add(new JLabel("Kategori"));
        form.add(cmbKategori);
        form.add(new JLabel("Harga"));
        form.add(txtHarga);

        JPanel btn = new JPanel();
        btn.add(btnTambah);
        btn.add(btnUbah);
        btn.add(btnHapus);

        JPanel top = new JPanel(new BorderLayout());
        top.add(form, BorderLayout.CENTER);
        top.add(btn, BorderLayout.SOUTH);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        setSize(500, 450);
        setLocationRelativeTo(null);
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
