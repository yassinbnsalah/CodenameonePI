/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author yacin
 */
public class ClientListe extends BaseForm {

    Form current;
    Form listeForm;
 
    public ClientListe(Resources res) {
        //super("ClientListe" ,BoxLayout.y());
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
         ArrayList<User> list = ServiceUser.getInstance().afficherAllClient(); 
        for (User user :  list){
          //  System.out.println("subs"+user);
           Container box = new Container(BoxLayout.x());
               Container c = new Container(BoxLayout.x());
        Container ca1 = new Container(BoxLayout.y());
        Button c1 = new Button(res.getImage("contact-a.png"));
        Label l = new Label("#"+user.getName());
        Label l1 = new Label("#"+user.getEmail());
        c.add(c1);
        ca1.add(l);
       ca1.add(l1);
        
        box.add(c); 
        box.add(ca1);
       
        current.add(box);
       
        }
        FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
         fab.addActionListener(e -> {
             new AjouteUserForm(res).show();
        });
    }

  
}
