/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.codename1.uikit.pheonixui;

import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 * Utility methods common to forms e.g. for binding the side menu
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {

    public void installSidemenu(Resources res) {
        Image selection = res.getImage("selection-in-sidemenu.png");

        Image inboxImage = null;
        if (isCurrentInbox()) {
            inboxImage = selection;
        }

        Image trendingImage = null;
        if (isCurrentTrending()) {
            trendingImage = selection;
        }

        Image OrderImage = null;
        if (isCurrentOrder()) {
            OrderImage = selection;
        }

        Image statsImage = null;
        if (isCurrentStats()) {
            statsImage = selection;
        }

        Button inboxButton = new Button("Inbox", inboxImage);
        inboxButton.setUIID("SideCommand");
        inboxButton.getAllStyles().setPaddingBottom(0);
        Container inbox = FlowLayout.encloseMiddle(inboxButton);
        inbox.setLeadComponent(inboxButton);
        inbox.setUIID("SideCommand");
        inboxButton.addActionListener(e -> new InboxForm().show());
     

        
        getToolbar().addCommandToSideMenu("Profile", trendingImage, e -> new TrendingForm(res).show());
        getToolbar().addCommandToSideMenu("Product Liste", null, e -> {});
        getToolbar().addCommandToSideMenu("Order Liste", OrderImage, e -> new OrderListe(res).show());
        getToolbar().addCommandToSideMenu("Client Liste", OrderImage, e -> new ClientListe(res).show());
        getToolbar().addCommandToSideMenu("subscription Liste", trendingImage, e -> new SubscriptionListe(res).show());
        // spacer
        getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));
        getToolbar().addComponentToSideMenu(new Label(res.getImage("profile_image.png"), "Container"));
        getToolbar().addComponentToSideMenu(new Label(SessionManager.getName(), "SideCommandNoPad"));
        getToolbar().addComponentToSideMenu(new Label(SessionManager.getEmail(), "SideCommandSmall"));
        
        getToolbar().addMaterialCommandToSideMenu("Deconnexion", FontImage.MATERIAL_EXIT_TO_APP, e -> {
         
            SessionManager.pref.clearAll();
            Storage.getInstance().clearStorage();
            Storage.getInstance().clearCache();
           // System.out.println(SessionManager.getName());
            new SignInForm(res).show();
        });
        refreshTheme();
    }

    public void installSidemenuClient(Resources res) {
        Image selection = res.getImage("selection-in-sidemenu.png");

        Image inboxImage = null;
        if (isCurrentInbox()) {
            inboxImage = selection;
        }

        Image trendingImage = null;
        if (isCurrentTrending()) {
            trendingImage = selection;
        }

        Image OrderImage = null;
        if (isCurrentOrder()) {
            OrderImage = selection;
        }

        Image statsImage = null;
        if (isCurrentStats()) {
            statsImage = selection;
        }

        Button inboxButton = new Button("Inbox", inboxImage);
        inboxButton.setUIID("SideCommand");
        inboxButton.getAllStyles().setPaddingBottom(0);
        Container inbox = FlowLayout.encloseMiddle(inboxButton);
        inbox.setLeadComponent(inboxButton);
        inbox.setUIID("SideCommand");
        inboxButton.addActionListener(e -> new InboxForm().show());
        getToolbar().addComponentToSideMenu(inbox);

        // getToolbar().addCommandToSideMenu("Stats", statsImage, e -> new StatsForm(res).show());
        // getToolbar().addCommandToSideMenu("Calendar", calendarImage, e -> new CalendarForm(res).show());
        getToolbar().addCommandToSideMenu("Profile", trendingImage, e -> new TrendingForm(res).show());
        getToolbar().addCommandToSideMenu("Order Liste", OrderImage, e -> new OrderListe(res).show());
      
        getToolbar().addCommandToSideMenu("subscription Liste", trendingImage, e -> new SubscriptionListe(res).show());
        // spacer
        getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));
        getToolbar().addComponentToSideMenu(new Label(res.getImage("profile_image.png"), "Container"));
        getToolbar().addComponentToSideMenu(new Label(SessionManager.getName(), "SideCommandNoPad"));
        getToolbar().addComponentToSideMenu(new Label(SessionManager.getEmail(), "SideCommandSmall"));
         getToolbar().addMaterialCommandToSideMenu("Deconnexion", FontImage.MATERIAL_EXIT_TO_APP, e -> {
         
            SessionManager.pref.clearAll();
            Storage.getInstance().clearStorage();
            Storage.getInstance().clearCache();
           // System.out.println(SessionManager.getName());
            new SignInForm(res).show();
        });
        refreshTheme();
    }

    protected boolean isCurrentInbox() {
        return false;
    }

    protected boolean isCurrentTrending() {
        return false;
    }

    protected boolean isCurrentOrder() {
        return false;
    }

    protected boolean isCurrentStats() {
        return false;
    }
}
