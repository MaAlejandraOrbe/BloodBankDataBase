package bloodbank.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;

import bloodbank.db.pojos.*;
import bloodbank.ifaces.*;
import jdbc.*;
import jpa.*;

public class Menu {
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private static BloodBankManager bbManager;
	private static DonationsManager donationsManager;
	private static RequestsManager requestsManager;
	
	private static UserManager userManager;
	
	
	//TODO una linea de JPA
	
	//TODO private static XMLManager xmlManager = new XMLManagerImpl();

	public static void main(String[] args) throws IOException {
		
		ConnectionManager connectionManager = new ConnectionManager();
		
		bbManager = new BloodBankManagerImpl(connectionManager.getConnection());
		donationsManager = new DonationsManagerImpl(connectionManager.getConnection());
		requestsManager = new RequestsManagerImpl(connectionManager.getConnection());
		
		userManager=new JPAUserManager();
		
	
		while (true) {
			try {
				System.out.println("Welcome to the BloodBank management solution");//TODO cambiar nombre
				System.out.println("Choose an option, please:");
				System.out.println("1. Register as a donor");
				System.out.println("2. Register as a recipient");
				//TODO: CONFIRMAR ESTO ES LOGIN
				System.out.println("3. Enter as a worker (login)");
				System.out.println("0. Exit");

				int choice = Integer.parseInt(reader.readLine());

				switch (choice) {
				case 1: {
					registerDonor();
					break;
				}
				case 2: {
					registerRecipient();
					break;
				}
				case 3: {
					//login();
					break;
				}
				case 0: {
					connectionManager.closeConnection();
					userManager.close();
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
	}

		public static void registerDonor() throws IOException {
			System.out.println("Please, input the Donor info:");
	        System.out.print("First name: ");
	        String firstName = reader.readLine();
	        System.out.print("Last name: ");
	        String lastName = reader.readLine();
	        
	        System.out.print("DOB (YYYY-MM-DD): ");
	        String dob = reader.readLine();
	        
	        //TODO: COMPROBAR ESTO DE DOB Y DATE, QUE SEA ASI
	        Date dobDate=Date.valueOf(dob);
	        
	        System.out.print("Blood type: ");
	        String bloodType = reader.readLine();
	        
	        //AQUI USO ESTO PARA QUE SEA BOOLEAN, COMO EL TIPO QUE HEMOS PUESTO EN EL POJO.
	        System.out.print("Eligible to donate? (1 = yes, 0 = no): ");
	        boolean eligible = reader.readLine().trim().equals("1");
	        
	        
	        System.out.print("Country: ");
	        String country = reader.readLine();
	        System.out.print("Contact number: ");
	        String contact = reader.readLine();
	        System.out.print("Emergency contact number: ");
	        String emergency = reader.readLine();
	        
	        
	        //TODO: I CREATED AND OBJECT WITH ID NULL BECASE THE USER WONT PUT IT,
	        //WHAT IS BETTER CREATE IT LIKE THAT OR SHOULD I HAVE A CONSTRUCTOR
	        //IN DONOR WITHOUT ID?
	        
	        
	        Donor donor=new Donor(null,firstName,lastName,dobDate,bloodType,country,eligible,contact,emergency);
	        donationsManager.newDonor(donor);
	        
	        System.out.println("New donor registerd correctly!");
			
		}
		
		public static void registerRecipient() throws IOException {
			System.out.println("Please, input the Recipient info:");
            System.out.print("First name: ");
            String firstName = reader.readLine();
            System.out.print("Last name: ");
            String lastName = reader.readLine();
            System.out.print("DOB (YYYY-MM-DD): ");
	        String dob = reader.readLine();
	        
	        //TODO: COMPROBAR ESTO DE DOB Y DATE, QUE SEA ASI
	        Date dobDate=Date.valueOf(dob);
	        
	        System.out.print("Blood type: ");
	        String bloodType = reader.readLine();
	        
	       
	        
            System.out.print("Country: ");
            String country = reader.readLine();
            System.out.print("Contact number: ");
            String contact = reader.readLine();
            System.out.print("Emergency contact number: ");
            String emergency = reader.readLine();

	        //TODO: I CREATED AND OBJECT WITH ID NULL BECASE THE USER WONT PUT IT,
	        //WHAT IS BETTER CREATE IT LIKE THAT OR SHOULD I HAVE A CONSTRUCTOR
	        //IN DONOR WITHOUT ID?
	        
	        Recipient recipient=new Recipient(null, firstName,lastName,dobDate,bloodType,country,contact,emergency);
	        requestsManager.newRecipient(recipient);
	       
	        System.out.println("New recipient registerd correctly!");
			
		}
		
		
		public static void login() throws IOException {
			while(true) {
				System.out.println("Username: ");
				String username=reader.readLine();
				System.out.println("Password: ");
				String password=reader.readLine();
				
				User user =userManager.login(username, password);
				if(user!=null) {
					
					//TODO: CONFIRM THE ROLES ARE THAT, WE HAVE DONOR AND RECIEPIENT, I THINK ANOTHER ROLE IS A BBWORKER AND A HOSPITALWORKER
					if(user.getRole().getName().equals("donor")){
						//TODO: I THINK IT WILL BE BBMANAGER, WHEN IN DOGCLINIC IS OWNER. CONFIRM THISSSSSS!!!!
						// codeeee
					}else if(user.getRole().getName().equals("recipient"));
					//codeeee
				}
				else {
					System.out.println("Wrong username/password combination.");
				}

				}
			
		}
	
		

		
		
	/*public static void bloodbankMenu() {
		
		
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
	}*/
		
		
	
		
		

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
	
	public static void donationMenu(int id) {
		/*
    public void newDonation(Donation Donation);
	public List<Donation> searchDonation(String status);
	public void updateDonation(Donation donation);
		 * */
		while(true) {
		try {
			System.out.println("What do you want to do with the donation?: ");
			System.out.println("1. Search donation");
			System.out.println("2. Update donation");
				
			//TODO: COMPROBAR QUE SEA A ESE MENU.
			System.out.println("0.Back to BloodBank menu");
				
			int choice=Integer.parseInt(reader.readLine());
			
			switch(choice) {
			
			case 1:
				//
				
				break;
				
			case 2:
				//updateDonation(id);
				
				
			case 0:
				return;
			}
		
		
		} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/*private static void updateDonation(int id) {
		Donation d=donationsManager.getDonorById(id);
		
		
	}*/
	
	
}
