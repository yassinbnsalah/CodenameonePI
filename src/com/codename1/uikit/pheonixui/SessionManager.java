/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.io.Preferences;

/**
 *
 * @author yacin
 */
public class SessionManager {

    public static Preferences pref;
    private static int id;
    private static String Name;
    private static String email;
    private static String passowrd;
    private static String role;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id", id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id", id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getName() {
        return pref.get("name", Name);
    }

    public static void setName(String Name) {
        pref.set("name", Name);
    }

    public static String getEmail() {
        return pref.get("email", email);
    }

    public static void setEmail(String email) {
        pref.set("email", email);
    }

    public static String getPassowrd() {
        return pref.get("passowrd", passowrd);
    }

    public static void setPassowrd(String passowrd) {
        pref.set("passowrd", passowrd);
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        SessionManager.role = role;
    }

}
