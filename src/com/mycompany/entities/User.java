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
public class User {

    private int id;
    private String CIN;
    private String Name;
    private String Numero;
    private String Age;
    private String Email;
    private String Adresse;
    private String Password;
    
    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public User(String Email, String Password) {
        this.Email = Email;
        this.Password = Password;
    }
    

    public User(int id, String CIN, String Name, String Numero, String Age, String Email, String Adresse) {
        this.id = id;
        this.CIN = CIN;
        this.Name = Name;
        this.Numero = Numero;
        this.Age = Age;
        this.Email = Email;
        this.Adresse = Adresse;
    }

    public User(String CIN, String Name, String Numero, String Age, String Email, String Adresse) {
        this.CIN = CIN;
        this.Name = Name;
        this.Numero = Numero;
        this.Age = Age;
        this.Email = Email;
        this.Adresse = Adresse;
    }

    public User(String CIN, String Name, String Numero, String Age, String Email, String Adresse, String Password) {
        this.CIN = CIN;
        this.Name = Name;
        this.Numero = Numero;
        this.Age = Age;
        this.Email = Email;
        this.Adresse = Adresse;
        this.Password = Password;
    }

    public int getId() {
        return id;
    }

    public User() {
    }

    public String getCIN() {
        return CIN;
    }

    public String getName() {
        return Name;
    }

    public String getNumero() {
        return Numero;
    }

    public String getAge() {
        return Age;
    }

    public String getEmail() {
        return Email;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

}
