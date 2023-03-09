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
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yacin
 */
public class ServiceSubscription {

    public static ServiceSubscription instance = null;

    private ConnectionRequest req;

    public ServiceSubscription() {
        req = new ConnectionRequest();

    }

    public static ServiceSubscription getInstance() {
        if (instance == null) {
            instance = new ServiceSubscription();
        }
        return instance;
    }

    public void addSubscription(Subscription subscription, int id) {
        String url = Statics.BASE_URL + "addsubscriptionclient?"
                + "id=" + id + "&dateSub=" + subscription.getDatesub()
                + "&type=" + subscription.getType() + "&paiementType=" + subscription.getPaiementType()
                + "&amount=" + subscription.getAmount();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data ==" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public ArrayList<Subscription> afficherSubscription(int id) {
        ArrayList<Subscription> result = new ArrayList<>();
        req.setUrl(Statics.BASE_URL + "listecurrentsubservice?"
                + "id=" + id);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapSubscription = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapSubscription.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Subscription sub = new Subscription();
                        float id = Float.parseFloat(obj.get("id").toString());
                        System.out.println("object :" + obj);
                        sub.setId((int) id);
                        sub.setPaiementType(String.valueOf(obj.get("paiementType")));
                        sub.setReference(String.valueOf(obj.get("reference")));
                        sub.setDatesub(String.valueOf(obj.get("dateSub")));
                        sub.setState(String.valueOf(obj.get("state")));
                        result.add(sub);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    public ArrayList<Subscription> afficherAllSubscription() {
        ArrayList<Subscription> result = new ArrayList<>();
        req.setUrl(Statics.BASE_URL + "listesubservice");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapSubscription = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapSubscription.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        if (obj.get("reference") != null) {
                            Subscription sub = new Subscription();
                            float id = Float.parseFloat(obj.get("id").toString());
                            sub.setPaiementType(obj.get("paiementType").toString());
                            sub.setReference(obj.get("reference").toString());
                            sub.setDatesub(obj.get("dateSub").toString());
                            sub.setId((int) id);
                            result.add(sub);
                        }
                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    public Subscription DetailsSub(int id) {
        Subscription result = new Subscription();
        req.setUrl(Statics.BASE_URL + "subdetails?id=" + id);
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapSub = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    if (mapSub.get("reference") != null) {
                        float id = Float.parseFloat(mapSub.get("id").toString());
                        result.setPaiementType(mapSub.get("paiementType").toString());
                        result.setReference(mapSub.get("reference").toString());
                        result.setDatesub(mapSub.get("dateSub").toString());
                        result.setState(mapSub.get("state").toString());
                        result.setId((int) id);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
    public void UpdateStateSub(int id,String state) {
        String url = Statics.BASE_URL + "updatestatesubscription?id=" + id+"&state="+state;
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data ==" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

    }
    
      public void DeleteSub(int id) {
        String url = Statics.BASE_URL + "deletesub?id=" + id;
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data ==" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

    }
      
      
     
        public ArrayList<Subscription> SubscriptionByClient(int id) {
        ArrayList<Subscription> result = new ArrayList<>();
      req.setUrl(Statics.BASE_URL + "listeSubByUser?id=" + id);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapSubscription = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapSubscription.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        if (obj.get("reference") != null) {
                            Subscription sub = new Subscription();
                            float id = Float.parseFloat(obj.get("id").toString());
                            sub.setPaiementType(obj.get("paiementType").toString());
                            sub.setReference(obj.get("reference").toString());
                            sub.setDatesub(obj.get("dateSub").toString());
                            sub.setId((int) id);
                            result.add(sub);
                        }
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
