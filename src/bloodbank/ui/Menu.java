package bloodbank.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;

import bloodbank.db.pojos.*;
import bloodbank.ifaces.*;
import jdbc.*;

public class Menu {
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private static BloodBankManager bbManager;
	private static DonationsManager donationsManager;
	private static RequestsManager requestsManager;
	
	//TODO una linea de JPA
	
	//TODO private static XMLManager xmlManager = new XMLManagerImpl();

	public static void main(String[] args) throws IOException {
		
		ConnectionManager connectionManager = new ConnectionManager();
		bbManager = new BloodBankManagerImpl(connectionManager.getConnection());
		donationsManager = new DonationsManagerImpl(connectionManager.getConnection());
		requestsManager = new RequestsManagerImpl(connectionManager.getConnection());
		//algo de JPA
	
		while(true) {
			try {
				System.out.println("Welcome to the Bloodbank Management Solution"); //TODO cambiar nombre
				System.out.println("Choose an option please:");
				System.out.println("1. Create a new Bloodbank");
				System.out.println("2. Search for a Bloodbank ");
				System.out.println("3. Modify a Bloodbank");
				System.out.println("0. Exit ");
				int choice = Integer.parseInt(reader.readLine());
				
				switch(choice) {
				case 1: {
					//create a bloodbank
					createBloodBank();
					break;
				}
				case 2:{
					//search for a bloodbank
					searchBloodBank();
					break;
				}
				case 3:{
					//modify a bloodbank
					modifyBloodBank();
					break;
				}
				case 0:{
					return;
				}
				
			}
			
			}
			
			}
		}
		
		
	

	private static void createBloodBank() throws IOException {
		System.out.println("Please input the bloodbank's data");
		System.out.println("Name");
		String name = reader.readLine();
		System.out.println("Address");
		String address = reader.readLine();
		System.out.println("City");
		String city = reader.readLine();
		System.out.println("Contact number");
		String contact_number = reader.readLine();
		
		BloodBank bloodbank = new BloodBank(name, address, city, contact_number);
		
		//call the bloodbankmanager
		bbManager.newBloodBank(bloodbank);
		
	}

	private static void searchBloodBank() {
		// TODO Auto-generated method stub
		
	}
	
	private static void modifyBloodBank() {
		// TODO Auto-generated method stub
		
	}
	
	
}
