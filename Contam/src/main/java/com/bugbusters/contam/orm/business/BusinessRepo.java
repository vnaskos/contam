package com.bugbusters.contam.orm.business;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessRepo extends CrudRepository<BusinessDTO, Long> {

    @Query("SELECT b FROM BusinessDTO b " +
            "WHERE b.latitude BETWEEN :minLatitude AND :maxLatitude " +
            "AND b.longitude BETWEEN :minLongitude AND :maxLongitude " +
            "AND b.name LIKE CONCAT('%', :keyword, '%')")
    List<BusinessDTO> findWithinBounds(@Param("keyword") String keyword,
                                       @Param("minLatitude") Double minLatitude,
                                       @Param("maxLatitude") Double maxLatitude,
                                       @Param("minLongitude") Double minLongitude,
                                       @Param("maxLongitude") Double maxLongitude);

}
