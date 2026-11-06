package com.mycompany.service;

import java.util.List;
import com.mycompany.model.Menu;
import repository.MenuRepository;

public class Menuservice {

    public static final String PESAN_INVALID = "Data Input Tidak Valid";
    private final MenuRepository repo;

    public Menuservice(MenuRepository repo) {
        this.repo = repo;
    }

    public List<Menu> daftar() throws Exception {
        return repo.findAll();
    }

    public void tambah(Menu m) throws Exception {
        validasi(m);
        m.id = repo.nextId();
        repo.save(m);
    }

    public void ubah(Menu m) throws Exception {
        validasi(m);
        repo.update(m);
    }

    public void hapus(String id) throws Exception {
        repo.delete(id);
    }

    private void validasi(Menu m) {
        if (m.nama.trim().isEmpty() || m.kategori.trim().isEmpty() || m.harga <= 0) {
            throw new IllegalArgumentException(PESAN_INVALID);
        }
    }
}
