package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import bloodbank.db.pojos.*;
import bloodbank.ifaces.BloodBankManager;

public class BloodBankManagerImpl implements BloodBankManager {

    private Connection c;

    public BloodBankManagerImpl(Connection c) {
        this.c = c;
    }
    
   
    
    
    public void insertBloodBankWorker(BloodBankWorker bloodbankWorker) {
    	try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO bloodbankWorker (name, phone, email) VALUES ('" + bloodbankWorker.getName() + "', "
					+ bloodbankWorker.getPhone() + ", '" + bloodbankWorker.getEmail() + "')";
			s.executeUpdate(sql);
			s.close();
		} catch (SQLException e) {
			System.out.println("Database exception.");
			e.printStackTrace();
		}
    }
    
    //TODO: REVISAR NOMBRE TABLE DATABASE
    public BloodBankWorker getBloodBankWorkerByEmail(String email) {
    	try {
			String sql = "SELECT * FROM bloodbankWorker WHERE email = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, email);
			ResultSet rs = p.executeQuery();
			rs.next();
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			Integer phone = rs.getInt("phone");
			BloodBankWorker bbw = new BloodBankWorker(id, name, phone, email);
			rs.close();
			p.close();
			return bbw;
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
    }
    
    
    public BloodBankWorker getBloodBankWorker(int id) {
    	try {
			String sql = "SELECT * FROM bloodbankWorker WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			rs.next();
			String name = rs.getString("name");
			Integer phone = rs.getInt("phone");
			String email = rs.getString("email");
			BloodBankWorker bbw = new BloodBankWorker(id, name, phone, email);
			rs.close();
			p.close();
			return bbw;
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
		return null;
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
            p.setInt(6, bloodbank.getCapacity_stock());
            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            System.out.println("Database error with new BloodBank.");
            e.printStackTrace();
        }
    }



    @Override
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
    
   public BloodBank getBloodBankByIdManager(int id) {
        try {
            String sql = "SELECT * FROM BloodBank WHERE ID = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            BloodBank b=null;
            if (rs.next()) {
            
                return new BloodBank(
                    rs.getInt("ID"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("contact_number"),
                    rs.getString("personResponsible"),
                    rs.getInt("current_stock")
                );
            }
            rs.close();
            p.close();
            return b;
        } catch (SQLException e) {
        	 System.out.println("Error retrieving BloodBank with ID " + id);
            e.printStackTrace();
        }
        return null;
    }

    public List<BloodBank> getAllBloodBanks() {
        List<BloodBank> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM BloodBank";
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            
           
    		
    		
       
            while (rs.next()) {
                BloodBank bb = new BloodBank(
                        rs.getInt("ID"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("contact_number"),
                        rs.getString("personResponsible"),
                        rs.getInt("current_stock")
                    );
                list.add(bb);
            }
            rs.close();
            p.close();
        } catch (SQLException e) {
            System.out.println("Database error in getAllBloodBanksManager.");
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public void updateBloodBank(BloodBank bloodbank) {
        try {
            String sql = "UPDATE BloodBank SET name = ?, address = ?, city = ?, contact_number = ?, person_responsible = ?, capacity_stock = ? WHERE ID = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, bloodbank.getName());
            p.setString(2, bloodbank.getAddress());
            p.setString(3, bloodbank.getCity());
            p.setString(4,bloodbank.getContact_number());
            p.setString(5, bloodbank.getPerson_responsible());
            p.setInt(6, bloodbank.getCapacity_stock());
            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            System.out.println("Database error in updateBloodBank.");
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteDonation(int id) {
        try {
            String sql = "DELETE FROM Donations WHERE ID = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, id);
            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            System.out.println("Database error in deleteDonation.");
            e.printStackTrace();
        }
    }
}

    