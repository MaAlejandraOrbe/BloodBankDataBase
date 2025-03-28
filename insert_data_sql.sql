
INSERT INTO BloodBank (ID, name, address, city, contact_number, person_responsible, capacity_stock, 
                       A_pos_stock, A_neg_stock, B_pos_stock, B_neg_stock, AB_pos_stock, AB_neg_stock, O_pos_stock, O_neg_stock)
VALUES 
(1, 'Central Blood Bank', '123 Main St', 'New York', '1234567890', 'John Doe', 1000, 200, 150, 180, 120, 100, 90, 220, 140),
(2, 'City Blood Bank', '456 Elm St', 'Los Angeles', '9876543210', 'Jane Smith', 800, 150, 120, 160, 110, 80, 70, 180, 100);

-- Insert sample data into Donor
INSERT INTO Donor (ID, first_name, last_name, DOB, blood_type, eligible_to_donate, country, contact_number, emergency_contact_number)
VALUES 
(1, 'Alice', 'Johnson', '1990-05-14', 'A+', TRUE, 'USA', '5551234567', '5559876543'),
(2, 'Bob', 'Williams', '1985-08-22', 'O-', FALSE, 'USA', '5559876543', '5557654321');

-- Insert sample data into Donations
INSERT INTO Donations (ID, donor_id, bloodBank_id, status, date_donation, quantity, expiration_date)
VALUES 
(1, 1, 1, 'Pending', '2025-03-15', 450, '2025-06-15'),
(2, 2, 2, 'Fulfilled', '2025-02-10', 500, '2025-05-10');

-- Insert sample data into Hospitals
INSERT INTO Hospitals (ID, name, city, address, person_responsible, contact_number)
VALUES 
(1, 'General Hospital', 'New York', '789 Pine St', 'Dr. Emily Clark', '5558765432'),
(2, 'Sunrise Medical Center', 'Los Angeles', '321 Oak St', 'Dr. Michael Scott', '5557654321');

-- Insert sample data into Recipient
INSERT INTO Recipient (ID, first_name, last_name, DOB, blood_type, country, contact_number, emergency_contact_number, hospital_id)
VALUES 
(1, 'Charlie', 'Brown', '2000-03-21', 'A+', 'USA', '5558765432', '5554567890', 1),
(2, 'Dana', 'White', '1978-12-05', 'B-', 'USA', '5557654321', '5551230987', 2);

INSERT INTO Hospital_Recipient(recipient_id, hospital_id) 
VALUES
(1,1),
(1,2),
(2,1);

-- Insert sample data into BloodRequest
INSERT INTO BloodRequest (ID, recipient_id, bloodBank_id, quantity_order, status)
VALUES 
(1, 1, 1, 2, 'Pending'),
(2, 2, 2, 1, 'Fulfilled');
