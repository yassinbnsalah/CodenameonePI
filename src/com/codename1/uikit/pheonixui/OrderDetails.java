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
import com.mycompany.services.ServiceOrder;
import com.mycompany.entities.Order;
import com.mycompany.entities.OrderLigne;
import java.util.ArrayList;
/**
 *
 * @author yacin
 */
public class OrderDetails extends BaseForm {

    Form current;
    Form listeForm;

    public OrderDetails(Resources res, int id) {
        listeForm = new Form("Order", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setScrollableY(true);
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Order Details", "Title")
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
        System.out.println("details");
         Order orderDetails = ServiceOrder.getInstance().DetailsOrder(id);
            Label Lref = new Label("Reference: " + orderDetails.getReference());
        Label Ldate = new Label("Date Order: " + orderDetails.getDateOrder());
        Label Lstate = new Label("State Order: " + orderDetails.getState());
        Container CSub = new Container(BoxLayout.y());
        CSub.add(Lref);
        CSub.add(Ldate);
        CSub.add(Lstate);
        current.add(CSub);
         ArrayList<OrderLigne> list = ServiceOrder.getInstance().DetailsOrderLigne(id);
          for (OrderLigne ordligne : list) {
            System.out.println("order Front : " + ordligne);
            Container c = new Container(BoxLayout.x());
            Container ca1 = new Container(BoxLayout.y());
            Button c1 = new Button(res.getImage("contact-a.png"));
            Label l = new Label("quantity : " + ordligne.getQuantity());
            Label l1 = new Label("Price : "+ordligne.getPrice());
            c.add(c1);
            ca1.add(l);
            ca1.add(l1);
            c.add(ca1);
           
            current.add(c);
        }
        

    }

}
