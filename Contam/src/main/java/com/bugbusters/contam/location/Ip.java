/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bugbusters.contam.location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vavouraz
 */
public class Ip {

    public static String getClientPublicIP() {
        try {
            URL connection = new URL("http://checkip.amazonaws.com/");
            URLConnection con = connection.openConnection();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String ip = reader.readLine();
            
            return ip;
        } catch (IOException ex) {
            Logger.getLogger(Ip.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
}
