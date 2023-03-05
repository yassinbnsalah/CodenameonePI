/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.components.InfiniteProgress;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Subscription;
import com.mycompany.services.ServiceSubscription;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author yacin
 */
public class AjoutSubscriptionForm extends com.codename1.ui.Form {

    Form current;

    public AjoutSubscriptionForm(Resources res) {
        super("Add Sub To Client", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        setTitle("Client Details");
        getContentPane().setScrollVisible(false);
        Picker dateS = new Picker();
        //TextField dateS = new TextField("", "set date");
        //  dateS.setUIID("TextFieldBlack");
        addStringValue("dateS", dateS);

        ComboBox<String> typeSubE = new ComboBox<>(
                "1Month", "3Months", "6Months");
        addStringValue("Subber", typeSubE);

        TextField paiementType = new TextField("", "PaiementType");
        // paiementType.setUIID("TextFieldBlack");
        addStringValue("paiementType", paiementType);

        TextField amount = new TextField("", "amount");
        //  amount.setUIID("TextFieldBlack");
        addStringValue("amount", amount);

        Button btnajouter = new Button("ajouter");
        addStringValue("", btnajouter);

        btnajouter.addActionListener((ActionEvent e) -> {
            try {
                if (dateS.getText() == ""
                        || amount.getText() == "" || paiementType.getText() == "") {
                    Dialog.show("plz add ", "", "annuler", "ok");
                } else {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iD = ip.showInfiniteBlocking();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                    //System.out.println(dateS.getDate());
                    String datestring = (new SimpleDateFormat("yyyy-MM-dd")).format(dateS.getDate());
                    System.out.println(datestring);
                    System.out.println("type is " + typeSubE.getSelectedIndex());
                    Subscription s = new Subscription(
                            datestring, String.valueOf(typeSubE.getSelectedIndex() + 1),
                            String.valueOf(paiementType.getText()),
                            Integer.valueOf(amount.getText()),
                            1);
                    System.out.println("data is " + s);
                    ServiceSubscription.getInstance().addSubscription(s);
                    iD.dispose();
                    refreshTheme();
                   
                    DetailsForm f = new DetailsForm(res); 
                    f.show(); 
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
    }

    private void addStringValue(String obj, Component v) {
        add(BorderLayout.west(new Label(obj, "paddedLabel"))
                .add(BorderLayout.CENTER, v));
        //  aad(createLineSeparator(Oxeeeeee));
    }

}
