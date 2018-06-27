package com.bugbusters.contam.orm.business;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessRepo extends CrudRepository<BusinessDTO, Long> {

    @Query("SELECT b FROM BusinessDTO b WHERE acos(sin(:latitude) * sin(b.latitude) + cos(:latitude) * cos(b.latitude) * cos(b.longitude - (:longitude))) * 6371 <= 1000 AND name = :keyword")
    List <BusinessDTO> find(
            @Param("keyword") String keyword,
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude);

}
