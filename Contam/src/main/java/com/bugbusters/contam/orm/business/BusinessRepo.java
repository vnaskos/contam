package com.bugbusters.contam.orm.business;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessRepo extends CrudRepository<BusinessDTO, Long> {

    /**
     * Search for a list of businesses relevant to the given keyword and close to the provided location
     *
     * TODO: the query is not working, for testing purposes it was replaced by a "findAll"
     *
     * @param keyword what are you searching for
     * @param latitude latitude
     * @param longitude longitude
     * @return List of businesses near the provided location
     */
//    @Query("SELECT b FROM BusinessDTO b WHERE acos(sin(:latitude) * sin(b.latitude) + cos(:latitude) * cos(b.latitude) * cos(b.longitude - (:longitude))) * 6371 <= 1000 AND name = :keyword")
    @Query("SELECT b FROM BusinessDTO b")
    List <BusinessDTO> find(
            @Param("keyword") String keyword,
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude);

}
