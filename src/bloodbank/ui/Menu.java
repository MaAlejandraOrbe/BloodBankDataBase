package bloodbank.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;

import bloodank.db.pojos.*;
import managers.BloodBankManager;

public class Menu {
	
	private static BloodBankManager bbManager;
	private static BufferedReader reader;
	

	public static void main(String[] args) {
		
		bbManager=null;
		reader=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("");
		
		
	}
	
	//esto es para probar
	BloodBank bloodbank = new BloodBank(12,"b","n",12345);
	LocalDate date1 = LocalDate.of(1999,12,3);
	LocalDate date2 = LocalDate.of(2013,4,5);
	Donation donation = new Donation(3,4,"status",date1,date2);
	
	//donation.setBloodbank(bloodbank);
	//da error en el .
	
	
}
}
