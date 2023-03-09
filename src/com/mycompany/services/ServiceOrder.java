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
import com.mycompany.entities.Order;
import com.mycompany.entities.OrderLigne;
import com.mycompany.entities.Subscription;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yacin
 */
public class ServiceOrder {

    public static ServiceOrder instance = null;

    private ConnectionRequest req;

    public ServiceOrder() {
        req = new ConnectionRequest();

    }

    public static ServiceOrder getInstance() {
        if (instance == null) {
            instance = new ServiceOrder();
        }
        return instance;
    }

    public ArrayList<Order> afficherOrder() {
        ArrayList<Order> result = new ArrayList<>();
        req.setUrl(Statics.BASE_URL + "displayOrder");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapSubscription = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapSubscription.get("root");
                    System.out.println("listOfMaps : " + listOfMaps);
                    for (Map<String, Object> obj : listOfMaps) {

                        Order order = new Order();
                        float id = Float.parseFloat(obj.get("id").toString());

                        order.setId((int) id);
                        order.setReference(String.valueOf(obj.get("reference")));
                        order.setDateOrder(String.valueOf(obj.get("dateOrder")));
                        order.setState(String.valueOf(obj.get("state")));
                        System.out.println("result : " + order);
                        result.add(order);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    public Order DetailsOrder(int id) {
        Order result = new Order();
        req.setUrl(Statics.BASE_URL + "orderDetails?id=" + id);
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapSub = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    if (mapSub.get("reference") != null) {
                        System.out.println("hello");
                        float id = Float.parseFloat(mapSub.get("id").toString());
                        result.setDateOrder(String.valueOf(mapSub.get("dateOrder")));
                        result.setReference(String.valueOf(mapSub.get("reference")));
                        result.setState(String.valueOf(mapSub.get("state")));
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

    public ArrayList<OrderLigne> DetailsOrderLigne(int id) {
        ArrayList<OrderLigne> result = new ArrayList<>();
        req.setUrl(Statics.BASE_URL + "orderligneDetails?id=" + id);
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapSubscription = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapSubscription.get("root");
                    System.out.println("listOfMaps : " + listOfMaps);
                    for (Map<String, Object> obj : listOfMaps) {

                        OrderLigne orderligne = new OrderLigne();
                        float id = Float.parseFloat(obj.get("id").toString());
                       
                        float qte = Float.parseFloat(obj.get("quantity").toString());
                        orderligne.setQuantity((int)qte);
                        float price = Float.parseFloat(obj.get("price").toString());
                        orderligne.setPrice((int)price);
                        
                        // orderligne.setState(String.valueOf(obj.get("state"))); System.out.println("OrderLigne is here  : " + orderligne);
                        orderligne.setId((int) id);
                        result.add(orderligne);
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
