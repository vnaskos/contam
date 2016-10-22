/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas
 */
public class BusinessDAOImpl implements BusinessDAO{
   List<Business> businesslist = new ArrayList<>(); 

    @Override
    public List<Business> getAllBusiness() {
       return businesslist;
    }

    @Override
    public Business getBusiness(int id) {
        for(Business business : businesslist){
          if(business.getID() == id){
              return business;
          }  
        }
        return null;
    }

    @Override
    public boolean updateBusiness(Business business) {
        return false;
    }

    @Override
    public boolean deleteBusiness(Business business) {
        return false;
    }

    @Override
    public void addBusiness(Business business) {
        businesslist.add(business);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public Business getBusinessxy(double longitude, double latitude) {
        for(Business business : businesslist){
          if(business.getLongitude() == longitude && business.getLatitude() == latitude){
              return business;
          }  
        }
        return null;
    }


    @Override
    public Business getBusinessAddress(String address) {
        for(Business business : businesslist){
          if(business.getAddress().equals(address)){
              return business;
          }  
        }
        return null;
    }
}
