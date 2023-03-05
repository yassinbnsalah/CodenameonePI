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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author yacin
 */
public class ClientListe extends BaseForm {

    Form current;
    Form listeForm;

    public ClientListe(Resources res) {
        listeForm = new Form("Russia 2018", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setScrollableY(true);
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Client Liste", "Title")
                )
        );
        installSidemenu(res);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        getContentPane().setScrollVisible(false);
        
        Container c = new Container(BoxLayout.x());
        Container ca1 = new Container(BoxLayout.y());
        Button c1 = new Button(res.getImage("contact-a.png"));
        Label l = new Label("yessine ben salah");
        Label l1 = new Label("yessinebensalah@gmail.com");
        c.add(c1);
        ca1.add(l);
        ca1.add(l1);
        c.add(ca1);
        current.add(c);
        
        
    }
}
