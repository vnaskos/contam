package com.bugbusters.contam.orm.business;

import com.bugbusters.contam.helper.DBConnectionHelper;
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
    public List<BusinessDTO> getAllBusiness() {
        try {
            Connection conn = DBConnectionHelper.getConnection();
            
            String query = "SELECT * FROM business";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            List<BusinessDTO> businesses = new ArrayList<>();
            
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double longitude = rs.getDouble("longitude");
                double latitude = rs.getDouble("latitude");
                BusinessDTO b = new BusinessDTO(id, name, longitude, latitude);
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
    public BusinessDTO getBusinessById(int id) {
        try {
            Connection conn = DBConnectionHelper.getConnection();
            
            String query = "SELECT * FROM business WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            
            String name = rs.getString("name");
            double longitude = rs.getDouble("longitude");
            double latitude = rs.getDouble("latitude");
            BusinessDTO b = new BusinessDTO(id, name, longitude, latitude);
            
            stmt.close();
            conn.close();
            
            return b;
        } catch (SQLException ex) {
            Logger.getLogger(BusinessDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public void updateBusiness(BusinessDTO business) {
        try {
            Connection conn = DBConnectionHelper.getConnection();
            
            String query = "UPDATE business SET address = ?,description = ?,longitude = ?,latitude = ?,postalCode = ? WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, business.getAddress());
            stmt.setString(2, business.getDescription());
            stmt.setDouble(3, business.getLongitude());
            stmt.setDouble(4, business.getLatitude());
            stmt.setInt(5, business.getPostalCode());
            stmt.setString(6, business.getName());
            stmt.execute();
            
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BusinessDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteBusiness(BusinessDTO business) {
          try {
            Connection conn = DBConnectionHelper.getConnection();
            
            String query = "DELETE FROM business WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, business.getName());
            stmt.execute();
            
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BusinessDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addBusiness(BusinessDTO business) {
        try {
            Connection conn = DBConnectionHelper.getConnection();
            
            String query = "INSERT INTO business "
                    + "(name, latitude, longitude, description, address) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, business.getName());
            stmt.setDouble(2, business.getLatitude());
            stmt.setDouble(3, business.getLongitude());
            stmt.setString(4, business.getDescription());
            stmt.setString(5, business.getAddress());
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
    public List<BusinessDTO> getBusinessByXY(double lat, double lon) {
        try {
            Connection conn = DBConnectionHelper.getConnection();
            
            String query = "SELECT * FROM business WHERE acos(sin(?) * sin(latitude) + cos(?) * cos(latitude) * cos(longitude - (?))) * 6371 <= 1000";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, lat);
            stmt.setDouble(2, lat);
            stmt.setDouble(3, lon);
            ResultSet rs = stmt.executeQuery();
            
            List<BusinessDTO> businesses = new ArrayList<>();
            
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double longitude = rs.getDouble("longitude");
                double latitude = rs.getDouble("latitude");
                BusinessDTO b = new BusinessDTO(id, name, longitude, latitude);
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
    public List<BusinessDTO> searchBusiness(String keyword, double lat, double lon) {
        try {
            Connection conn = DBConnectionHelper.getConnection();

            String query = "SELECT * FROM business WHERE acos(sin(?) * sin(latitude) + cos(?) * cos(latitude) * cos(longitude - (?))) * 6371 <= 1000 AND levenshtein(name, ?) <= 4";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, lat);
            stmt.setDouble(2, lat);
            stmt.setDouble(3, lon);
            stmt.setString(4, keyword);

            ResultSet rs = stmt.executeQuery();

            List<BusinessDTO> businesses = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double longitude = rs.getDouble("longitude");
                double latitude = rs.getDouble("latitude");
                String address = rs.getString("address");
                String description = rs.getString("description");
                BusinessDTO b = new BusinessDTO(id, name, longitude, latitude);
                b.setAddress(address);
                b.setDescription(description);
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
    public List<BusinessDTO> getBusinessByAddress(String address) {
        return Collections.EMPTY_LIST;
    }
}
