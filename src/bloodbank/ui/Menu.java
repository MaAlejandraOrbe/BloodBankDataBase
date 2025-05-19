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
				System.out.println("1. Register as blood bank manager.");
				System.out.println("2. Register as hospital worker.");
				System.out.println("3. Login.");
				System.out.println("0. Exit");

				int choice = Integer.parseInt(reader.readLine());

				switch (choice) {
				case 1: {
					//TODO
					break;
				}
				case 2: {
					//TODO: IF THEY CHOOSE NUMBER 2, THEN THEY WILL HAVE
					//OTHER MENU TO CHOOSE RECIPIENT MANAGER OR DONATION MANAGER
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
	
	public static void login() throws IOException {
		while(true) {
			System.out.println("Username: ");
			String username=reader.readLine();
			System.out.println("Password: ");
			String password=reader.readLine();
			
			User user =userManager.login(username, password);
			if(user!=null) {
				
				if(user.getRole().getName().equals("bloodbankManager")){
					//bloodBankManagerMenu(user.getEmail());
					
				}else if(user.getRole().getName().equals("donationsWorker")) {
					donationsWorkerMenu(user.getEmail());
					
				}else if(user.getRole().getName().equals("requestsWorker")) {
					//requestsWorkerrMenu(user.getEmail());
				
			}
			else {
				System.out.println("Wrong username/password combination.");
			}

			}}
		
	}

		
		public static void hospitalWorkerMenu() throws IOException {
			
			
			while(true) {
				try {
					System.out.println("Welcome to hospital management: ");
					System.out.println("Choose an option, please: ");
					System.out.println("1. Register as donations worker.");
					System.out.println("2. Register as requests worker.");
					System.out.println("3. Login.");
					//TODO:  I THINK HERE OF THEY DONT CHOOSE 1-3 IT SHOULD BE GO BACK
					//TO THE FIRST MENU
					System.out.println("0. Exit.");
					
					int hospChoice=Integer.parseInt(reader.readLine());
					switch(hospChoice) {
					case 1: {
						
						registerDonationsWorker();
						break;
						
						
					}
					
					case 2:{
						
						//registerRequestsmanager();
						
						break;}
					case 3:{
						//
					}
					case 0:{
						//
					}	
			}
		}catch(NumberFormatException e) {
			System.out.println("You did not choose an option!");
			e.printStackTrace();
		}
			
			}
	}

		private static void registerDonationsWorker() throws IOException {
			System.out.println("Please, input the donations worker data: ");
			System.out.println("Name: ");
			String name=reader.readLine();
			System.out.println("Phone: ");
			Integer phone=Integer.parseInt(reader.readLine());
			System.out.println("Email: ");
			String email=reader.readLine();
			System.out.println("Username: ");
			String username=reader.readLine();
			System.out.println("Password: ");
			String password=reader.readLine();
			
			DonationsWorker dW=new DonationsWorker(name,phone,email);
			donationsManager.insertDonationsWorker(dW);
			User u=new User(username,password,email);
			userManager.register(u);
			Role r=userManager.getRole("donationsWorker");
			userManager.assignRole(u, r);
			
		}
		
		private static void registerRequestsWorker() throws IOException {
			System.out.println("Please, input the donations worker data: ");
			System.out.println("Name: ");
			String name=reader.readLine();
			System.out.println("Phone: ");
			Integer phone=Integer.parseInt(reader.readLine());
			System.out.println("Email: ");
			String email=reader.readLine();
			System.out.println("Username: ");
			String username=reader.readLine();
			System.out.println("Password: ");
			String password=reader.readLine();
			
			RequestsWorker rW=new RequestsWorker(name,phone,email);
			//requestsManager
			/*DonationsWorker dW=new DonationsWorker(name,phone,email);
			donationsManager.insertDonationsWorker(dW);
			User u=new User(username,password,email);
			userManager.register(u);
			Role r=userManager.getRole("donationsWorker");
			userManager.assignRole(u, r);*/
			
		}
		

		private static void donationsWorkerMenu(String email) throws NumberFormatException, IOException {
			
			DonationsWorker dw=donationsManager.getDonationsWorkerByEmail(email);
			
			while(true) {
				
			try {
				System.out.println("Welcome Donations Worker, choose what do you want to do");
				System.out.println("1.Create donation");
				System.out.println("2.Create donor");
				System.out.println("3.Search donor");
				System.out.println("4.Search donation");
				System.out.println("5.Update donor");
				System.out.println("6.Update donation");
				System.out.println("0.Back to main menu");
				int choice=Integer.parseInt(reader.readLine());
			
			
			switch(choice) {
			case 1:
				createDonation(); //INCOMPLETE, IDK HOW
				break;
			case 2: 
				createDonor(dw.getId());
				break;
			case 3:
				
				//TODO: THESE OF SEARCH I THINK THEY ARE CORRECT WITHOUT PARAMETERS
				searchDonor();
				break;
			case 4:
				searchDonation();
				break;
			case 5:
				updateDonor(dw.getId());
			case 6:
				updateDonation(dw.getId());
				break;
			case 0:
				return;
			
			
			}
			
		}
			catch (NumberFormatException e) {
				System.out.println("You didn't type a number!");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("I/O Exception.");
				e.printStackTrace();
			}
		}
	}



		
		

		

		//TODO: IDK HOW TO MANAGE THE BLOODBANK AND DONOR ID, ???/ (MALE)
		private static void createDonation() {
			System.out.println("Please, input the donation info: ");
			System.out.println("");;
			
		}
		
		private static void createDonor(int id) throws IOException {
			System.out.println("Please, input the Donor info:");
	        System.out.print("First name: ");
	        String firstName = reader.readLine();
	        System.out.print("Last name: ");
	        String lastName = reader.readLine();
	        System.out.print("DOB (YYYY-MM-DD): ");
	        String dob = reader.readLine();
	        Date dobDate;
	        try {
	        	dobDate=Date.valueOf(dob);
	        }catch(IllegalArgumentException ia) {
	        	System.out.println("Invalid date format. Use YYY-MM-DD");
	        	return;
	        }
	        System.out.print("Blood type: ");
	        String bloodType = reader.readLine();
	        System.out.print("Eligible to donate? (1 = yes, 0 = no): ");
	        boolean eligible = reader.readLine().trim().equals("1");
	        System.out.print("Country: ");
	        String country = reader.readLine();
	        System.out.print("Contact number: ");
	        String contact = reader.readLine();
	        System.out.print("Emergency contact number: ");
	        String emergency = reader.readLine();
	        
	        DonationsWorker dw=donationsManager.getDonationsWorker(id);
	        Donor donor=new Donor(firstName,lastName,dobDate,bloodType,country,eligible,contact,emergency,dw);
	        donationsManager.newDonor(donor);
	        System.out.println("New donor registerd correctly!");	
		}
		
		
		private static void searchDonor() throws IOException {
			System.out.println(" Search Donor ");
			System.out.println("Enter first name: ");
			String firstName=reader.readLine();
			System.out.println("Enter last name: ");
			String lastName=reader.readLine();
			System.out.print(" Enter date of birth (YYYY-MM-DD): ");
	        String dob = reader.readLine();
	        Date dobDate;
	        try {
	        	dobDate=Date.valueOf(dob);
	        }catch(IllegalArgumentException ia) {
	        	System.out.println("Invalid date format. Use YYY-MM-DD");
	        	return;
	        }
	        
	        List<Donor> results =donationsManager.searchDonor(firstName,lastName,dobDate);
	        
	        if(results.isEmpty()) {
	        	System.out.println("No donors found matching the information given.");
	        	
	        }else {
	        	System.out.println("Donor found !");
	        	for(Donor d:results) {
	        		System.out.println(d);
	        	}
	        }
	    }
		
		private static void searchDonation() throws IOException {
			System.out.println(" Search Donation ");
			System.out.print("Enter status: ");
			String status=reader.readLine().trim();
			
			List<Donation>donations=donationsManager.searchDonation(status);
			
			if(donations.isEmpty()) {
				System.out.println("No donors found with matching status:  "+status);
			}else {
				System.out.println(" Donation found !");
				for(Donation d:donations) {
					System.out.println(d);
				}
			}
		}
		
		
		private static void updateDonor(int id) throws IOException {
			Donor donor=donationsManager.getDonorById(id);
			System.out.println("Type the new data, or press enter to keep actual data");
			System.out.println("First name (" + donor.getFirst_name() + "):");
			String firstname = reader.readLine();
			if (!firstname.equals("")) {
				donor.setFirst_name(firstname);
			}
			System.out.println("Last name (" + donor.getLast_name() + "):");
			String lastname = reader.readLine();
			if (!lastname.equals("")) {
				donor.setLast_name(lastname);
			}
			
		    System.out.println("Blood type (" + donor.getBlood_type() + "):");
		    String bloodType = reader.readLine();
		    if (!bloodType.equals("")) {
		        donor.setBlood_type(bloodType);
		    }

		    System.out.println("Country (" + donor.getCountry() + "):");
		    String country = reader.readLine();
		    if (!country.equals("")) {
		        donor.setCountry(country);
		    }

		    System.out.println("Eligible to donate? (" + donor.getEligible_donate() + ") [true/false]:");
		    String eligible = reader.readLine();
		    if (!eligible.equals("")) {
		        donor.setEligible_donate(Boolean.parseBoolean(eligible));
		    }

		    System.out.println("Contact number (" + donor.getContact_number() + "):");
		    String contactNumber = reader.readLine();
		    if (!contactNumber.equals("")) {
		        donor.setContact_number(contactNumber);
		    }

		    System.out.println("Emergency contact number (" + donor.getEmergency_contact_number() + "):");
		    String emergencyContact = reader.readLine();
		    if (!emergencyContact.equals("")) {
		        donor.setEmergency_contact_number(emergencyContact);
		    }

			donationsManager.updateDonor(donor);
			System.out.println("Donor updated successfully.");
		}
			
		private static void updateDonation(int id) throws IOException {
			 Donation donation = donationsManager.getDonationById(id);
			    System.out.println("Type the new data, or press enter to keep actual data");

			    System.out.println("Status (" + donation.getStatus() + "):");
			    String status = reader.readLine();
			    if (!status.equals("")) {
			        donation.setStatus(status);
			    }

			    System.out.println("Donation date (" + donation.getDonation_date() + ") [yyyy-mm-dd]:");
			    String dateDonationStr = reader.readLine();
			    if (!dateDonationStr.equals("")) {
			        try {
			            donation.setDonation_date(Date.valueOf(dateDonationStr));
			        } catch (IllegalArgumentException e) {
			            System.out.println("Invalid date format. Skipping update of donation date.");
			        }
			    }

			    System.out.println("Quantity (" + donation.getQuantity() + "):");
			    String quantityStr = reader.readLine();
			    if (!quantityStr.equals("")) {
			        try {
			            donation.setQuantity(Integer.parseInt(quantityStr));
			        } catch (NumberFormatException e) {
			            System.out.println("Invalid number. Skipping update of quantity.");
			        }
			    }

			    System.out.println("Expiration date (" + donation.getExpiration_date() + ") [yyyy-mm-dd]:");
			    String expirationDateStr = reader.readLine();
			    if (!expirationDateStr.equals("")) {
			        try {
			            donation.setExpiration_date(Date.valueOf(expirationDateStr));
			        } catch (IllegalArgumentException e) {
			            System.out.println("Invalid date format. Skipping update of expiration date.");
			        }
			    }

			    donationsManager.updateDonation(donation);
			    System.out.println("Donation updated successfully.");
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
	
	
}
