/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Subscription;
import com.mycompany.entities.User;
import static com.mycompany.services.ServiceSubscription.instance;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yacin
 */
public class ServiceUser {

    public static ServiceUser instance = null;

    private ConnectionRequest req;

    public ServiceUser() {
        req = new ConnectionRequest();

    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public void loginAdmin(User user) {

        String url = Statics.BASE_URL + "user/signin"
                + "?Email=" + user.getEmail()
                + "&Password=" + user.getPassword();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data ==" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void addUser(User user) {
        String url = Statics.BASE_URL + "user/signup"
                + "?CIN=" + user.getCIN() + "&Name=" + user.getName()
                + "&Email=" + user.getEmail()
                + "&Adresse=" + user.getAdresse()
                + "&Password=" + user.getPassword()
                + "&Numero=" + user.getNumero()
                + "&age=" + user.getAge();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data ==" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public ArrayList<User> afficherAllClient() {
        ArrayList<User> result = new ArrayList<>();
        req.setUrl(Statics.BASE_URL + "user/liste");
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapUser = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapUser.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        System.out.println("users : " + obj);
                        User userL = new User();

                        float id = Float.parseFloat(obj.get("id").toString());
                        userL.setName(obj.get("Name").toString());
                        userL.setCIN(obj.get("CIN").toString());
                        userL.setEmail(obj.get("Email").toString());
                        userL.setAdresse(obj.get("Adresse").toString());
                        userL.setId((int) id);

                        result.add(userL);

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
}
