/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bugbusters.contam.location;

import java.io.File;
import java.io.IOException;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;

/**
 *
 * @author Vavouraz
 */
public class FindMyLocation {
    
    public static void main(String[] args) {
	FindMyLocation obj = new FindMyLocation();
	ServerLocation location = obj.getLocation("2.85.56.245"); //put the IP you see at Ip.java run result
	System.out.println(location);
  }

  public ServerLocation getLocation(String ipAddress) {

	File file = new File(
	    "C:\\Users\\Vavouraz\\Desktop\\GeoLiteCity.dat"); //put the location of GeoLiteCity.dat file
	return getLocation(ipAddress, file);

  }

  public ServerLocation getLocation(String ipAddress, File file) {

	ServerLocation serverLocation = null;

	try {

	serverLocation = new ServerLocation();

	LookupService lookup = new LookupService(file,LookupService.GEOIP_MEMORY_CACHE);
	Location locationServices = lookup.getLocation(ipAddress);

	serverLocation.setCountryCode(locationServices.countryCode);
	serverLocation.setCountryName(locationServices.countryName);
	serverLocation.setRegion(locationServices.region);
	serverLocation.setRegionName(regionName.regionNameByCode(
             locationServices.countryCode, locationServices.region));
	serverLocation.setCity(locationServices.city);
	serverLocation.setPostalCode(locationServices.postalCode);
	serverLocation.setLatitude(String.valueOf(locationServices.latitude));
	serverLocation.setLongitude(String.valueOf(locationServices.longitude));

	} catch (IOException e) {
		System.err.println(e.getMessage());
	}

	return serverLocation;

  }
}
