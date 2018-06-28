package com.bugbusters.contam.orm.business;

import java.util.List;

public interface BusinessService {

    List<BusinessDTO> searchNearbyBusinesses(String keyword, Double currentLatitude, Double currentLongitude);

}
