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

/**
 *
 * @author Vavouraz
 */
public class Ip {
    
     public static void main(String[] args) throws IOException 
    {
    URL connection = new URL("http://checkip.amazonaws.com/");
    URLConnection con = connection.openConnection();
    @SuppressWarnings("UnusedAssignment")
    String str = null;
    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
    str = reader.readLine();
    System.out.println(str);
     }
}
