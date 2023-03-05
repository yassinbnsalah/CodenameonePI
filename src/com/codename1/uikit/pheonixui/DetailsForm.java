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

import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 * A simple details form
 *
 * @author Shai Almog
 */
public class DetailsForm extends BaseForm {

    private Form current;

    public DetailsForm(Resources res) {
      
        Toolbar tb = new Toolbar(true);
        
        setScrollableY(true);
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Client Details", "Title")
                      
                )
        );
        installSidemenu(res);
       

        current = this;
 setToolbar(tb);
        getTitleArea().setUIID("container");
    
        getContentPane().setScrollVisible(false);

        // add form 
        /*
        TextField tf1 = new TextField("", "Nom");
        TextField tf2 = new TextField("", "Num");
        Button b = new Button("Add Persone");
         
        this.add(tf1);
        this.add(tf2);
        this.add(b);*/
    }

    /* public DetailsForm(com.codename1.ui.util.Resources resourceObjectInstance) {
       setScrollableY(true);
        Toolbar tb = new Toolbar(true);
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        current = this;
        setToolbar(tb);

        getTitleArea().setUIID("container");
        setTitle("Client Details");
        getContentPane().setScrollVisible(false);

    }*/
//-- DON'T EDIT BELOW THIS LINE!!!
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.FlowLayout());
        setTitle("DetailsForm");
        setName("DetailsForm");

    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
