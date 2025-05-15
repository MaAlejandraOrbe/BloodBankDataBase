package bloodbank.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
	
		while (true) {
			try {
				System.out.println("Welcome to the BloodBank management solution");//TODO cambiar nombre
				System.out.println("Choose an option, please:");
				System.out.println("1. Enter as a donor");
				System.out.println("2. Enter as a recipient");
				System.out.println("3. Enter as a worker");
				System.out.println("0. Exit");

				int choice = Integer.parseInt(reader.readLine());

				switch (choice) {
				case 1: {
					newDonor();
					break;
				}
				case 2: {
					newRecipient();
					break;
				}
				case 3: {
					//();
					break;
				}
				case 0: {
					connectionManager.closeConnection();
					return;
				}
				}

			} catch (NumberFormatException e) {
				System.out.println("You didn't type a number!");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("I/O Exception.");
				e.printStackTrace();
			}
		}

		private static void newDonor() {
			/*System.out.println("Please, input the dono data:");
			System.out.println("Name:");
			String name = r.readLine();
			System.out.println("Phone:");
			Integer phone = Integer.parseInt(r.readLine());
			System.out.println("Email:");
			String email = r.readLine();
			System.out.println("Username:");
			String username = r.readLine();
			System.out.println("Password:");
			String password = r.readLine();
			Owner o = new Owner(name, phone, email);
			ownerMan.insertOwner(o);
			User u = new User(username, password, email);
			userMan.register(u);
			Role r = userMan.getRole("owner");
			userMan.assignRole(u, r);
			*/
		}
		private static void newRecipient() {
			//TODO
		}
		
	
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
					createBloodBank();
					break;
				}
				case 2:{
					searchBloodBank();
					break;
				}
				case 3:{
					deleteBloodBank();
					break;
				}
				case 0:{
					return;
				}
				
			}
			
			}
			
			}
		}
		

	//TODO ns que poner en los parentesis
	private static void createBloodBank() throws IOException {
		System.out.println("Please input the bloodbank's data:");
		System.out.println("Name:");
		String name = reader.readLine();
		System.out.println("Address:");
		String address = reader.readLine();
		System.out.println("City:");
		String city = reader.readLine();
		System.out.println("Contact number:");
		String contact_number = reader.readLine();
		
		BloodBank bloodbank = new BloodBank(name, address, city, contact_number);
		
		bbManager.newBloodBank(bloodbank);
		
	}

	//TODO ns si esta bien
	private static void searchBloodBank(String name, String city) throws IOException {
		System.out.println("Input the name and city of the bloodbank you want to search for:");
		//TODO entender su codigo. List<BloodBank> listbloodbank = bbManager.searchBloodBank(name, city);
		System.out.println("Name:");
		name = reader.readLine();
		System.out.println("City:");
		city = reader.readLine();
		
		bbManager.searchBloodBank(name, city);
		
		
	}
	
	private static void deleteBloodBank(int id) {
		bbManager.deleteBloodBank(id);
		System.out.println("The bloodbank has been deleted.");
		
	}
	
	
}
