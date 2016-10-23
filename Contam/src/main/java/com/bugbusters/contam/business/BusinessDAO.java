/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bugbusters.contam.business;

import java.util.List;

/**
 *
 * @author Andreas
 */
public interface BusinessDAO extends MainDAO{
    public List<Business> getAllBusiness();
    public Business getBusiness(int id);
    public Business getBusinessxy(double longitude,double latitude);
    public Business getBusinessAddress(String address);
    public boolean updateBusiness(Business business);
    public boolean deleteBusiness(Business business);
    public void addBusiness(Business business);
}
