package com.bugbusters.contam.controller;

import com.bugbusters.contam.location.Location;
import com.bugbusters.contam.location.LocationFinder;
import com.bugbusters.contam.orm.business.BusinessDTO;
import com.bugbusters.contam.orm.business.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private BusinessService businessService;

    private LocationFinder locationFinder;

    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam(value = "keyword", defaultValue = "") String keyword,
                         @RequestParam(value = "x", required = false) Double x,
                         @RequestParam(value = "y", required = false) Double y) {
        Double latitude = x;
        Double longitude = y;

        if(x == null || y == null) {
            String publicIp = locationFinder.getClientPublicIP();
            Location userLocation = locationFinder.getLocation(publicIp);

            latitude = Double.parseDouble(userLocation.getLatitude());
            longitude = Double.parseDouble(userLocation.getLongitude());
        }

        List<BusinessDTO> businesses = businessService.searchNearbyBusinesses(keyword, latitude, longitude);
        model.addAttribute("latitude", latitude);
        model.addAttribute("longitude", longitude);
        model.addAttribute("results", businesses);

        return "index";
    }

    @Autowired
    public void setBusinessService(BusinessService businessService) {
        this.businessService = businessService;
    }

    @Autowired
    public void setLocationFinder(LocationFinder locationFinder) {
        this.locationFinder = locationFinder;
    }
}
