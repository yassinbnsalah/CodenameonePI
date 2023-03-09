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
import com.mycompany.services.ServiceSubscription;
import java.util.ArrayList;

/**
 *
 * @author yacin
 */
public class SubscriptionListe extends BaseForm {

    Form current;

    public SubscriptionListe(Resources res) {
        System.out.println("sub liste");
        Toolbar tb = new Toolbar(true);
        setScrollableY(true);
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Subscription Liste", "Title")
                )
        );
          System.out.println("admin");
        if (SessionManager.getRole().equals("ROLE_ADMIN")) {
            installSidemenu(res);
        } else {
            installSidemenuClient(res);
        }

        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        getContentPane().setScrollVisible(false);
        ArrayList<Subscription> list ; 
         if (SessionManager.getRole().equals("ROLE_ADMIN")) {
            list = ServiceSubscription.getInstance().afficherAllSubscription();
        } else {
            list = ServiceSubscription.getInstance().afficherSubscription(SessionManager.getId());
        }
        
        for (Subscription sub : list) {

            Container c = new Container(BoxLayout.x());
            Container ca1 = new Container(BoxLayout.y());
            Button c1 = new Button(res.getImage("contact-a.png"));
            Label l = new Label("#" + sub.getReference());
            Label l1 = new Label(sub.getDatesub());
            c.add(c1);
            ca1.add(l);
            ca1.add(l1);
            c.add(ca1);
            c.setLeadComponent(l);
            l.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
                if (SessionManager.getRole().equals("ROLE_ADMIN")) {
                    new SubDetails(res, sub.getId()).show();
                } else {
                   new SubDetails(res, sub.getId()).show();
                }
            });

            current.add(c);
        }
    }
}
