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
public interface BusinessDAO extends MainDAO {

    public List<Business> getAllBusiness();

    public Business getBusinessById(int id);

    public List<Business> getBusinessByXY(double longitude, double latitude);

    public List<Business> getBusinessByAddress(String address);

    public void updateBusiness(Business business);

    public void deleteBusiness(Business business);

    public void addBusiness(Business business);
}
