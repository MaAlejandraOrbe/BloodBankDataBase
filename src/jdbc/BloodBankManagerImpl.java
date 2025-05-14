package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bloodbank.db.pojos.BloodBank;
import bloodbank.db.pojos.Donation;
import bloodbank.db.pojos.BloodRequest;
import bloodbank.ifaces.BloodBankManager;

public class BloodBankManagerImpl implements BloodBankManager {

    private Connection c;

    public BloodBankManagerImpl(Connection c) {
        this.c = c;
    }

    @Override
    public void newBloodBank(BloodBank bloodbank) {
        try {
            String sql = "INSERT INTO BloodBank (name, address, city, contact_number, person_responsible, capacity_stock) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            p.setString(1, bloodbank.getName());
            p.setString(2, bloodbank.getAddress());
            p.setString(3, bloodbank.getCity());
            p.setString(4, bloodbank.getContact_number());
            p.setString(5, bloodbank.getPerson_responsible());
            //TODO add capacity stock to bloodbank
            p.setInt(6, bloodbank.getCapacity_stock());
            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            System.out.println("Database error with new BloodBank.");
            e.printStackTrace();
        }
    }

    @Override
    public void linkDonation(BloodBank bloodbank, Donation donation) {
        
    }

    @Override
    //TODO hay un error que no entiendo
    public void deleteBloodBank(int id) {
        try {
            String sql = "DELETE FROM BloodBank WHERE ID = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, id);
            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            System.out.println("Database error in deleteBloodBank.");
            e.printStackTrace();
        }
    }

    @Override
    public List<BloodBank> searchBloodBank(String name, String city) {
        List<BloodBank> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM BloodBank WHERE name LIKE ? AND city LIKE ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, name);
            p.setString(2, city);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Integer id           = rs.getInt("ID");
                String namebb           = rs.getString("name");
                String address          = rs.getString("address");
                String citybb           = rs.getString("city");
                String contactNumber   = rs.getString("contact_number");
                String personResponsible= rs.getString("person_responsible");
                Integer capacityStock= rs.getInt("capacity_stock");
                
                BloodBank bb = new BloodBank(id, namebb, address ,citybb ,contactNumber,personResponsible,capacityStock);
                list.add(bb);
            } 
            rs.close();
            p.close();
            
        } catch (SQLException e) {
            System.out.println("Database error in searchBloodBank.");
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void linkBloodRequest(BloodBank bloodbank, BloodRequest bloodrequest) {
        
    }}