package com.bugbusters.contam.location;

import java.io.File;
import java.io.IOException;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vavouraz
 */
public class LocationFinder {

    /**
     * Get the physical address which the IP belongs to
     * 
     * @param ipAddress public IP
     * @return 
     */
    public Location getLocation(String ipAddress) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
	File file = new File(loader.getResource("GeoLiteCity.dat").getFile());
        return getLocation(ipAddress, file);
    }

    /**
     * Find the physical address which IP belongs to
     * 
     * @param ipAddress public ip address
     * @param file geoip library's dataset
     * @return 
     */
    private Location getLocation(String ipAddress, File file) {
        Location location = null;

        try {
            location = new Location();

            LookupService lookup = new LookupService(file, LookupService.GEOIP_MEMORY_CACHE);
            com.maxmind.geoip.Location locationServices = lookup.getLocation(ipAddress);

            location.setCountryCode(locationServices.countryCode);
            location.setCountryName(locationServices.countryName);
            location.setRegion(locationServices.region);
            location.setRegionName(regionName.regionNameByCode(
                    locationServices.countryCode, locationServices.region));
            location.setCity(locationServices.city);
            location.setPostalCode(locationServices.postalCode);
            location.setLatitude(String.valueOf(locationServices.latitude));
            location.setLongitude(String.valueOf(locationServices.longitude));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return location;
    }
    
    /**
     * Find the public IP of the client using amazon service
     * 
     * @return client's public IP
     */
    public String getClientPublicIP() {
        try {
            URL connection = new URL("http://checkip.amazonaws.com/");
            URLConnection con = connection.openConnection();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String ip = reader.readLine();
            
            return ip;
        } catch (IOException ex) {
            Logger.getLogger(LocationFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
}
