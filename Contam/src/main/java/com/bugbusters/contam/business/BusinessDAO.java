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
    
    public List<Business> searchBusiness(String keyword, double x, double y);

    public void updateBusiness(Business business);

    public void deleteBusiness(Business business);

    public void addBusiness(Business business);
}
