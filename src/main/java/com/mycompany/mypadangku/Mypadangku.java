package com.mycompany.mypadangku;

import com.mycompany.controller.Menucontroller;
import javax.swing.SwingUtilities;
import com.mycompany.repository.Mysqlmenurepository;
import com.mycompany.service.Menuservice;
import com.mycompany.view.Menuview;

public class Mypadangku {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menuservice service = new Menuservice(new Mysqlmenurepository());
            Menuview view = new Menuview();
            new Menucontroller(view, service);
            view.setVisible(true);
        });
    }
}
