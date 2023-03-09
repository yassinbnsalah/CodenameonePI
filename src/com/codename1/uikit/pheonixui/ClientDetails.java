/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Subscription;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceSubscription;
import com.mycompany.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author yacin
 */
public class ClientDetails extends BaseForm {

    Form current;
    Form listeForm;
    private MultiButton gui_Multi_Button_1;
    private com.codename1.components.MultiButton gui_LA;

    public ClientDetails(Resources res, int id) {
        listeForm = new Form("ClientList", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setScrollableY(true);
        /*getToolbar().addMaterialCommandToLeftBar("Back",FontImage.MATERIAL_ARROW_BACK , 
                        e ->{
                            new ClientListe(res).showBack() ; 
                        });*/
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Client Details", "Title")
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
        User userDetail = ServiceUser.getInstance().affichageUserBYID(id);

        // System.out.println("user detailer " + userDetail.toString() );
        Label L1 = new Label(userDetail.getName());
        Container box = new Container(BoxLayout.y());
        Container c = new Container(BoxLayout.x());
        Container SubContainer = new Container(BoxLayout.x());
        Container ca1 = new Container(BoxLayout.y());
        Button c1 = new Button(res.getImage("contact-a.png"));
        Label l = new Label("#" + userDetail.getName());
        Label l1 = new Label("#" + userDetail.getEmail());

        c.add(c1);
        ca1.add(l);
        ca1.add(l1);

        box.add(c);
        box.add(ca1);

        Label lRecentSub = new Label("Recent Sub");

        box.add(lRecentSub);

        ArrayList<Subscription> list = ServiceSubscription.getInstance().afficherSubscription(id);

        for (Subscription sub : list) {
            if (!sub.getReference().equals("null")) {
                Container gui_Container_1 = new Container(BoxLayout.y());
                MultiButton gui_Multi_Button_1 = new MultiButton();
                gui_Multi_Button_1.setUIID("Label");
                gui_Multi_Button_1.setName("Multi_Button_1");
                gui_Multi_Button_1.setIcon(res.getImage("contact-c.png"));
                gui_Multi_Button_1.setPropertyValue("line1", String.valueOf(sub.getId()));
                gui_Multi_Button_1.setPropertyValue("line2", sub.getReference());
                box.add(gui_Multi_Button_1);
                box.setLeadComponent(gui_Multi_Button_1);
                gui_Multi_Button_1.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
                    new SubDetails(res, sub.getId()).show();
                    //  new SubDetails(res, user.getId()).show();
                });

            }

        }
        current.add(box);

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder) fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> {
            new AjoutSubscriptionForm(res, id).showBack();
        });

    }

}
