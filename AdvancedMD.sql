DROP DATABASE advancedMD;
CREATE DATABASE advancedMD;
USE advancedMD;

CREATE TABLE roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) 
);

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- Create patients table
CREATE TABLE patients (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    dob DATE NOT NULL,
    gender VARCHAR(50) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(50),
    admission_date DATE,
    discharge_date DATE,
    status VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Create staff table
CREATE TABLE staff (
    staff_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    specialization VARCHAR(255),
    phone_number VARCHAR(50),
    status VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Create rooms table
CREATE TABLE rooms (
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    room_num VARCHAR(50) NOT NULL,
    room_type VARCHAR(50),
    status VARCHAR(50)
);

-- Create appointments table
CREATE TABLE appointments (
    app_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    staff_id INT NOT NULL,
    room_id INT,
    app_date DATETIME NOT NULL,
    status VARCHAR(50),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (staff_id) REFERENCES staff(staff_id),
    FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);

-- Create medical records table
CREATE TABLE medical_records (
    record_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    staff_id INT NOT NULL,
    diagnosis VARCHAR(255),
    prescription VARCHAR(255),
    report TEXT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (staff_id) REFERENCES staff(staff_id)
);

CREATE TABLE schedules (
    s_schedule_id INT PRIMARY KEY AUTO_INCREMENT,
    schedule_name VARCHAR(255) NOT NULL,
    shift_start DATETIME NOT NULL,
    shift_end DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE staff_schedule (
    assignment_id INT PRIMARY KEY AUTO_INCREMENT,
    s_schedule_id INT NOT NULL,
    staff_id INT NOT NULL,
    FOREIGN KEY (s_schedule_id) REFERENCES schedules(s_schedule_id),
    FOREIGN KEY (staff_id) REFERENCES staff(staff_id),
    UNIQUE (s_schedule_id, staff_id) -- Prevents duplicate assignments
);

-- Create room assignments table
CREATE TABLE room_assignments (
    assignment_id INT PRIMARY KEY AUTO_INCREMENT,
    room_id INT NOT NULL,
    patient_id INT NOT NULL,
    check_in DATETIME NOT NULL,
    check_out DATETIME DEFAULT NULL,
    status VARCHAR(50),
    FOREIGN KEY (room_id) REFERENCES rooms(room_id),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);

-- Create payments table
CREATE TABLE payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_date DATETIME NOT NULL,
    method VARCHAR(50),
    status VARCHAR(50),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);

-- Create prescriptions table
CREATE TABLE prescriptions (
    prescription_id INT PRIMARY KEY AUTO_INCREMENT,
    record_id INT NOT NULL,
    medication_name VARCHAR(255) NOT NULL,
    dosage VARCHAR(255),
    instructions VARCHAR(255),
    FOREIGN KEY (record_id) REFERENCES medical_records(record_id)
);

-- Create billing table
CREATE TABLE billing (
    billing_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    service_description VARCHAR(255) NOT NULL,
    service_cost DECIMAL(10, 2) NOT NULL,
    date_billed DATETIME NOT NULL,
    status VARCHAR(50),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);

-- Create medical procedures table
CREATE TABLE medical_procedures (
    procedure_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    staff_id INT NOT NULL,
    procedure_name VARCHAR(255) NOT NULL,
    procedure_date DATETIME NOT NULL,
    notes TEXT,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (staff_id) REFERENCES staff(staff_id)
);

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_STAFF');
INSERT INTO roles (name) VALUES ('ROLE_PATIENT');

INSERT INTO users (password, username) VALUES ('password123', 'admin_user');
INSERT INTO users (password, username) VALUES ('password123', 'staff_john');
INSERT INTO users (password, username) VALUES ('password123', 'staff_anna');
INSERT INTO users (password, username) VALUES ('password123', 'staff_lisa');
INSERT INTO users (password, username) VALUES ('password123', 'patient_mike');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);  -- Admin role for admin_user
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);  -- Doctor role for doctor_john
INSERT INTO user_roles (user_id, role_id) VALUES (3, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (4, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (5, 3);  -- Patient role for patient_mike

INSERT INTO patients (user_id, firstname, lastname, dob, gender, address, phone_number, admission_date, discharge_date, status) 
VALUES (5, 'Mike', 'Tyson', '1990-01-01', 'Male', '1234 Elm Street, City, Country', '555-1234', '2024-01-15', '2024-01-20', 'Discharged');

INSERT INTO staff (user_id, name, specialization, phone_number, status) 
VALUES (2, 'Dr. John Doe', 'Cardiologist', '555-2345', 'Active');
INSERT INTO staff (user_id, name, specialization, phone_number, status) 
VALUES (3, 'Dr. Emily Roberts', 'Neurologist', '555-4567', 'Active');

INSERT INTO staff (user_id, name, specialization, phone_number, status) 
VALUES (4, 'Dr. Steve Johnson', 'Orthopedic Surgeon', '555-5678', 'Active');



INSERT INTO rooms (room_num, room_type, status) 
VALUES ('101', 'Private', 'Occupied');
INSERT INTO rooms (room_num, room_type, status) 
VALUES ('102', 'Semi-private', 'Available');
INSERT INTO rooms (room_num, room_type, status) 
VALUES ('103', 'ICU', 'Occupied');

INSERT INTO appointments (patient_id, staff_id, room_id, app_date, status) 
VALUES (1, 2, 1, '2024-01-16 09:00:00', 'Scheduled');
INSERT INTO appointments (patient_id, staff_id, room_id, app_date, status) 
VALUES (1, 3, 2, '2024-01-16 10:00:00', 'Completed');

INSERT INTO medical_records (patient_id, staff_id, diagnosis, prescription, report, created_at) 
VALUES (1, 2, 'Hypertension', 'Losartan 50mg', 'Patient is diagnosed with high blood pressure, prescribed medication.', '2024-01-16 11:00:00');

INSERT INTO schedules (schedule_name, shift_start, shift_end, status) 
VALUES ('Morning Shift', '2024-01-16 07:00:00', '2024-01-16 15:00:00', 'Active');
INSERT INTO schedules (schedule_name, shift_start, shift_end, status) 
VALUES ('Night Shift', '2024-01-16 15:00:00', '2024-01-17 07:00:00', 'Active');

INSERT INTO staff_schedule (s_schedule_id, staff_id) 
VALUES (1, 2);  -- Assign Dr. John Doe to Morning Shift
INSERT INTO staff_schedule (s_schedule_id, staff_id) 
VALUES (2, 3);  


INSERT INTO room_assignments (room_id, patient_id, check_in, check_out, status) 
VALUES (1, 1, '2024-01-15 14:00:00', '2024-01-20 10:00:00', 'Completed');

INSERT INTO payments (patient_id, amount, payment_date, method, status) 
VALUES (1, 150.00, '2024-01-18 10:00:00', 'Credit Card', 'Paid');

INSERT INTO prescriptions (record_id, medication_name, dosage, instructions) 
VALUES (1, 'Losartan', '50mg', 'Take 1 tablet daily');

INSERT INTO billing (patient_id, service_description, service_cost, date_billed, status) 
VALUES (1, 'Consultation with Dr. John Doe', 100.00, '2024-01-16 11:00:00', 'Billed');

INSERT INTO medical_procedures (patient_id, staff_id, procedure_name, procedure_date, notes) 
VALUES (1, 2, 'Cardiac Stress Test', '2024-01-17 08:00:00', 'Procedure performed successfully');








