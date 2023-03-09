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
import com.codename1.uikit.pheonixui.SessionManager;
import com.mycompany.entities.Subscription;
import com.mycompany.entities.User;
import static com.mycompany.services.ServiceSubscription.instance;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

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
        JSONParser jsonp;
        jsonp = new JSONParser();
        // String urlll = "http://127.0.0.1:8000/user/details?id="+user.getId() ; 
        String url = Statics.BASE_URL + "user/signin"
                + "?Email=" + user.getEmail()
                + "&Password=" + user.getPassword();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
//            User user = new User(req.getResponseData()) ; 
            try {
                System.out.println(jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray())));
                Map<String, Object> usere = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                float id = Float.parseFloat(usere.get("id").toString());
                SessionManager.setId((int) id);
                SessionManager.setPassowrd(usere.get("Password").toString());
                SessionManager.setName(usere.get("Name").toString());
                SessionManager.setEmail(usere.get("Email").toString());

                List<Object> roles = Arrays.asList(jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray())).get("roles"));
                System.out.println("roles : " + roles.get(0).toString());
                String subroles = roles.get(0).toString().substring(1, roles.get(0).toString().indexOf(","));
                System.out.println("subroles :"+subroles);
                if (subroles.equals("ROLE_ADMIN")) {
                    SessionManager.setRole("ROLE_ADMIN");
                } else {
                    SessionManager.setRole("ROLE_CLIENT");
                }
                System.out.println(subroles);
                //     List<String> roles =  Arrays.asList(jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray())).get("roles"));
            } catch (Exception es) {
                es.printStackTrace();
            }

            System.out.println("data ==" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void Logout() {
        //float id = Float.parseFloat(usere.get("id").toString());
        SessionManager.setId(0);
        SessionManager.setPassowrd("");
        SessionManager.setName("");
        SessionManager.setEmail("");
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
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapUser = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapUser.get("root");
                    System.out.println("probleme");
                    for (Map<String, Object> obj : listOfMaps) {
                        if (obj != null) {
                            User userL = new User();

                            float id = Float.parseFloat(obj.get("id").toString());
                            userL.setName(obj.get("Name").toString());
                            userL.setCIN(obj.get("CIN").toString());
                            userL.setEmail(obj.get("Email").toString());
                            userL.setAdresse(obj.get("Adresse").toString());
                            userL.setId((int) id);

                            result.add(userL);
                        }
                        //System.out.println("users : " + obj);

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    public User affichageUserBYID(int id) {
        User result = new User();
        req.setUrl(Statics.BASE_URL + "user/details" + "?id=" + id);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {

                    Map<String, Object> mapUser = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    System.out.println("mapUser :" + mapUser);
                    result.setName(mapUser.get("Name").toString());
                    result.setCIN(mapUser.get("CIN").toString());
                    result.setEmail(mapUser.get("Email").toString());
                    result.setAdresse(mapUser.get("Adresse").toString());
                    result.setId((int) id);

                } catch (Exception ex) {

                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
        //NetworkManager.getInstance().addToQueueAndWait(req);

    }

}
