CREATE TABLE BloodBank(
      ID INTEGER PRIMARY KEY AUTOINCREMENT,
	  name TEXT NOT NULL,
	  address TEXT NOT NULL,
	  city TEXT NOT NULL,
	  contact_number INTEGER NOT NULL,
	  person_responsible TEXT,
	  capacity_stock INTEGER NOT NULL,
	  A_pos_stock INTEGER,
	  A_neg_stock INTEGER,
	  B_pos_stock INTEGER,
	  B_neg_stock INTEGER,
	  AB_pos_stock INTEGER,
	  AB_neg_stock INTEGER,
	  O_pos_stock INTEGER,
	  O_neg_stock INTEGER	 	  
);

CREATE TABLE Donor(
      ID INTEGER PRIMARY KEY AUTOINCREMENT,
	  first_name TEXT NOT NULL,
	  last_name TEXT NOT NULL,
	  DOB DATE NOT NULL,
	  blood_type TEXT NOT NULL,
	  eligible_to_donate BOOLEAN NOT NULL,
	  country TEXT,
	  contact_number TEXT NOT NULL,
	  emergency_contact_number INTEGER NOT NULL
);

CREATE TABLE Donations(
      ID INTEGER PRIMARY KEY AUTOINCREMENT,
	  donor_id INTEGER NOT NULL, 
	  bloodBank_id INTEGER NOT NULL,
	  status TEXT NOT NULL,
	  date_donation DATE NOT NULL,
	  quantity INTEGER NOT NULL,
	  expiration_date DATE NOT NULL,
	  FOREIGN KEY (donor_id) REFERENCES Donor (id) ON DELETE CASCADE,
	  FOREIGN KEY (bloodBank_id) REFERENCES BloodBank(ID) ON DELETE SET NULL
	  );
	  
CREATE TABLE Recipient(
      ID INTEGER PRIMARY KEY AUTOINCREMENT,
	  first_name TEXT NOT NULL,
	  last_name TEXT NOT NULL,
	  DOB DATE NOT NULL,
	  blood_type TEXT NOT NULL,
	  country TEXT,
	  contact_number INTEGER ,
	  emergency_contact_number INTEGER,
	  hospital_id INTEGER NOT NULL
	  );


CREATE TABLE Hospitals(
      ID INTEGER PRIMARY KEY,
	  name TEXT NOT NULL,
	  city TEXT NOT NULL,
	  address TEXT NOT NULL,
	  person_responsible TEXT,
	  contact_number TEXT NOT NULL
);

	  

CREATE TABLE Hospital_Recipient (
      recipient_id INTEGER NOT NULL,
      hospital_id INTEGER NOT NULL,
      PRIMARY KEY (recipient_id, hospital_id),
      FOREIGN KEY (recipient_id) REFERENCES Recipient(ID) ON DELETE CASCADE,
      FOREIGN KEY (hospital_id) REFERENCES Hospitals(ID) ON DELETE CASCADE
);

CREATE TABLE BloodRequest(
      ID INTEGER PRIMARY KEY,
	  recipient_id INTEGER NOT NULL ,
	  bloodBank_id INTEGER NOT NULL,
	  quantity_order INTEGER NOT NULL,
	  status TEXT NOT NULL,
	  FOREIGN KEY (bloodBank_id) REFERENCES BloodBank(ID) ON DELETE CASCADE,
	  FOREIGN KEY (recipient_id) REFERENCES Recipient(ID) ON DELETE CASCADE
);


