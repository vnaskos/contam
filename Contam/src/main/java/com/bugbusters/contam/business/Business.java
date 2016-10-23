/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bugbusters.contam.business;

/**
 *
 * @author Andreas
 */
public class Business {
  public int id;
  public String name; 
  public String address;
  public String description;
  public double longitude;
  public double latitude;
  public int postal_code;
  
  public Business(){
      
  }
  
  public String getName(){
      return name;
  }
  
  public void setName(String name){
      this.name = name;
  }
  
  public String getAddress(){
      return address;
  }
  
  public void setAddress(String address){
      this.address = address;
  }
  
  public String getDescription(){
      return description;
  }
  
  public void setDescription(String description){
      this.description = description;
  }
  
  public int getID(){
      return id;
  }
  
  public void setID(int id){
      this.id = id;
  }
  
  public double getLongitude(){
      return longitude;
  }
  
  public void setLongitude(double longitude){
      this.longitude = longitude;
  }
  
  public double getLatitude(){
      return latitude;
  }
  
  public void setLatitude(double latitude){
      this.latitude = latitude;
  }
}
