package com.mycompany.repository;

import java.util.List;
import com.mycompany.model.Menu;

public interface Menurepository {

    List<Menu> findAll() throws Exception;

    void save(Menu m) throws Exception;

    void update(Menu m) throws Exception;

    void delete(String id) throws Exception;

    String nextId() throws Exception;
}
