package com.mycompany.controller;

import com.mycompany.model.Menu;
import com.mycompany.service.Menuservice;
import com.mycompany.view.Menuview;

public class Menucontroller {

    private final Menuview view;
    private final Menuservice service;

    public Menucontroller(Menuview view, Menuservice service) {
        this.view = view;
        this.service = service;

        view.btnTambah.addActionListener(e -> aksi(true));
        view.btnUbah.addActionListener(e -> aksi(false));
        view.btnHapus.addActionListener(e -> hapus());
        view.table.getSelectionModel().addListSelectionListener(e -> isiForm());
        muatData();
    }

    private void muatData() {
        try {
            view.tampilkan(service.daftar());
        } catch (Exception ex) {
            view.pesan("Gagal memuat data: " + ex.getMessage());
        }
    }

    private void isiForm() {
        int r = view.table.getSelectedRow();
        if (r < 0) {
            return;
        }
        view.txtNama.setText(view.model.getValueAt(r, 1).toString());
        view.cmbKategori.setSelectedItem(view.model.getValueAt(r, 2));
        view.txtHarga.setText(view.model.getValueAt(r, 3).toString());
    }

    private void aksi(boolean tambah) {
        try {
            String id = null;
            if (!tambah) {
                int r = view.table.getSelectedRow();
                if (r < 0) {
                    view.pesan("Pilih menu pada tabel dahulu");
                    return;
                }
                id = view.model.getValueAt(r, 0).toString();
            }
            int harga = Integer.parseInt(view.txtHarga.getText().trim());
            Menu m = new Menu(id, view.txtNama.getText(), (String) view.cmbKategori.getSelectedItem(), harga);
            if (tambah) {
                service.tambah(m);
            } else {
                service.ubah(m);
            }
            view.pesan("Berhasil disimpan");
            view.kosongkanForm();
            muatData();
        } catch (NumberFormatException ex) {
            view.pesan(Menuservice.PESAN_INVALID);
        } catch (IllegalArgumentException ex) {
            view.pesan(ex.getMessage());
        } catch (Exception ex) {
            view.pesan("Kesalahan database: " + ex.getMessage());
        }
    }

    private void hapus() {
        int r = view.table.getSelectedRow();
        if (r < 0) {
            view.pesan("Pilih menu pada tabel dahulu");
            return;
        }
        try {
            service.hapus(view.model.getValueAt(r, 0).toString());
            view.kosongkanForm();
            muatData();
        } catch (Exception ex) {
            view.pesan("Kesalahan database: " + ex.getMessage());
        }
    }
}
