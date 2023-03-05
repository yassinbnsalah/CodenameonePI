/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.components.InfiniteProgress;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUser;

/**
 *
 * @author yacin
 */
public class AjouteUserForm extends BaseForm {

    Form current;

    public AjouteUserForm(Resources res) {
        Toolbar tb = new Toolbar(true);
        setScrollableY(true);
        getToolbar().setTitleComponent(
                FlowLayout
                        .encloseCenterMiddle(
                                new Label("Add User", "Title")
                        )
        );
        installSidemenu(res);
        current = this;
        TextField tName = new TextField("", "tName ");
        TextField tCin = new TextField("", "tCin");
        TextField tEmail = new TextField("", "tEmail");
        TextField tadress = new TextField("", "tadress");
        TextField tpassword = new TextField("", "tpassword");
        TextField tNumero = new TextField("", "tNumero");
        TextField tAge = new TextField("", "tAge");
        Button btnajouter = new Button("Add User");
        Container c = new Container(BoxLayout.y());
        c.add(tName).add(tCin).add(tEmail).add(tNumero).add(tAge)
                .add(tadress).add(tpassword).add(btnajouter);
        //this.add(tf2);
        current.add(c);
        btnajouter.addActionListener((ActionEvent e) -> {
            try {
                if (tName.getText() == "" || tCin.getText() == ""
                        || tEmail.getText() == "" || tadress.getText() == ""
                        || tpassword.getText() == "" || tNumero.getText() == ""
                        || tAge.getText() == "") {
                    Dialog.show("plz add ", "", "annuler", "ok");
                } else {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iD = ip.showInfiniteBlocking();
                    User user = new User(String.valueOf(tCin.getText()), String.valueOf(tName.getText()),
                            String.valueOf(tNumero.getText()), String.valueOf(tAge.getText()),
                            String.valueOf(tEmail.getText()), String.valueOf(tadress.getText()),
                            String.valueOf(tpassword.getText()));
                    System.out.println("data is " + user);
                    ServiceUser.getInstance().addUser(user);
                    iD.dispose();
                    refreshTheme();
                    ClientListe f = new ClientListe(res);
                    f.show();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
    }
}
