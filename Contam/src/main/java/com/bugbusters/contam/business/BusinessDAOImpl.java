/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bugbusters.contam.business;

import com.bugbusters.contam.helper.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andreas
 */
public class BusinessDAOImpl implements BusinessDAO {

    @Override
    public List<Business> getAllBusiness() {
        try {
            Connection conn = DBHelper.getConnection();
            
            String query = "SELECT * FROM business";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            List<Business> businesses = new ArrayList<>();
            
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double longitude = rs.getDouble("longitude");
                double latitude = rs.getDouble("latitude");
                Business b = new Business(id, name, longitude, latitude);
                businesses.add(b);
            }
            
            stmt.close();
            conn.close();
            
            return businesses;
        } catch (SQLException ex) {
            Logger.getLogger(BusinessDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Collections.EMPTY_LIST;
    }

    @Override
    public Business getBusinessById(int id) {
        try {
            Connection conn = DBHelper.getConnection();
            
            String query = "SELECT * FROM business WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            
            String name = rs.getString("name");
            double longitude = rs.getDouble("longitude");
            double latitude = rs.getDouble("latitude");
            Business b = new Business(id, name, longitude, latitude);
            
            stmt.close();
            conn.close();
            
            return b;
        } catch (SQLException ex) {
            Logger.getLogger(BusinessDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public boolean updateBusiness(Business business) {
        return false;
    }

    @Override
    public boolean deleteBusiness(Business business) {
        return false;
    }

    @Override
    public void addBusiness(Business business) {
        try {
            Connection conn = DBHelper.getConnection();
            
            String query = "INSERT INTO business (name, longitude, latitude)"
                    + " VALUESResultSet rs =  ( ?, ?, ? )";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, business.getName());
            stmt.setDouble(2, business.getLongitude());
            stmt.setDouble(3, business.getLatitude());
            stmt.execute();
            
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BusinessDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public List<Business> getBusinessByXY(double lat, double lon) {
        try {
            Connection conn = DBHelper.getConnection();
            
            lat = (lat*Math.PI)/180;
            lon = (lon*Math.PI)/180;
            
            String query = "SELECT * FROM business WHERE acos(sin(?) * sin(latitude) + cos(?) * cos(latitude) * cos(longitude - (?))) * 6371 <= 1000;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, lat);
            stmt.setDouble(2, lat);
            stmt.setDouble(3, lon);
            ResultSet rs = stmt.executeQuery();
            
            List<Business> businesses = new ArrayList<>();
            
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double longitude = rs.getDouble("longitude");
                double latitude = rs.getDouble("latitude");
                Business b = new Business(id, name, longitude, latitude);
                businesses.add(b);
            }
            
            stmt.close();
            conn.close();
            
            return businesses;
        } catch (SQLException ex) {
            Logger.getLogger(BusinessDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Business> getBusinessByAddress(String address) {
        return Collections.EMPTY_LIST;
    }
}
