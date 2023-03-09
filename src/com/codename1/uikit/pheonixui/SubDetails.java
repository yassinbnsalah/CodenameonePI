package com.codename1.uikit.pheonixui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.BaseForm;
import com.mycompany.entities.Subscription;
import com.mycompany.services.ServiceSubscription;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yacin
 */
public class SubDetails extends BaseForm {

    Form current;
    Form listeForm;

    public SubDetails(Resources res, int id) {
        listeForm = new Form("ClientList", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setScrollableY(true);
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Subscription Details", "Title")
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
        Subscription subdetails = ServiceSubscription.getInstance().DetailsSub(id);
        Label Lref = new Label("Reference: " + subdetails.getReference());
        Label Ldate = new Label("Date Sub: " + subdetails.getDatesub());
        Label Lstate = new Label("State Sub: " + subdetails.getState());
        Container CSub = new Container(BoxLayout.y());
        CSub.add(Lref);
        CSub.add(Ldate);
        CSub.add(Lstate);
        System.out.println(SessionManager.getRole());
        if (SessionManager.getRole().equals("ROLE_ADMIN")) {
            ComboBox<String> typeSubE = new ComboBox<>("Confirmed", "Suspend", "Cancel");
            Button btnUpdate = new Button("Update Sub");
            btnUpdate.addActionListener((ActionEvent e) -> {
                try {
                    System.out.println("Update State");
                    ServiceSubscription.getInstance().UpdateStateSub(id, String.valueOf(typeSubE.getSelectedIndex() + 1));

                    refreshTheme();
                    ClientListe f = new ClientListe(res);
                    f.show();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            });
            Button btnDelete = new Button("Delete Sub");
            btnDelete.addActionListener((ActionEvent e) -> {
                try {
                    System.out.println("Update State");
                    ServiceSubscription.getInstance().DeleteSub(id);

                    refreshTheme();
                    ClientListe f = new ClientListe(res);
                    f.show();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            });
            CSub.add(typeSubE);
            CSub.add(btnUpdate);
            CSub.add(btnDelete);
        }

        current.add(CSub);
    }

}
