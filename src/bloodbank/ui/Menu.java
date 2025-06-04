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
	
	
	//TODO private static XMLManager xmlManager = new XMLManagerImpl();

	public static void main(String[] args) throws IOException {
		
		ConnectionManager connectionManager = new ConnectionManager();
		
		bbManager = new BloodBankManagerImpl(connectionManager.getConnection());
		donationsManager = new DonationsManagerImpl(connectionManager.getConnection());
		requestsManager = new RequestsManagerImpl(connectionManager.getConnection());
		userManager=new JPAUserManager();
		
	
		while (true) {
			try {
				System.out.println("\n\n\n\n\n\n\n\nWelcome to the BloodBank management solution");//TODO cambiar nombre
				System.out.println("\nChoose an option, please:");
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
					hospitalWorkerMenu();
					break;
				}
				case 3: {
					login();
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
			System.out.println("\nUsername: ");
			String username=reader.readLine();
			System.out.println("\nPassword: ");
			String password=reader.readLine();
			
			User user =userManager.login(username, password);
			if(user==null) {
				System.out.println("Wrong username/password. Try again.");
				continue;
			}
			
				
				if(user.getRole().getName().equals("bloodbankManager")){
					//bloodBankWorkerMenu(user.getEmail());
					break;
					
				}else if(user.getRole().getName().equals("donationsWorker")) {
					donationsWorkerMenu(user.getEmail());
					
					break;
				}else if(user.getRole().getName().equals("requestsWorker")) {
					requestsWorkerMenu(user.getEmail());
					break;
				
			}
			}
		
	}

		
		public static void hospitalWorkerMenu() throws IOException {
			
			
			while(true) {
				try {
					System.out.println("\nWelcome to hospital management: ");
					System.out.println("\nChoose an option, please: ");
					System.out.println("\n1. Register as donations worker.");
					System.out.println("\n2. Register as requests worker.");
					System.out.println("\n0. Back to main menu");
					
					int hospChoice=Integer.parseInt(reader.readLine());
					switch(hospChoice) {
					case 1: {
						registerDonationsWorker();
						
						break;	
					}
					
					case 2:{
						registerRequestsWorker();
						break;}
					case 0:{
						return;
					}
						
			}
		}catch(NumberFormatException e) {
			System.out.println("You did not choose an option!");
			e.printStackTrace();
		}
			
			}
	}

		private static void registerDonationsWorker() throws IOException {
			System.out.println("\nPlease, input the donations worker data: ");
			System.out.println("\nName: ");
			String name=reader.readLine();
			System.out.println("\nPhone: ");
			Integer phone=Integer.parseInt(reader.readLine());
			System.out.println("\nEmail: ");
			String email=reader.readLine();
			System.out.println("\nUsername: ");
			String username=reader.readLine();
			System.out.println("\nPassword: ");
			String password=reader.readLine();
			
			DonationsWorker dW=new DonationsWorker(name,phone,email);
			donationsManager.insertDonationsWorker(dW);
			User u=new User(username,password,email);
			userManager.register(u);
			Role r=userManager.getRole("donationsWorker");
			userManager.assignRole(u, r);
			
			donationsWorkerMenu( email);
			
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
			requestsManager.insertRequestsWorker(rW);
			User u=new User(username,password,email);
			userManager.register(u);
			Role r=userManager.getRole("requestsWorker");
			userManager.assignRole(u, r);
			
			
		}
		

		private static void donationsWorkerMenu(String email) throws NumberFormatException, IOException {
			
			DonationsWorker dw=donationsManager.getDonationsWorkerByEmail(email);
			
			while(true) {
				
			try {
				System.out.println("\nWelcome Donations Worker, choose what do you want to do");
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
				createDonation(dw.getId()); 
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
				break;
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
		private static void createDonation(int id) throws IOException {
			System.out.println("Please, input the donation info: ");
			System.out.println("Status: ");
			String status=reader.readLine();
			System.out.println("Donation date: ");
			String donationDate=reader.readLine();
			Date ddate;
			try {
				ddate=Date.valueOf(donationDate);
			}catch(IllegalArgumentException iae) {
				System.out.println("Invalid date format. Use YYYY-MM-DD");
				return;
			}
			System.out.println("Quantity: ");
			int  quantity=Integer.parseInt(reader.readLine());
			
			System.out.println("Expiration date of donation: ");
			String expirationDate=reader.readLine();
			Date edate;
			try {
				edate=Date.valueOf(expirationDate);
			}catch(IllegalArgumentException iae) {
				System.out.println("Invalid date format. Use YYYY-MM-DD");
				return;
			}
			
		    System.out.print("Insert blood bank ID: ");
		    int bbId = Integer.parseInt(reader.readLine());
		    BloodBank bloodBank = donationsManager.getBloodBankById(bbId);
		    if (bloodBank == null) {
		        System.out.println("Blood bank not found.");
		        return;
		    }
		    
			System.out.print("Insert donor ID: ");
		    int donorId = Integer.parseInt(reader.readLine());
		    Donor donor = donationsManager.getDonorById(donorId);
		    if (donor == null) {
		        System.out.println("Donor not found.");
		        return;
		    }

	
		
		    DonationsWorker dw=donationsManager.getDonationsWorker(id);
		    Donation donation=new Donation(status,ddate,quantity,edate,bloodBank,donor,dw);
		    /*public Donation(String status, Date donation_date, Integer quantity, Date expiration_date, BloodBank bloodbank,
		     */
	        donationsManager.newDonation(donation);
	        System.out.println("New donotion registerd correctly!");	
			
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
	        	System.out.println("Invalid date format. Use YYYY-MM-DD");
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
			
			System.out.println("List of all egisters donors: ");
			List<Donor>donors=donationsManager.getAllDonors();
			
			if(donors.isEmpty()) {
				System.out.println("No donors found in system.");
				return;
			}
			
	       
			
			for(Donor d:donors){
				System.out.println("ID: "+d.getId()+
						           " |First name:  "+d.getFirst_name()+
						           " |Last name: "+d.getLast_name()+
						           " |Status: "+d.getDOB());
			
				
			}
			
			
		    System.out.print("Enter the ID of the donor to update: ");
		    int donorId = Integer.parseInt(reader.readLine());
		    Donor donor = donationsManager.getDonorById(donorId);
		    if (donor == null) {
		        System.out.println("Donor not found.");
		        return;
		    }
			 System.out.println("Type the new data, or press enter to keep current values:");

			    System.out.print("First name (" + donor.getFirst_name() + "): ");
			    String fn = reader.readLine();
			    if (!fn.isEmpty()) donor.setFirst_name(fn);

			    System.out.print("Last name (" + donor.getLast_name() + "): ");
			    String ln = reader.readLine();
			    if (!ln.isEmpty()) donor.setLast_name(ln);

			    System.out.print("Blood type (" + donor.getBlood_type() + "): ");
			    String bt = reader.readLine();
			    if (!bt.isEmpty()) donor.setBlood_type(bt);

			    System.out.print("Country (" + donor.getCountry() + "): ");
			    String country = reader.readLine();
			    if (!country.isEmpty()) donor.setCountry(country);

			    System.out.print("Eligible to donate? (" + donor.getEligible_donate() + ") [true/false]: ");
			    String eligible = reader.readLine();
			    if (!eligible.isEmpty()) donor.setEligible_donate(Boolean.parseBoolean(eligible));

			    System.out.print("Contact number (" + donor.getContact_number() + "): ");
			    String contact = reader.readLine();
			    if (!contact.isEmpty()) donor.setContact_number(contact);

			    System.out.print("Emergency contact number (" + donor.getEmergency_contact_number() + "): ");
			    String emergency = reader.readLine();
			    if (!emergency.isEmpty()) donor.setEmergency_contact_number(emergency);

			    donationsManager.updateDonor(donor);
			    System.out.println("Donor updated successfully.");
			}
			
			
		private static void updateDonation(int donationsWorkerId) throws IOException {
		    System.out.println("To update a donation, first search by status.");
		    
		    System.out.print("Enter donation status (e.g., stored, tested): ");
		    String status = reader.readLine();

		    List<Donation> results = donationsManager.searchDonation(status);
		    
		    if (results.isEmpty()) {
		        System.out.println("No donations found with that status.");
		        return;
		    }

		    System.out.println("Matching donations:");
		    for (Donation d : results) {
		        System.out.println("ID: " + d.getId() + 
		                           " | Date: " + d.getDonation_date() +
		                           " | Donor ID: " + d.getDonor().getId() +
		                           " | Status: " + d.getStatus());
		    }

		    System.out.print("Enter the ID of the donation to update: ");
		    int donationId = Integer.parseInt(reader.readLine());

		    Donation donation = donationsManager.getDonationById(donationId);
		    if (donation == null) {
		        System.out.println("Donation not found.");
		        return;
		    }

		    System.out.println("Type the new data, or press enter to keep current values:");

		    System.out.print("Status (" + donation.getStatus() + "): ");
		    String newStatus = reader.readLine();
		    if (!newStatus.isEmpty()) donation.setStatus(newStatus);

		    System.out.print("Donation date (" + donation.getDonation_date() + ") (YYYY-MM-DD): ");
		    String newDateStr = reader.readLine();
		    if (!newDateStr.isEmpty()) {
		        try {
		            donation.setDonation_date(Date.valueOf(newDateStr));
		        } catch (IllegalArgumentException e) {
		            System.out.println("Invalid date format.");
		            return;
		        }
		    }

		    System.out.print("Quantity (" + donation.getQuantity() + "): ");
		    String quantityStr = reader.readLine();
		    if (!quantityStr.isEmpty()) {
		        try {
		            donation.setQuantity(Integer.parseInt(quantityStr));
		        } catch (NumberFormatException e) {
		            System.out.println("Invalid quantity.");
		            return;
		        }
		    }

		    System.out.print("Expiration date (" + donation.getExpiration_date() + ") (YYYY-MM-DD): ");
		    String expStr = reader.readLine();
		    if (!expStr.isEmpty()) {
		        try {
		            donation.setExpiration_date(Date.valueOf(expStr));
		        } catch (IllegalArgumentException e) {
		            System.out.println("Invalid date format.");
		            return;
		        }
		    }

		  
		    donationsManager.updateDonation(donation);
		    System.out.println("Donation updated successfully.");
		}


		
		private static void requestsWorkerMenu(String email) {
			
			RequestsWorker  rW=requestsManager.getRequestsWorkerByEmail(email);
			
			while(true) {
				
			try {
				System.out.println("Welcome Requests Worker, choose what do you want to do");
				System.out.println("1.Create blood request");
				System.out.println("2.Create recipient");
				System.out.println("3.Create hospital");
				System.out.println("4.Delete recipient");
				System.out.println("5.Delete hospital");
				System.out.println("6.Search blood request");
				System.out.println("7.Search hospital");
				System.out.println("8.Search recipient");
				System.out.println("9.Fulfill requests ");
				System.out.println("10.Update blood request");
				System.out.println("0.Back to main menu");
				int choice=Integer.parseInt(reader.readLine());
			
			
			switch(choice) {
			case 1:
				createBloodRequest(rW.getId());
				break;
			case 2: 
				createRecipient(rW.getId());
				break;
			case 3:
				createHospital(rW.getId());
				break;
			case 4:
				deleteRecipient(rW.getId());
				break;
			case 5:
				deleteHospital(rW.getId());
				break;
			case 6:
				searchBloodRequest();
				break;
			case 7:
				//searchHospital(rW.getId());
				break;
			case 8:
				//searchRecipient(rW.getId());
				break;
			case 9:
				//TODO: CONFIRMAR TIPO
				//fullfillRequests(rW.getId());
				
			case 10:
				//updateBloodRequest(rW.getId());
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

		private static void deleteHospital(int id) {
			/*
			requestsManager.removeHospital(id);
			System.out.println("The hospital has been removed. :(");
			*/
		}

		private static void searchBloodRequest() {
			/*
			System.out.println("Enter the information of the blood request that you wish to search for: ");
			System.out.println("Enter status: ");
			String status=reader.readLine();
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
			*/
		}

		private static void deleteRecipient(int id) {
			// TODO Auto-generated method stub
			
		}

		private static void createBloodRequest(int id) {
			/*
			 * System.out.println("Please, input the Blood request info:");
	        System.out.print("Quantity required: ");
	        Integer quantity_order = Integer.parseInt(reader.readLine());
	        System.out.print("Recipient: "); //TODO deberia de existir un recipient antes de crear un blood request
	        String recipient = reader.readLine();
  
	        RequestsWorker rw = requestsManager.getRequestsWorker(id);
	        Donor donor=new Donor(firstName,lastName,dobDate,bloodType,country,eligible,contact,emergency,dw);
	        BloodRequest blood_request = new BloodRequest()
	        donationsManager.newDonor(donor);
	        System.out.println("New donor registerd correctly!");	
	        */
		}
			

		private static void createRecipient(int id) {
			// TODO Auto-generated method stub
			
		}

		private static void createHospital(int id) {
			// TODO Auto-generated method stub
			
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		
}