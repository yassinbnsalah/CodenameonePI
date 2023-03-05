/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

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
        installSidemenu(res);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        getContentPane().setScrollVisible(false);
    }

}
