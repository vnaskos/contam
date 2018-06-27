package com.bugbusters.contam.orm.business;

import java.util.List;

/**
 *
 * @author Andreas
 */
public interface BusinessDAO extends MainDAO {

    public List<BusinessDTO> getAllBusiness();

    public BusinessDTO getBusinessById(int id);

    public List<BusinessDTO> getBusinessByXY(double longitude, double latitude);

    public List<BusinessDTO> getBusinessByAddress(String address);
    
    public List<BusinessDTO> searchBusiness(String keyword, double x, double y);

    public void updateBusiness(BusinessDTO business);

    public void deleteBusiness(BusinessDTO business);

    public void addBusiness(BusinessDTO business);
}
