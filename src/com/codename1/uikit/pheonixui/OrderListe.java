/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Subscription;
import com.mycompany.services.ServiceOrder;
import com.mycompany.services.ServiceSubscription;
import java.util.ArrayList;
import com.mycompany.entities.Order;

/**
 *
 * @author yacin
 */
public class OrderListe extends BaseForm {

    Form current;

    public OrderListe(Resources res) {

        Toolbar tb = new Toolbar(true);
        setScrollableY(true);
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Order Liste", "Title")
                )
        );
        if (SessionManager.getRole().equals("ROLE_ADMIN")) {
            installSidemenu(res);
        } else {
            installSidemenuClient(res);
        }
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        getContentPane().setScrollVisible(false);

        ArrayList<Order> list = ServiceOrder.getInstance().afficherOrder();
        System.out.println("order here");
        for (Order ord : list) {
            System.out.println("order Front : " + ord);
            Container c = new Container(BoxLayout.x());
            Container ca1 = new Container(BoxLayout.y());
            Button c1 = new Button(res.getImage("contact-a.png"));
            Label l = new Label("#" + ord.getReference());
            Label l1 = new Label(ord.getDateOrder());
            c.add(c1);
            ca1.add(l);
            ca1.add(l1);
            c.add(ca1);
            c.setLeadComponent(l);
            l.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
                new OrderDetails(res, ord.getId()).show();
            });
            current.add(c);
        }
    }
}
