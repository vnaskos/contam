package com.bugbusters.contam.orm.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessServiceImpl implements BusinessService {

    private final static Integer RADIUS = 50; //km
    private final static Integer LAT_LON_DEGREES_IN_KM = 111;

    private BusinessRepo businessRepo;

    @Override
    @Transactional
    public List<BusinessDTO> searchNearbyBusinesses(final String keyword, final Double latitude, final Double longitude) {
        final Double angleRadius = Math.abs(RADIUS / (LAT_LON_DEGREES_IN_KM * Math.cos(latitude)));
        final Double minLatitude = latitude - angleRadius;
        final Double maxLatitude = latitude + angleRadius;
        final Double minLongitude = longitude - angleRadius;
        final Double maxLongitude = longitude + angleRadius;

        List<BusinessDTO> businessesWithinBounds = businessRepo.findWithinBounds(
                keyword, minLatitude, maxLatitude, minLongitude, maxLongitude);

        businessesWithinBounds = businessesWithinBounds.stream()
                .filter(business -> getDistanceBetweenGeoPoints(latitude, longitude, business.getLatitude(), business.getLongitude()) <= RADIUS)
                .collect(Collectors.toList());

        return businessesWithinBounds;
    }

    private Double getDistanceBetweenGeoPoints(final Double latitude1, final Double longitude1,
                                               final Double latitude2, final Double longitude2) {
        final Double theta = longitude1 - longitude2;

        Double distance = Math.sin(Math.toRadians(latitude1)) * Math.sin(Math.toRadians(latitude2)) +
                (Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) * Math.cos(Math.toRadians(theta)));
        distance = Math.toDegrees(Math.acos(distance)) * 60 * 1.1515;
        distance *= 1.609344; //km

        return (Math.round(distance * 100.0) / 100.0);
    }

    @Autowired
    public void setBusinessRepo(BusinessRepo businessRepo) {
        this.businessRepo = businessRepo;
    }
}
