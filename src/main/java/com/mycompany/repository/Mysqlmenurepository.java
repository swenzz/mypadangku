package com.mycompany.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.model.Menu;

public class Mysqlmenurepository implements Menurepository {

    private static final String URL = "jdbc:mysql://localhost:3306/db_sonampera";
    private static final String USER = "skennatic";
    private static final String PASS = "munjul100";

    private Connection conn() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    @Override
    public List<Menu> findAll() throws SQLException {
        List<Menu> list = new ArrayList<>();
        try (Connection c = conn(); ResultSet rs = c.createStatement().executeQuery("SELECT * FROM menu ORDER BY id_menu")) {
            while (rs.next()) {
                list.add(new Menu(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
        }
        return list;
    }

    @Override
    public void save(Menu m) throws SQLException {
        try (Connection c = conn(); PreparedStatement ps = c.prepareStatement(
                "UPDATE menu SET nama_menu=?, kategori=?, harga_menu=? WHERE id_menu=?")) {
            ps.setString(1, m.nama);
            ps.setString(2, m.kategori);
            ps.setInt(3, m.harga);
            ps.setString(4, m.id);
            ps.executeUpdate();
        }
    }

    @Override
    public void update(Menu m) throws SQLException {
        try (Connection c = conn(); PreparedStatement ps = c.prepareStatement("UPDATE menu SET nama_menu=?, kategori=?, harga_menu=? WHERE id_menu=?")) {
            ps.setString(1, m.nama);
            ps.setString(2, m.kategori);
            ps.setInt(3, m.harga);
            ps.setString(4, m.id);
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        try (Connection c = conn(); PreparedStatement ps = c.prepareStatement("DELETE FROM menu WHERE id_menu=?")) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public String nextId() throws SQLException {
        try (Connection c = conn(); ResultSet rs = c.createStatement().executeQuery(
                "SELECT id_menu FROM menu ORDER BY id_menu DESC LIMIT 1")) {
            int n = rs.next() ? Integer.parseInt(rs.getString(1).substring(2)) + 1 : 1;
            return String.format("MN%03d", n);
        }
    }
}
