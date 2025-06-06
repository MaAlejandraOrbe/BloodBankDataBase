package bloodbank.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;
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
					registerBloodBankWorker();
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
	
	private static void registerBloodBankWorker() throws IOException {
		System.out.println("\nPlease, input the BloodBank worker data: ");
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
		
		BloodBankWorker bbW=new BloodBankWorker(name,phone,email);
		bbManager.insertBloodBankWorker(bbW);
		User u=new User(username,password,email);
		userManager.register(u);
		Role r=userManager.getRole("bloodbankWorker");
		userManager.assignRole(u, r);
		bloodBankWorkerMenu(email);
		
		
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
					bloodBankWorkerMenu(user.getEmail());
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

		

		private static void bloodBankWorkerMenu(String email) throws NumberFormatException, IOException {
			
			BloodBankWorker bbW=bbManager.getBloodBankWorkerByEmail(email);
			while(true) {
				
			try {
				System.out.println("\nWelcome BloodBank Worker, choose what do you want to do");
				System.out.println("1.Create bloodbank");
				System.out.println("2.Search bloodbank");
				System.out.println("3.Update bloodbank");
				System.out.println("4.Delete bloodbank");
				System.out.println("5.Delete donation donor"); //esta creo que es innecesaria
				System.out.println("0.Back to main menu");
				int choice=Integer.parseInt(reader.readLine());
			
			
			switch(choice) {
			case 1:
				createBloodBank(bbW.getId()); 
				break;
			case 2: 
				searchBloodBank();
				break;
			case 3:
				updateBloodBank(bbW.getId());
				
				break;
			case 4:
				deleteBloodBank(bbW.getId());
				break;
			case 5:
				deleteDonation(bbW.getId()); //ns
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

	


		private static void createBloodBank(Integer id) throws IOException {
			
			System.out.println("Enter name: ");
			String name=reader.readLine();
			
			System.out.println("Enter address: ");
			String address=reader.readLine();
			
			System.out.println("Enter city: ");
			String city=reader.readLine();
			System.out.println("Enter contact number: ");
			String contact_number=reader.readLine();
			System.out.println("Enter person responsible: ");
			String person_responsible=reader.readLine();
			System.out.println("Enter capacity stock: ");
			Integer capacity_stock=Integer.parseInt(reader.readLine());
			BloodBankWorker bbW=bbManager.getBloodBankWorker(id);
			BloodBank bloodbank=new BloodBank(name,address,city,contact_number,person_responsible,capacity_stock,bbW);
			bbManager.newBloodBank(bloodbank);
			System.out.println("New BloodBank registerd correctly!");	
			
			
		}
		private static void searchBloodBank() throws IOException {
			System.out.println("Search Blood Bank");
			System.out.println("Enter name: ");
			String name=reader.readLine();
			System.out.println("Enter city: ");
			String city=reader.readLine();
			List<BloodBank> results=bbManager.searchBloodBank(name, city);
			
			if(results.isEmpty()) {
	        	System.out.println("No bloodbank found matching the information given.");
	        	
	        }else {
	        	System.out.println("BloodBank found !");
	        	for(BloodBank bb:results) {
	        		System.out.println(bb);
	        	}
	        }
			
		}
		
		
		private static void updateBloodBank(Integer id) throws NumberFormatException, IOException {
			System.out.println("List of all registered Bloodbanks: ");
			List<BloodBank> bloodbanks=bbManager.getAllBloodBanks();
			if(bloodbanks.isEmpty()) {
				System.out.println("No Bloodbanks found in system.");
				return;
			}
			for(BloodBank bb:bloodbanks){
				System.out.println("ID: "+bb.getId()+
						           " |Name:  "+bb.getName()+
						           " |Address: "+bb.getAddress()+
						           " |City: "+bb.getCity()+
						           " |Contact number: "+bb.getContact_number()+
						           " |Person responsible: "+bb.getPerson_responsible()+
						           " |Capacity stock: "+bb.getCapacity_stock()
						           );
			}
			System.out.println("Enter the ID of the bloodbank to update: ");
			int bbId=Integer.parseInt(reader.readLine());
			BloodBank bloodbank=bbManager.getBloodBankByIdManager(bbId);
			if(bloodbank==null) {
				System.out.println("BloodBank not found");
				return;
			}
			 System.out.println("Type the new data, or press enter to keep current values:");

			    System.out.print("Name (" +bloodbank.getName() + "): ");
			    String n = reader.readLine();
			    if (!n.isEmpty()) bloodbank.setName(n);
			    
			    System.out.print("Address (" +bloodbank.getAddress() + "): ");
			    String a = reader.readLine();
			    if (!a.isEmpty()) bloodbank.setAddress(a);

			    System.out.print("City (" +bloodbank.getCity() + "): ");
			    String c = reader.readLine();
			    if (!c.isEmpty()) bloodbank.setName(c);
			    
			    System.out.print("Contact Number (" +bloodbank.getContact_number() + "): ");
			    String cn = reader.readLine();
			    if (!cn.isEmpty()) bloodbank.setName(cn);

			    System.out.print("Person Responsible (" +bloodbank.getPerson_responsible() + "): ");
			    String pr = reader.readLine();
			    if (!pr.isEmpty()) bloodbank.setPerson_responsible(pr);
			    
			 
			    
			    System.out.print("Capacity stock (" + bloodbank.getCapacity_stock() + "): ");
			    String input = reader.readLine();  

			    if (!input.isEmpty()) {
			        
			            Integer cs = Integer.parseInt(input);
			            bloodbank.setCapacity_stock(cs);
			    }

			
		}
		
		
		private static void deleteBloodBank(Integer id) throws NumberFormatException, IOException {
			System.out.print("Enter the ID of the Bloodbank to delete: ");
			int bbId = Integer.parseInt(reader.readLine());
			bbManager.deleteBloodBank(bbId);;
			System.out.println("Bloodbank deleted successfully.");
			
		}


		private static void deleteDonation(Integer id) throws NumberFormatException, IOException {
			System.out.print("Enter the ID of the donation to delete: ");
			int donationId = Integer.parseInt(reader.readLine());
			bbManager.deleteBloodBank(donationId);;
			System.out.println("Donation deleted successfully.");
			
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

	        donationsManager.newDonation(donation);
	        System.out.println("New donation registerd correctly!");	
			
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
				System.out.println("No donations found with matching status:  "+status);
			}else {
				System.out.println(" Donation found !");
				for(Donation d:donations) {
					System.out.println(d);
				}
			}
		}
		
		
		private static void updateDonor(int id) throws IOException {
			
			System.out.println("List of all registered donors: ");
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
			
			
		private static void updateDonation(int id) throws IOException {
			
			System.out.println("List of registered donations: ");
			List<Donation> donations=donationsManager.getAllDonations();
			
			if(donations.isEmpty()) {
				System.out.println("No donations found is system.");
				return;
			}
			
			for(Donation d:donations) {
				System.out.println("ID: "+d.getId()+"|Status: "+d.getStatus()+"|Donation date: "+d.getDonation_date()+"|Quantity: "+d.getExpiration_date()+"|Expiration date: "+d.getExpiration_date());
				
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
				    System.out.println("1. Create blood request");
				    System.out.println("2. Create recipient");
				    System.out.println("3. Create hospital");
				    System.out.println("4. Delete recipient");
				    System.out.println("5. Link recipient to blood request");
				    System.out.println("6. Search blood request");
				    System.out.println("7. Search hospital");
				    System.out.println("8. Search recipient");
				    System.out.println("9. Fulfill requests");
				    System.out.println("10. Update blood request");
				    System.out.println("0. Back to main menu");

				    int choice = Integer.parseInt(reader.readLine());

				    switch (choice) {
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
				            linkRecipientToBloodRequest();
				            break;
				        case 6:
				            searchBloodRequest(rW.getId());
				            break;
				        case 7:
				            searchHospital(rW.getId());
				            break;
				        case 8:
				            searchRecipient(rW.getId());
				            break;
				        case 9:
				            fullfillBloodRequest();
				            break;
				        case 10:
				            updateBloodRequest();
				            break;
				        case 0:
				            return;
				        default:
				            System.out.println("Invalid option. Please try again.");
				            break;
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
		
		private static void createBloodRequest(int id) throws IOException {
		    System.out.println("Please, input the Blood Request info:");
		    System.out.print("Quantity requested: ");
		    int quantity = Integer.parseInt(reader.readLine());

		    String status = "pending";

		    System.out.print("BloodBank ID: ");
		    int bbid = Integer.parseInt(reader.readLine());
		    BloodBank bb = donationsManager.getBloodBankById(bbid);
		    if (bb == null) {
		        System.out.println("BloodBank not found.");
		        return;
		    }

		    System.out.print("Recipient ID: ");
		    int rid = Integer.parseInt(reader.readLine());
		    Recipient recipient = requestsManager.getRecipientById(rid);
		    if (recipient == null) {
		        System.out.println("Recipient not found.");
		        return;
		    }

		    System.out.print("Donation ID: ");
		    int did = Integer.parseInt(reader.readLine());
		    Donation donation = donationsManager.getDonationById(did);
		    if (donation == null) {
		        System.out.println("Donation not found.");
		        return;
		    }

		    BloodRequest br = new BloodRequest(quantity, status, bb, recipient, donation);
		    requestsManager.newBloodRequest(br);
		    System.out.println("New blood request created successfully!");
		}

		
		private static void createHospital(int id) throws IOException {
			System.out.println("Please, input the Hospital info:");
			System.out.print("Name: ");
			String name = reader.readLine();
			System.out.print("City: ");
			String city = reader.readLine();
			System.out.print("Address: ");
			String address = reader.readLine();
			System.out.print("Person responsible: ");
			String responsible = reader.readLine();
			System.out.print("Contact number: ");
			String contact = reader.readLine();
			
			Hospital hospital = new Hospital(name, city, address, responsible, contact);
			requestsManager.newHospital(hospital);
			System.out.println("New hospital registered successfully!");
		}

		
		private static void createRecipient(int id) throws IOException {
			System.out.println("Please, input the Recipient info:");
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
		    System.out.print("Country: ");
		    String country = reader.readLine();
		    System.out.print("Contact number: ");
		    String contact = reader.readLine();
		    System.out.print("Emergency contact number: ");
		    String emergency = reader.readLine();
		    
		    Recipient recipient = new Recipient(firstName, lastName, dobDate, bloodType, country, contact, emergency);
		    requestsManager.newRecipient(recipient);
		    System.out.println("New recipient registered successfully!");
		}
		
		private static void searchBloodRequest(int id) throws IOException {
		    System.out.print("Enter the Blood Request ID to search: ");
		    int bloodRequestId = Integer.parseInt(reader.readLine());

		    List<BloodRequest> allRequests = requestsManager.getAllBloodRequests();

		    BloodRequest bloodRequest = null;
		    for (BloodRequest br : allRequests) {
		        if (br.getId() == bloodRequestId) {
		            bloodRequest = br;
		            break;
		        }
		    }

		    if (bloodRequest != null) {
		        System.out.println("Blood Request found: ");
		        System.out.println("ID: " + bloodRequest.getId() +
		                           " | Quantity Requested: " + bloodRequest.getQuantity_order() +
		                           " | Status: " + bloodRequest.getStatus());
		    } else {
		        System.out.println("No Blood Request found with ID: " + bloodRequestId);
		    }
		}

		private static void linkRecipientToBloodRequest() throws IOException {
		    System.out.print("Enter the Recipient ID: ");
		    int recipientId = Integer.parseInt(reader.readLine());
		    System.out.print("Enter the Blood Request ID: ");
		    int bloodRequestId = Integer.parseInt(reader.readLine());

		    requestsManager.linkRecipientToBloodRequest(recipientId, bloodRequestId);
		    System.out.println("Recipient linked to Blood Request successfully!");
		}
		
		
		private static void fullfillBloodRequest() throws IOException {
		    System.out.println("List of all Blood Requests: ");
		    List<BloodRequest> allRequests = requestsManager.getAllBloodRequests();

		    if (allRequests.isEmpty()) {
		        System.out.println("No blood requests found in the system.");
		        return;
		    }

		    for (BloodRequest request : allRequests) {
		        System.out.println("ID: " + request.getId() +
		                " | Status: " + request.getStatus() +
		                " | Quantity: " + request.getQuantity_order() +
		                " | Recipient: " + request.getRecipient().getFirst_name());
		    }

		    System.out.print("Enter the ID of the Blood Request to fulfill: ");
		    int requestId = Integer.parseInt(reader.readLine());

		    BloodRequest bloodRequest = requestsManager.getBloodRequestById(requestId);
		    if (bloodRequest == null) {
		        System.out.println("Blood Request not found.");
		        return;
		    }

		    System.out.println("Current status: " + bloodRequest.getStatus());
		    System.out.print("Enter the new status (press enter to keep 'completed'): ");
		    String newStatus = reader.readLine();
		    if (!newStatus.isEmpty()) {
		        bloodRequest.setStatus(newStatus);
		    } else {
		        bloodRequest.setStatus("completed");
		    }

		    requestsManager.fullfillBloodRequest(bloodRequest);
		    System.out.println("Blood request with ID " + requestId + " has been marked as " + bloodRequest.getStatus() + ".");
		}

		private static void searchHospital(int id) throws IOException {
		    System.out.print("Enter the Hospital ID to search: ");
		    int hospitalId = Integer.parseInt(reader.readLine());
		    
		    Hospital hospital = requestsManager.getHospitalById(hospitalId);
		    if (hospital != null) {
		        System.out.println("Hospital found: ");
		        System.out.println(hospital);
		    } else {
		        System.out.println("Hospital not found.");
		    }
		}
		
		private static void searchRecipient(int id) throws IOException {
		    System.out.print("Enter the Recipient ID to search: ");
		    int recipientId = Integer.parseInt(reader.readLine());
		    
		    Recipient recipient = requestsManager.getRecipientById(recipientId);
		    if (recipient != null) {
		        System.out.println("Recipient found: ");
		        System.out.println(recipient);
		    } else {
		        System.out.println("Recipient not found.");
		    }
		}
		

		private static void deleteRecipient(int id) throws IOException {
			System.out.print("Enter the ID of the recipient to delete: ");
			int recipientId = Integer.parseInt(reader.readLine());
			requestsManager.deleteRecipient(recipientId);
			System.out.println("Recipient deleted successfully.");
		}

		private static void updateBloodRequest() throws IOException {
		    System.out.print("Enter the BloodRequest ID to update: ");
		    int bloodRequestId = Integer.parseInt(reader.readLine());

		    BloodRequest bloodRequest = requestsManager.getBloodRequestById(bloodRequestId);
		    if (bloodRequest == null) {
		        System.out.println("BloodRequest not found.");
		        return;
		    }

		    System.out.println("Current details: ");
		    System.out.println("ID: " + bloodRequest.getId() + 
		                       " | Quantity Requested: " + bloodRequest.getQuantity_order() + 
		                       " | Status: " + bloodRequest.getStatus());

		    System.out.println("Type the new data, or press enter to keep current values:");

		    System.out.print("Quantity (" + bloodRequest.getQuantity_order() + "): ");
		    String newQuantity = reader.readLine();
		    if (!newQuantity.isEmpty()) {
		        try {
		            bloodRequest.setQuantity_order(Integer.parseInt(newQuantity));
		        } catch (NumberFormatException e) {
		            System.out.println("Invalid quantity format.");
		            return;
		        }
		    }

		    System.out.print("Status (" + bloodRequest.getStatus() + "): ");
		    String newStatus = reader.readLine();
		    if (!newStatus.isEmpty()) {
		        bloodRequest.setStatus(newStatus);
		    }

		    requestsManager.updateBloodRequest(bloodRequest);
		    System.out.println("BloodRequest updated successfully!");
		}

	}
		
	