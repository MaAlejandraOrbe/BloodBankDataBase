package bloodbank.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import bloodank.db.pojos.*;
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
		System.out.println("Address");
		String address = reader.readLine();
		System.out.println("City");
		String city = reader.readLine();
		System.out.println("Contact number");
		Integer contact_number = Integer.parseInt(reader.readLine());
		
		BloodBank bloodbank = new BloodBank(address,city,contact_number);
		
		//call the bloodbankmanager
		bbManager.insertBloodBank(bloodbank);
		
	}

	private static void searchBloodBank() {
		// TODO Auto-generated method stub
		
	}
	
	private static void modifyBloodBank() {
		// TODO Auto-generated method stub
		
	}
		
	//esto es para probar
	BloodBank bloodbank = new BloodBank(12,"b","n",12345);
	LocalDate date1 = LocalDate.of(1999,12,3);
	LocalDate date2 = LocalDate.of(2013,4,5);
	Donation donation = new Donation(3,4,"status",date1,date2);
	
	//donation.setBloodbank(bloodbank);
	//da error en el .
	
	


	


	
}
