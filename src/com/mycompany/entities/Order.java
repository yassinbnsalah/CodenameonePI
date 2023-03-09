/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author yacin
 */
public class Order {
       private int id ; 
       private String reference ; 
       private String dateOrder ; 
       private String state ; 
       
    public Order(int id, String reference, String dateOrder, String state) {
        this.id = id;
        this.reference = reference;
        this.dateOrder = dateOrder;
        this.state = state;
    }

    public Order() {
    }

    public Order(String reference, String dateOrder, String state) {
        this.reference = reference;
        this.dateOrder = dateOrder;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", reference=" + reference + ", dateOrder=" + dateOrder + ", state=" + state + '}';
    }

    public String getState() {
        return state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public void setState(String state) {
        this.state = state;
    }
       
}
