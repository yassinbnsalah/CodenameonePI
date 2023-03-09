/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author yacin
 */
public class Subscription {
    
    private int id ; 
    private String datesub ; 
    private String type ; 
    private String state ;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    private String paiementType ; 
    private int amount ; 
    private int idUser ; 
    private String reference  ; 
    private User userSub ;
    public Subscription() {
    }

    public Subscription(int id, String datesub, String type, String paiementType, int amount, int idUser) {
        this.id = id;
        this.datesub = datesub;
        this.type = type;
        this.paiementType = paiementType;
        this.amount = amount;
        this.idUser = idUser;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }

    public Subscription(String datesub, String type, String paiementType, int amount, int idUser) {
        this.datesub = datesub;
        this.type = type;
        this.paiementType = paiementType;
        this.amount = amount;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setUserSub(User userSub) {
        this.userSub = userSub;
    }

    public User getUserSub() {
        return userSub;
    }

    public String getDatesub() {
        return datesub;
    }

    public String getType() {
        return type;
    }

    public String getPaiementType() {
        return paiementType;
    }

    public int getAmount() {
        return amount;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDatesub(String datesub) {
        this.datesub = datesub;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPaiementType(String paiementType) {
        this.paiementType = paiementType;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
}
