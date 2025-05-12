package bloodbank.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import bloodbank.db.pojos.*;
import bloodbank.ifaces.*;
import jdbc.*;

public class Menu {
	
	private static BloodBankManager bbManager;
	private static BufferedReader reader;
	

	public static void main(String[] args) throws IOException {
		
		bbManager = new BloodBankManagerImpl();
		reader=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Welcome to the Bloodbank Management Solution");
		System.out.println("Please choose the option you want by typing its number");
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
